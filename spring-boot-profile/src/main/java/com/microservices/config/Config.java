package com.microservices.config;

import com.microservices.data.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

  @Bean
  public DataSource dataSource() {
    return new DataSource();
  }
}
