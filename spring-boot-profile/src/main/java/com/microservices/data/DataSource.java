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

  public Profile update(final UUID owner, Profile p) {
    Profile profile = profiles
      .stream()
      .filter(x -> x.getOwner() == owner)
      .findFirst()
      .get();

    int index = profiles.indexOf(profile);

    if (p.getDob() != null) profile.setDob(p.getDob());

    if (p.getMaritalStatus() != null) profile.setMaritalStatus(
      p.getMaritalStatus()
    );

    Profile newProfile = profiles.set(index, profile);

    return newProfile;
  }

  public Integer delete(final UUID owner) {
    Profile profile = profiles
      .stream()
      .filter(x -> x.getOwner() == owner)
      .findFirst()
      .get();
    // int index = profiles.indexOf(profile);
    boolean isRemoved = profiles.remove(profile);
    return isRemoved ? 1 : 0;
  }
}
