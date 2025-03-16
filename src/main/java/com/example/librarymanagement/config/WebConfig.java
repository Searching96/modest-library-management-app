package com.example.librarymanagement.config;

import com.example.librarymanagement.converter.StringToAuthorConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final StringToAuthorConverter stringToAuthorConverter;

    @Autowired
    public WebConfig(StringToAuthorConverter stringToAuthorConverter) {
        this.stringToAuthorConverter = stringToAuthorConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToAuthorConverter);
    }
}
