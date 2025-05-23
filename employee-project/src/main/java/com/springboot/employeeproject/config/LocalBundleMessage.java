package com.springboot.employeeproject.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class LocalBundleMessage {

    @Value("${spring.messages.basename}")
    private String msg;

    @Bean
    public ResourceBundleMessageSource getMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(msg);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
