package org.skozurak.integration.service.java_camel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ConversionService conversionService;

    public WebConfig(@Lazy ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }
}
