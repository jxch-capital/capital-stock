package org.jxch.capital.stock4j.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StockBatchRes {
    private Map<String, List<KLine>> klines = new HashMap<>();

    public StockBatchRes addByStockRes(StockRes stockRes) {
        klines.put(stockRes.getCode(), stockRes.getKLines());
        return this;
    }

    public StockBatchRes addAllByStockRes(List<StockRes> stockRes) {
        stockRes.forEach(this::addByStockRes);
        return this;
    }

}
