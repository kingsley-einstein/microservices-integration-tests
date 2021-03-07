package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootProfileApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootProfileApplication.class, args);
  }
}
