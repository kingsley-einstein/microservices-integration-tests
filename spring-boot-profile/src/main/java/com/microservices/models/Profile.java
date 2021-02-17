package com.microservices.models;

import com.microservices.models.types.MaritalStatus;
import java.util.Date;
import java.util.UUID;

// enum MaritalStatus {
//  SINGLE, MARRIED, DIVORCED, COMPLICATED
// }

@SuppressWarnings("serial")
public class Profile implements java.io.Serializable {

  private Date dob;
  private MaritalStatus maritalStatus;
  private UUID owner;

  public Profile() {}

  public Profile(
    final Date dob,
    final MaritalStatus maritalStatus,
    final UUID owner
  ) {
    this.dob = dob;
    this.maritalStatus = maritalStatus;
    this.owner = owner;
  }

  public void setDob(final Date dob) {
    this.dob = dob;
  }

  public Date getDob() {
    return dob;
  }

  public void setMaritalStatus(final MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public MaritalStatus getMaritalStatus() {
    return maritalStatus;
  }

  public UUID getOwner() {
    return owner;
  }
}
