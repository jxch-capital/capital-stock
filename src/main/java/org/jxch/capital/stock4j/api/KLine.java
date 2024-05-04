package org.jxch.capital.stock4j.api;

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
public class KLine {
    private Date date;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
}
