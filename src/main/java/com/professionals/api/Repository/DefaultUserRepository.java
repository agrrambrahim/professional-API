package com.professionals.api.Repository;

import com.professionals.api.domain.User;

import java.util.Optional;

public class DefaultUserRepository implements UserRepository {

    @Override
    public Optional<User> findByUsername(String username) {
        return DataSource.apiUsersList.values().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
}
