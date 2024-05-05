package org.jxch.capital.stock4j.yahoo.quote.converter;

import org.jxch.capital.stock4j.api.StockBatchParam;
import org.jxch.capital.stock4j.yahoo.quote.YahooQuoteParam;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockBatchParamToYahooQuoteParamConverter implements Converter<StockBatchParam, YahooQuoteParam> {

    @Override
    public YahooQuoteParam convert(StockBatchParam source) {
        return YahooQuoteParam.builder().symbols(source.getCodes()).build();
    }

}
