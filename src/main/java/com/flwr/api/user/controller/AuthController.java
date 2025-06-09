package com.flwr.api.user.controller;

import com.flwr.api.common.dto.ApiResponse;
import com.flwr.api.user.dto.LoginRequest;
import com.flwr.api.user.dto.SignupRequest;
import com.flwr.api.user.dto.LoginResponse;
import com.flwr.api.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<LoginResponse>> signup(@RequestBody @Valid SignupRequest request) {
    LoginResponse response = authService.signup(request);
    return ResponseEntity.ok(new ApiResponse<>(response));
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request) {
    LoginResponse response = authService.login(request);
    return ResponseEntity.ok(new ApiResponse<>(response));
  }

  @GetMapping("/test")
  public void test(){
    System.out.println("TEST");
  }
}