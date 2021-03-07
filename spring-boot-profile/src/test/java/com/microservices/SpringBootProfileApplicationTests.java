package com.microservices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.microservices.feign.client.Auth;
import java.util.UUID;
// import org.junit.After;
import org.junit.AfterClass;
// import org.junit.AfterClass;
// import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
// import org.aspectj.lang.annotation.After;
// import org.aspectj.lang.annotation.Before;
// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

// @TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class SpringBootProfileApplicationTests {

  @ClassRule
  public static WireMockRule mockRule = new WireMockRule(5000);

  @BeforeClass
  public static void startServer() {
    Auth auth = new Auth("0x", UUID.randomUUID());

    mockRule.start();

    mockRule.stubFor(
      WireMock
        .get(WireMock.urlMatching("/api/v1/deserialize"))
        .withHeader("Authorization", WireMock.equalTo("Bearer xxxxx"))
        .willReturn(
          WireMock
            .aResponse()
            .withBody(auth.toString())
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
        )
    );
  }

  @Test
  public void runTest() {
    final RestTemplate template = new RestTemplate();
    final HttpHeaders headers = new HttpHeaders();

    headers.set("Authorization", "Bearer xxxxx");

    final HttpEntity<?> entity = new HttpEntity<>(headers);
    final ResponseEntity<Auth> response = template.exchange(
      "http://localhost:5000/api/v1/deserialize",
      HttpMethod.GET,
      entity,
      Auth.class,
      1
    );

    System.out.println(" ======> " + response.getBody().toString());

    assertNotNull(response.getBody());
    assertEquals(response.getBody().getUsername(), "0x");
  }

  @AfterClass
  public static void stopServer() {
    mockRule.stop();
  }
}
