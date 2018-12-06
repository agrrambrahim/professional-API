package com.professionals.api.Repository;

import com.professionals.api.domain.User;

import java.util.Optional;

public interface UserRepository {


    Optional<User> findByUsername(String username);
}
