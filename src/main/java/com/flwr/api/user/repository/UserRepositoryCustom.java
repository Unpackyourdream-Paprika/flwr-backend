package com.flwr.api.user.repository;

import com.flwr.api.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {
   List<User> searchUsersByFirstName(String firstName);
   Page<User> findAllUsersOrderByIdAsc(Pageable pageable);
}
