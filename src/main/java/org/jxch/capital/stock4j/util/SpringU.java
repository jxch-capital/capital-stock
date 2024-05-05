package org.jxch.capital.stock4j.util;

import org.jxch.capital.stock4j.config.Stock4JAutoConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class SpringU implements ApplicationContextAware {
    public static ApplicationContext context;
    private static ConversionService conversion;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        conversion = context.getBean(Stock4JAutoConfig.STOCK4J_CONVERSION_SERVICE, ConversionService.class);
    }

    public static <T, R> R convert(T t, Class<R> r) {
        return conversion.convert(t, r);
    }

}
