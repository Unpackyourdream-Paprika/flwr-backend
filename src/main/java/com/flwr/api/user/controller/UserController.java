package com.flwr.api.user.controller;

import com.flwr.api.common.dto.ApiResponse;
import com.flwr.api.user.domain.User;
import com.flwr.api.user.dto.UserResponse;
import com.flwr.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/me")
  public ResponseEntity<ApiResponse<UserResponse>> getMyInfo(Authentication authentication) {
    User user = (User) authentication.getPrincipal();

    return ResponseEntity.ok(new ApiResponse<>(UserResponse.of(user)));
  }

  @GetMapping("/search")
  public ResponseEntity<ApiResponse<List<UserResponse>>> searchUserByFirstName(@RequestParam(required = false) String firstName) {

    List<User> users = userService.searchUsersByFirstName(firstName);
    List<UserResponse> responseList = users.stream().map(UserResponse::of).toList();
    return ResponseEntity.ok(new ApiResponse<>(responseList));
  }

  @GetMapping("/all")
  public ResponseEntity<ApiResponse<Page<UserResponse>>> getAllUsers(@PageableDefault(size = 10) Pageable pageable) {
    // http://localhost:8080/api/users/all?page=3&size=1&sort=asc
    return ResponseEntity.ok(new ApiResponse<>(userService.getAllUsers(pageable).map((UserResponse::of))));
  }
}
