package org.jxch.capital.stock4j.yahoo.quote;

import com.alibaba.fastjson2.JSONObject;
import lombok.NonNull;
import lombok.SneakyThrows;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jxch.capital.stock4j.api.*;
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
public class YahooQuoteApi implements YahooStockApi {
    private final OkHttpClient client;
    private final Supplier<HttpUrl.Builder> url;
    private final Supplier<Request.Builder> request;

    public YahooQuoteApi(@Qualifier(Stock4JAutoConfig.OK_HTTP_CLIENT) OkHttpClient client,
                       @Qualifier(YahooConfig.NEW_YAHOO_URL_QUOTE) Supplier<HttpUrl.Builder> url,
                       @Qualifier(YahooConfig.NEW_YAHOO_REQUEST) Supplier<Request.Builder> request) {
        this.client = client;
        this.url = url;
        this.request = request;
    }

    @SneakyThrows
    public YahooQuoteRes quote(@NonNull YahooQuoteParam param) {
        try (Response response = client.newCall(param.newRequest(request, url)).execute()) {
            return JSONObject.parseObject(Objects.requireNonNull(response.body()).string(), YahooQuoteRes.class);
        }
    }

    @Override
    public StockRes query(StockParam param) {
        YahooQuoteParam yahooQuoteParam = SpringU.convert(param, YahooQuoteParam.class);
        return SpringU.convert(quote(yahooQuoteParam), StockRes.class);
    }

    @Override
    public StockBatchRes query(StockBatchParam param) {
        YahooQuoteParam yahooQuoteParam = SpringU.convert(param, YahooQuoteParam.class);
        return SpringU.convert(quote(yahooQuoteParam), StockBatchRes.class);
    }

    @Override
    public boolean support(StockParam param) {
        return Objects.equals(param.getInterval(), StockInterval.REAL);
    }

    @Override
    public boolean support(StockBatchParam param) {
        return Objects.equals(param.getInterval(), StockInterval.REAL);
    }

}
