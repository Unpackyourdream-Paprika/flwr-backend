package com.flwr.api.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flwr.api.user.dto.LoginResponse;

import org.springframework.stereotype.Service;

import com.flwr.api.global.jwt.JwtProvider;
import com.flwr.api.user.domain.User;
import com.flwr.api.user.dto.LoginRequest;
import com.flwr.api.user.dto.SignupRequest;
import com.flwr.api.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;

  public LoginResponse signup(SignupRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("Email is Already exist.");
    }

    String encodedPassword = passwordEncoder.encode(request.getPassword());

    System.out.println("PWD: " + encodedPassword);

    User user = User.builder()
        .email(request.getEmail())
        .password(encodedPassword)
        .firstName(request.getFirstName())
        .firstNameKana(request.getFirstNameKana())
        .lastName(request.getLastName())
        .lastNameKana(request.getLastNameKana())
        .phone(request.getPhone())
        .postalCode(request.getPostalCode())
        .prefecture(request.getPrefecture())
        .address1(request.getAddress1())
        .address2(request.getAddress2())
        .birthday(request.getBirthday())
        .gender(request.getGender())
        .acceptTerms(request.isAcceptTerms())
        .role(User.UserRole.USER)
        .build();

    userRepository.save(user);
    System.out.println("USER: " + user);

    String accessToken = jwtProvider.createToken(user.getId().toString());

    return new LoginResponse(user.getId(), accessToken);
  }

  public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Not exist user"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new IllegalArgumentException("Password not correct");
    }

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    String userJson = null;
    try {
      userJson = objectMapper.writeValueAsString(user);
      System.out.println("user: " + userJson);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    String accessToken = jwtProvider.createToken(user.getId().toString());

    return new LoginResponse(user.getId(), accessToken);
  }
}
