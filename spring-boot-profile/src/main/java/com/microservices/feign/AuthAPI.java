package com.microservices.feign;

import com.microservices.feign.client.Auth;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${auth.url}", path = "/api/v1", value = "auth-service")
public interface AuthAPI {
  @GetMapping("/deserialize")
  public Auth getAuth(@RequestHeader("Authorization") String authorization);
}
