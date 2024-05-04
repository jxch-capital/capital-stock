package org.jxch.capital.stock4j.api.convert;

import org.jxch.capital.stock4j.api.StockBatchRes;
import org.jxch.capital.stock4j.api.StockRes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockResListToStockBatchResConverter implements Converter<List<StockRes>, StockBatchRes> {

    @Override
    public StockBatchRes convert(List<StockRes> source) {
        StockBatchRes stockBatchRes = new StockBatchRes();
        stockBatchRes.addAllByStockRes(source);
        return stockBatchRes;
    }

}
