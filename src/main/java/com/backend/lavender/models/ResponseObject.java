package com.backend.lavender.models;

import java.util.Objects;


public class ResponseObject {
  
  private String status;
  private String message;
  private String body;


  public ResponseObject() {
  }

  public ResponseObject(String status, String message, String body) {
    this.status = status;
    this.message = message;
    this.body = body;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getBody() {
    return this.body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public ResponseObject status(String status) {
    setStatus(status);
    return this;
  }

  public ResponseObject message(String message) {
    setMessage(message);
    return this;
  }

  public ResponseObject body(String body) {
    setBody(body);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ResponseObject)) {
            return false;
        }
        ResponseObject responseObject = (ResponseObject) o;
        return Objects.equals(status, responseObject.status) && Objects.equals(message, responseObject.message) && Objects.equals(body, responseObject.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, message, body);
  }

  @Override
  public String toString() {
    return "{" +
      " status='" + getStatus() + "'" +
      ", message='" + getMessage() + "'" +
      ", body='" + getBody() + "'" +
      "}";
  }
 

}
