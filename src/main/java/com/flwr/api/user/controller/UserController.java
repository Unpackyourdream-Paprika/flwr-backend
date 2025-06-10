package com.flwr.api.user.controller;

import com.flwr.api.user.domain.User;
import com.flwr.api.user.dto.UserResponse;
import com.flwr.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public ResponseEntity<UserResponse> getMyInfo(Authentication authentication) {
    User user = (User) authentication.getPrincipal();

    return ResponseEntity.ok(UserResponse.of(user));
  }
}
