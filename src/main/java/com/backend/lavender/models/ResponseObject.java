package com.backend.lavender.models;

import java.util.Objects;

import lombok.Data;

@Data
public class ResponseObject {

  private String status;
  private String message;
  private Object data;

  public ResponseObject() {
  }

  public ResponseObject(String status, String message, Object data) {
    this.status = status;
    this.message = message;
    this.data = data;
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

  public Object getData() {
    return this.data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public ResponseObject status(String status) {
    setStatus(status);
    return this;
  }

  public ResponseObject message(String message) {
    setMessage(message);
    return this;
  }

  public ResponseObject data(Object data) {
    setData(data);
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
        return Objects.equals(status, responseObject.status) && Objects.equals(message, responseObject.message) && Objects.equals(data, responseObject.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, message, data);
  }

  @Override
  public String toString() {
    return "{" +
      " status='" + getStatus() + "'" +
      ", message='" + getMessage() + "'" +
      ", data='" + getData() + "'" +
      "}";
  }

}
