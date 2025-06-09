package com.flwr.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
  private boolean success = true;
  private T data;

  public ApiResponse(T data) {
    this.data = data;
  }
}
