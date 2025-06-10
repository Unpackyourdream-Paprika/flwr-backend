package com.flwr.api.user.service;

import com.flwr.api.user.domain.User;
import com.flwr.api.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUserInfoById(Long userId) {
    return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not exist"));
  }

  public User getUserInfoByEmail(String email) {
    return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not exist"));
  }

  public List<User> searchUsersByFirstName(String firstName){
    return userRepository.searchUsersByFirstName(firstName);
  }

  public Page<User> getAllUsers(Pageable pageable){
    return userRepository.findAllUsersOrderByIdAsc(pageable);
  }
}
