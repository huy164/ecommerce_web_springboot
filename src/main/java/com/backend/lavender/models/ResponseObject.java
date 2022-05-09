package com.backend.lavender.models;

import java.util.Objects;

import lombok.Data;

@Data
public class ResponseObject {

  private String status;
  private String message;
  private Object data;

  public ResponseObject(String status, String message, Object data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

 
}
