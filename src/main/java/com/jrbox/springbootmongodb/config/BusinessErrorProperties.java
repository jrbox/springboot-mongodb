package com.jrbox.springbootmongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:business-error.properties")
public class BusinessErrorProperties {
    @Autowired
    private Environment env;

    public String getErrorByCode(String code) {
        return env.getProperty(code);
    }
}
