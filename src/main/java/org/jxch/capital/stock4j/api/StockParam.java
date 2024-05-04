package org.jxch.capital.stock4j.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Calendar;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class StockParam {
    private String code;
    private Date start;
    @Builder.Default
    private Date end = Calendar.getInstance().getTime();
    @Builder.Default
    private StockInterval interval = StockInterval.DAY_1;
}
