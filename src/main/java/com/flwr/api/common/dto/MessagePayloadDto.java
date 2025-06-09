package com.flwr.api.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MessagePayloadDto {
  private String name;
  private int age;

  public MessagePayloadDto(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
