package org.jxch.capital.stock4j.yahoo.chart.converter;

import org.jxch.capital.stock4j.api.StockParam;
import org.jxch.capital.stock4j.yahoo.chart.YahooChartUrlParam;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockParamToYahooChartUrlParamConverter  implements Converter<StockParam, YahooChartUrlParam> {

    @Override
    public YahooChartUrlParam convert(StockParam source) {
        YahooChartUrlParam param = YahooChartUrlParam.builder()
                .code(source.getCode())
                .start(source.getStart())
                .end(source.getEnd())
                .build();

        switch (source.getInterval()) {
            case DAY_1 -> param.setInterval("1d");
            default -> throw new IllegalArgumentException("不支持的时间级别");
        }

        return param;
    }

}
