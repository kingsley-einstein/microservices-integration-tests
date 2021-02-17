package com.microservices.data;

import com.microservices.models.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataSource {

  private List<Profile> profiles;

  public DataSource() {
    this.profiles = new ArrayList<>();
  }

  public Profile add(final Profile profile) {
    boolean isAdded = profiles.add(profile);

    return isAdded ? profile : null;
  }

  public Profile getByOwner(final UUID owner) {
    Profile profile = null;

    for (Profile p : profiles) if (p.getOwner() == owner) profile = p;

    return profile;
  }
}
