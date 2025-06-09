package com.flwr.api.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flwr.api.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  // @Modifying
  // @Query("UPDATE User u SET u.email = :email WHERE u.id = :id") // User는 클래스 객체명
  // int updateUserAgeByNameFromJpql(@Param("email") String email, @Param("id") int id);
}