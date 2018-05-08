package edu.jam.telephony.model.entity;


public class TechSupportUser {

  private long techSupportUserId;
  private String firstName;
  private String lastName;
  private long phoneNumber;

  public TechSupportUser(long techSupportUserId, String firstName, String lastName, long phoneNumber) {
    this.techSupportUserId = techSupportUserId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
  }

  public TechSupportUser() {

  }

  public long getTechSupportUserId() {
    return techSupportUserId;
  }

  public void setTechSupportUserId(long techSupportUserId) {
    this.techSupportUserId = techSupportUserId;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

}
