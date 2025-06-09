package com.flwr.api.user.repository;

public interface UserRepositoryCustom {
   long updateUserAgeByNameFromQueryDsl(String name, int age);
}
