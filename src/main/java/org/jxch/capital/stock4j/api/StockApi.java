package org.jxch.capital.stock4j.api;

import org.jxch.capital.stock4j.util.SpringU;

import java.util.List;

public interface StockApi {

    StockRes query(StockParam param);

    @SuppressWarnings("unchecked")
    default StockBatchRes query(StockBatchParam param) {
        List<StockParam> params = SpringU.convert(param, List.class);
        List<StockRes> stockRes = params.stream().map(this::query).toList();
        return SpringU.convert(stockRes, StockBatchRes.class);
    }

    default boolean support(StockParam param) {
        return true;
    }

    default boolean support(StockBatchParam param) {
        return true;
    }

}
