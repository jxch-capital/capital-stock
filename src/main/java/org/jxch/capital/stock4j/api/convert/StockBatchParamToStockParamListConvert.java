package org.jxch.capital.stock4j.api.convert;

import org.jxch.capital.stock4j.api.StockBatchParam;
import org.jxch.capital.stock4j.api.StockParam;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockBatchParamToStockParamListConvert implements Converter<StockBatchParam, List<StockParam>> {

    @Override
    public List<StockParam> convert(StockBatchParam source) {
        return source.getCodes().stream().map(code -> StockParam.builder()
                .code(code)
                .start(source.getStart())
                .end(source.getEnd())
                .interval(source.getInterval())
                .build()).toList();
    }

}
