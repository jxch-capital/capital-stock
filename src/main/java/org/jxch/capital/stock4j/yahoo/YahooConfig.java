package org.jxch.capital.stock4j.yahoo;

import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class YahooConfig {
    public final static String YAHOO_API = "YAHOO_API";
    public final static String NEW_YAHOO_URL = "NEW_YAHOO_URL";
    public final static String NEW_YAHOO_REQUEST = "NEW_YAHOO_REQUEST";
    public final static String NEW_YAHOO_URL_QUOTE = "NEW_YAHOO_URL_QUOTE";
    public final static String NEW_YAHOO_URL_CSV = "NEW_YAHOO_URL_CSV";
    public final static String NEW_YAHOO_URL_CHART = "NEW_YAHOO_URL_CHART";
    @Value("${capital.stock4j.yahoo.cookie:GUC=AQEBCAFlULBleUIhXwTg&s=AQAAAC76j7jx&g=ZU9e-w; A1=d=AQABBK0Lr2ICEI-bBud4SuIDLaB4bqaMNbAFEgEBCAGwUGV5Zc3ibmUB_eMBAAcIrQuvYqaMNbA&S=AQAAAggpViKy189d2OkdWsFnK_Y; A3=d=AQABBK0Lr2ICEI-bBud4SuIDLaB4bqaMNbAFEgEBCAGwUGV5Zc3ibmUB_eMBAAcIrQuvYqaMNbA&S=AQAAAggpViKy189d2OkdWsFnK_Y; A1S=d=AQABBK0Lr2ICEI-bBud4SuIDLaB4bqaMNbAFEgEBCAGwUGV5Zc3ibmUB_eMBAAcIrQuvYqaMNbA&S=AQAAAggpViKy189d2OkdWsFnK_Y; gpp=DBAA; gpp_sid=-1; gam_id=y-rpk1JRJE2uL6EDgVwC0_in.GgdLv339c~A; axids=gam=y-rpk1JRJE2uL6EDgVwC0_in.GgdLv339c~A&dv360=eS00Mm1IMHpoRTJ1RXhhNVlTUGtSVWRPdkZCWExrbjFkNH5B; tbla_id=ba643052-93dc-4ae8-a277-ee1cb7f7dab5-tuctc48e47a; cmp=t=1699788664&j=0&u=1---; PRF=t%3DZURN.SW%252BSREN.SW%26newChartbetateaser%3D0%252C1700910092680")
    private String cookie;
    @Value("${capital.stock4j.yahoo.user-agent:Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0}")
    private String userAgent;
    @Value("${capital.stock4j.yahoo.crumb:mdeVssfeRhi}")
    private String crumb;

    @Bean(NEW_YAHOO_URL)
    public Supplier<HttpUrl.Builder> newYahooUrlBuilder() {
        return () -> new HttpUrl.Builder()
                .scheme("https")
                .addQueryParameter("crumb", crumb);
    }

    @Bean(NEW_YAHOO_REQUEST)
    public Supplier<Request.Builder> newYahooRequestBuilder() {
        return () -> new Request.Builder()
                .addHeader("cookie", cookie)
                .addHeader("user-agent", userAgent);
    }

    @Bean(NEW_YAHOO_URL_QUOTE)
    public Supplier<HttpUrl.Builder> newYahooQuoteUrlBuilder(@Qualifier(NEW_YAHOO_URL) Supplier<HttpUrl.Builder> newYahooUrlBuilder) {
        return () -> newYahooUrlBuilder.get()
                .host("query1.finance.yahoo.com")
                .addPathSegments("/v7/finance/quote");
    }

    @Bean(NEW_YAHOO_URL_CSV)
    public Supplier<HttpUrl.Builder> newYahooDownloadStockCsvUrlBuilder(@Qualifier(NEW_YAHOO_URL) Supplier<HttpUrl.Builder> newYahooUrlBuilder) {
        return () -> newYahooUrlBuilder.get()
                .host("query1.finance.yahoo.com")
                .addPathSegments("/v7/finance/download/");
    }

    @Bean(NEW_YAHOO_URL_CHART)
    public Supplier<HttpUrl.Builder> newYahooChartUrlBuilder(@Qualifier(NEW_YAHOO_URL) Supplier<HttpUrl.Builder> newYahooUrlBuilder) {
        return () -> newYahooUrlBuilder.get()
                .host("query2.finance.yahoo.com")
                .addPathSegments("/v8/finance/chart/");
    }

}
