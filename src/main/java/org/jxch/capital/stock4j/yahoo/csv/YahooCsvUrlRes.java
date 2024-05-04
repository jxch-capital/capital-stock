package org.jxch.capital.stock4j.yahoo.csv;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class YahooCsvUrlRes {
    @Alias("Date")
    private Date date;
    @Alias("Open")
    private Double open;
    @Alias("High")
    private Double high;
    @Alias("Low")
    private Double low;
    @Alias("Close")
    private Double close;
    @Alias("Adj Close")
    private Double adjClose;
    @Alias("Volume")
    private Double volume;
}
