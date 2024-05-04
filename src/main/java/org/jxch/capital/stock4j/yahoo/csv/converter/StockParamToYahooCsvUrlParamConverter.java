package org.jxch.capital.stock4j.yahoo.csv.converter;

import org.jxch.capital.stock4j.api.StockParam;
import org.jxch.capital.stock4j.yahoo.csv.YahooCsvUrlParam;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockParamToYahooCsvUrlParamConverter implements Converter<StockParam, YahooCsvUrlParam> {

    @Override
    public YahooCsvUrlParam convert(StockParam source) {
        YahooCsvUrlParam param = YahooCsvUrlParam.builder()
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
