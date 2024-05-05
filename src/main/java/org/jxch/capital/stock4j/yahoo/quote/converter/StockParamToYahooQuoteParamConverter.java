package org.jxch.capital.stock4j.yahoo.quote.converter;

import org.jxch.capital.stock4j.api.StockParam;
import org.jxch.capital.stock4j.yahoo.quote.YahooQuoteParam;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockParamToYahooQuoteParamConverter implements Converter<StockParam, YahooQuoteParam> {

    @Override
    public YahooQuoteParam convert(StockParam source) {
        return YahooQuoteParam.builder().symbols(List.of(source.getCode())).build();
    }

}
