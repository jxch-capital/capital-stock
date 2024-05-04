package org.jxch.capital.stock4j.config;

import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Data
@Configuration
@ComponentScan("org.jxch.capital.stock4j")
public class Stock4JAutoConfig {
    public final static String STOCK_API = "CAPITAL_STOCK4J_API";
    public final static String OK_HTTP_CLIENT = "OK_HTTP_CLIENT";
    @Value("${capital.stock4j.web.enable:false}")
    private boolean webEnable;
    @Value("${capital.stock4j.proxy.enable:false}")
    private boolean useProxy;
    @Value("${capital.stock4j.proxy.host:localhost}")
    private String proxyHost;
    @Value("${capital.stock4j.proxy.port:10809}")
    private Integer proxyPort;
    @Value("${capital.stock4j.proxy.type:HTTP}")
    private String proxyType;

    @Bean(OK_HTTP_CLIENT)
    public OkHttpClient stock4jOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (useProxy) {
            builder.proxy(new Proxy(Proxy.Type.valueOf(proxyType), new InetSocketAddress(proxyHost, proxyPort)));
        }

        return builder.build();
    }

    @Bean
    public ConversionService conversionService(ApplicationContext context) {
        DefaultConversionService service = new DefaultConversionService();
        context.getBeansOfType(Converter.class).values().forEach(service::addConverter);
        return service;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
