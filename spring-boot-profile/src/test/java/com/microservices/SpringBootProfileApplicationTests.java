package com.microservices;

import com.github.tomakehurst.wiremock.WireMockServer;
// import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootProfileApplicationTests {

  WireMockServer mockServer = new WireMockServer(5000);

  @BeforeAll
  void startServer() {
    this.mockServer.start();
  }

  @Test
  void runTest() {
    this.mockServer.stubFor(null);
  }

  @AfterAll
  void stopServer() {
    this.mockServer.stop();
  }
}
