package org.jxch.capital.stock4j.yahoo.quote.converter;

import org.jxch.capital.stock4j.api.KLine;
import org.jxch.capital.stock4j.api.StockRes;
import org.jxch.capital.stock4j.yahoo.quote.YahooQuoteRes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class YahooQuoteResToStockResConverter implements Converter<YahooQuoteRes, StockRes> {

    @Override
    public StockRes convert(YahooQuoteRes source) {
        YahooQuoteRes.QuoteResponseResultItem item = source.getQuoteResponse().getResult().get(0);
        return StockRes.builder()
                .code(item.getSymbol())
                .kLines(List.of(KLine.builder()
                        .date(new Date(item.getRegularMarketTime() * 1000))
                        .close(item.getRegularMarketPrice())
                        .open(item.getRegularMarketOpen())
                        .high(item.getRegularMarketDayHigh())
                        .low(item.getRegularMarketDayLow())
                        .volume(Double.valueOf(item.getRegularMarketVolume()))
                        .build()))
                .build();
    }

}
