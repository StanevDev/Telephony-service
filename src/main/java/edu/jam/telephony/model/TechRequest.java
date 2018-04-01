package edu.jam.telephony.model;


public class TechRequest {

  private long techRequest;
  private String problemDescription;
  private long dateId;
  private long subscriberId;
  private long techSupportUserId;


  public long getTechRequest() {
    return techRequest;
  }

  public void setTechRequest(long techRequest) {
    this.techRequest = techRequest;
  }


  public String getProblemDescription() {
    return problemDescription;
  }

  public void setProblemDescription(String problemDescription) {
    this.problemDescription = problemDescription;
  }


  public long getDateId() {
    return dateId;
  }

  public void setDateId(long dateId) {
    this.dateId = dateId;
  }


  public long getSubscriberId() {
    return subscriberId;
  }

  public void setSubscriberId(long subscriberId) {
    this.subscriberId = subscriberId;
  }


  public long getTechSupportUserId() {
    return techSupportUserId;
  }

  public void setTechSupportUserId(long techSupportUserId) {
    this.techSupportUserId = techSupportUserId;
  }

}
