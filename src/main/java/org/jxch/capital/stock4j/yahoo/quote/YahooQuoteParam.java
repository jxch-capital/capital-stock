package org.jxch.capital.stock4j.yahoo.quote;

import lombok.*;
import okhttp3.HttpUrl;
import org.jxch.capital.stock4j.support.UrlParamSupport;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YahooQuoteParam implements UrlParamSupport {
    private List<String> symbols;

    public String getSymbolsParam() {
        return String.join(",", symbols);
    }

    @Override
    public HttpUrl toUrl(@NonNull HttpUrl.Builder builder) {
        return builder.addQueryParameter("symbols", getSymbolsParam()).build();
    }
}
