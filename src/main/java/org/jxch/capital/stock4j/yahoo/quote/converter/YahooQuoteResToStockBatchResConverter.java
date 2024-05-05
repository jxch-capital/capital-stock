package org.jxch.capital.stock4j.yahoo.quote.converter;

import org.jxch.capital.stock4j.api.StockBatchRes;
import org.jxch.capital.stock4j.api.StockRes;
import org.jxch.capital.stock4j.util.SpringU;
import org.jxch.capital.stock4j.yahoo.quote.YahooQuoteRes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class YahooQuoteResToStockBatchResConverter implements Converter<YahooQuoteRes, StockBatchRes> {

    @Override
    public StockBatchRes convert(YahooQuoteRes source) {
        StockBatchRes res = new StockBatchRes();
        List<YahooQuoteRes.QuoteResponseResultItem> items = source.getQuoteResponse().getResult();
        YahooQuoteRes yahooQuoteRes = new YahooQuoteRes().setQuoteResponse(new YahooQuoteRes.QuoteResponse());
        for (YahooQuoteRes.QuoteResponseResultItem item : items) {
            yahooQuoteRes.getQuoteResponse().setResult(List.of(item));
            res.addByStockRes(SpringU.convert(yahooQuoteRes, StockRes.class));
        }
        return res;
    }

}
