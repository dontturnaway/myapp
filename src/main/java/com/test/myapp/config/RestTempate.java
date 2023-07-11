package com.test.myapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/* Needed for remote calls via RestTemplate*/
@Configuration
public class RestTempate {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /* If we need to customize it
    @Autowired(required=false)
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
    */
}
