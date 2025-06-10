package com.flwr.api.global.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NumberUtil {
  public static boolean isNumeric(String str) {
    if (str == null || str.isEmpty()) return false;
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
//      throw new RuntimeException(e);
      return false;
    }
  }
}
