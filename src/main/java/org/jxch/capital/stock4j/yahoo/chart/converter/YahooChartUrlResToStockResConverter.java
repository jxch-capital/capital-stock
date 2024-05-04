package org.jxch.capital.stock4j.yahoo.chart.converter;

import org.jxch.capital.stock4j.api.KLine;
import org.jxch.capital.stock4j.api.StockRes;
import org.jxch.capital.stock4j.yahoo.chart.YahooChartUrlRes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

@Component
public class YahooChartUrlResToStockResConverter implements Converter<YahooChartUrlRes, StockRes> {

    @Override
    public StockRes convert(YahooChartUrlRes source) {
        return StockRes.builder()
                .code(source.getCode())
                .kLines(IntStream.range(0, source.getLengthThrow()).parallel().mapToObj(index -> new KLine()
                                .setDate(source.getDate(index))
                                .setClose(source.getClose(index))
                                .setOpen(source.getOpen(index))
                                .setLow(source.getLow(index))
                                .setHigh(source.getHigh(index))
                                .setVolume(source.getVolume(index)))
                        .filter(kLine -> Objects.nonNull(kLine.getClose()))
                        .sorted(Comparator.comparing(KLine::getDate)).toList())
                .build();
    }

}
