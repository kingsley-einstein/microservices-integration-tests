package com.microservices.feign.client;

import java.util.UUID;

@SuppressWarnings("serial")
public class Auth implements java.io.Serializable {

  private String username;
  private UUID id;

  public Auth() {}

  public Auth(final String username, final UUID id) {
    this.username = username;
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public UUID getId() {
    return id;
  }
}
