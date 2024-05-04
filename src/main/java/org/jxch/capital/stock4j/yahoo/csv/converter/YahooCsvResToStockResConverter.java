package org.jxch.capital.stock4j.yahoo.csv.converter;

import org.jxch.capital.stock4j.api.KLine;
import org.jxch.capital.stock4j.api.StockRes;
import org.jxch.capital.stock4j.yahoo.csv.YahooCsvRes;
import org.jxch.capital.stock4j.yahoo.csv.YahooCsvUrlRes;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class YahooCsvResToStockResConverter implements Converter<YahooCsvRes, StockRes> {

    private KLine toKLine(YahooCsvUrlRes res) {
        KLine kLine = new KLine();
        BeanUtils.copyProperties(res, kLine);
        return kLine;
    }

    @Override
    public StockRes convert(YahooCsvRes source) {
        return StockRes.builder()
                .code(source.getCode())
                .kLines(source.getRes().stream().map(this::toKLine).sorted(Comparator.comparing(KLine::getDate)).toList())
                .build();
    }

}
