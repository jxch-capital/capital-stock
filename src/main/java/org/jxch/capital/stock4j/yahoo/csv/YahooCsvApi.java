package org.jxch.capital.stock4j.yahoo.csv;

import cn.hutool.core.text.csv.CsvUtil;
import lombok.NonNull;
import lombok.SneakyThrows;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jxch.capital.stock4j.api.StockParam;
import org.jxch.capital.stock4j.api.StockRes;
import org.jxch.capital.stock4j.config.Stock4JAutoConfig;
import org.jxch.capital.stock4j.util.SpringU;
import org.jxch.capital.stock4j.yahoo.YahooConfig;
import org.jxch.capital.stock4j.yahoo.YahooStockApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Supplier;

@Component
@Qualifier(YahooConfig.YAHOO_API)
public class YahooCsvApi implements YahooStockApi {
    private final OkHttpClient client;
    private final Supplier<HttpUrl.Builder> url;
    private final Supplier<Request.Builder> request;

    public YahooCsvApi(@Qualifier(Stock4JAutoConfig.OK_HTTP_CLIENT) OkHttpClient client,
                       @Qualifier(YahooConfig.NEW_YAHOO_URL_CSV) Supplier<HttpUrl.Builder> url,
                       @Qualifier(YahooConfig.NEW_YAHOO_REQUEST) Supplier<Request.Builder> request) {
        this.client = client;
        this.url = url;
        this.request = request;
    }

    @SneakyThrows
    public YahooCsvRes downloadStockCsv(@NonNull YahooCsvUrlParam param) {
        try (Response response = client.newCall(param.newRequest(request, url)).execute()) {
            return YahooCsvRes.builder().code(param.getCode())
                    .res(CsvUtil.getReader().read(Objects.requireNonNull(response.body()).string(), YahooCsvUrlRes.class))
                    .build();
        }
    }

    @Override
    public StockRes query(StockParam param) {
        YahooCsvUrlParam csvUrlParam = SpringU.convert(param, YahooCsvUrlParam.class);
        return SpringU.convert(downloadStockCsv(csvUrlParam), StockRes.class);
    }

}
