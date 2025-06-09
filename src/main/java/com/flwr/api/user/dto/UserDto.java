package com.flwr.api.user.dto;

import lombok.Getter;

@Getter
public class UserDto {
  private String name;
  private int age;

  public UserDto(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
