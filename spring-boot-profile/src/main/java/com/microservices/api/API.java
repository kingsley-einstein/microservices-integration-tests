package com.microservices.api;

import com.microservices.data.DataSource;
import com.microservices.errors.Error;
import com.microservices.feign.AuthAPI;
import com.microservices.feign.client.Auth;
import com.microservices.models.Profile;
import com.microservices.models.types.MaritalStatus;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class API {

  @Autowired
  private AuthAPI api;

  @Autowired
  private DataSource dataSource;

  @PostMapping("/add_profile")
  public ResponseEntity<Profile> addProfile(
    @RequestHeader("Authorization") String authorization,
    Map<String, Object> body
  ) {
    try {
      Auth auth = api.getAuth(authorization);
      Profile profile = dataSource.add(
        new Profile(
          (Date) body.get("dob"),
          MaritalStatus.valueOf((String) body.get("maritalStatus")),
          auth.getId()
        )
      );
      return new ResponseEntity<>(profile, HttpStatus.CREATED);
    } catch (Exception exc) {
      throw new Error(500, exc.getMessage());
    }
  }

  @GetMapping("/byUser")
  public ResponseEntity<Profile> findByUser(
    @RequestHeader("Authorization") String authorization
  ) {
    try {
      Auth auth = api.getAuth(authorization);
      Profile profile = dataSource.getByOwner(auth.getId());
      return new ResponseEntity<>(profile, HttpStatus.OK);
    } catch (Exception exc) {
      throw new Error(500, exc.getMessage());
    }
  }

  @GetMapping("/all")
  public ResponseEntity<List<Profile>> getAll() {
    try {
      List<Profile> profiles = dataSource.getAllProfiles();
      return new ResponseEntity<>(profiles, HttpStatus.OK);
    } catch (Exception exc) {
      throw new Error(500, exc.getMessage());
    }
  }
}
