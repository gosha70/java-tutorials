package com.egoge.examples.lazyfetch.controller;

public class DemoResponse {
  private String status;
  private Object message;

  public DemoResponse(
      String status,
      Object message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Object getMessage() {
    return message;
  }

  public void setMessage(Object message) {
    this.message = message;
  }
}
