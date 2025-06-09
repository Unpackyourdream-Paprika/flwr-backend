package com.flwr.api.user.dto;

import java.time.LocalDate;

import com.flwr.api.user.domain.User.Gender;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignupRequest {

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String firstName;

  private String firstNameKana;

  @NotBlank
  private String lastName;

  private String lastNameKana;

  @NotBlank
  private String phone;

  @NotBlank
  private String postalCode;

  @NotBlank
  private String prefecture;

  @NotBlank
  private String address1;

  private String address2;

  @NotNull
  private LocalDate birthday;

  @NotNull
  private Gender gender;

  @AssertTrue
  private boolean acceptTerms;
}
