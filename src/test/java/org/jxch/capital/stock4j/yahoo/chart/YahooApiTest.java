package org.jxch.capital.stock4j.yahoo.chart;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.jxch.capital.stock4j.api.*;
import org.jxch.capital.stock4j.config.Stock4JAutoConfig;
import org.jxch.capital.stock4j.yahoo.YahooStockApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest(classes = Stock4JAutoConfig.class)
@ActiveProfiles("test")
class YahooApiTest {
    @Autowired
    private YahooStockApi yahooApi;

    @Test
    void query() {
        StockRes stockRes = yahooApi.query(StockParam.builder().code("QQQ")
                .start(Date.from(LocalDate.now().plusDays(-20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .build());
        log.info(JSON.toJSONString(stockRes));
    }

    @Test
    void queryBatch() {
        StockBatchRes stockRes = yahooApi.query(StockBatchParam.builder().codes(List.of("QQQ", "SPY"))
                .start(Date.from(LocalDate.now().plusDays(-20).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                .interval(StockInterval.REAL)
                .build());
        log.info(JSON.toJSONString(stockRes));
    }

}