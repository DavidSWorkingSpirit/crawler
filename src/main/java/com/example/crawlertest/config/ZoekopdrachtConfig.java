package com.example.crawlertest.config;

import com.example.crawlertest.domein.Zoekopdracht;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZoekopdrachtConfig {

    @Bean
    public Zoekopdracht zoekopdracht() {

        return new Zoekopdracht();
    }
}
