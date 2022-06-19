package com.cafe.service.core.user;

import com.cafe.entity.User;

import java.util.Optional;

public interface UserService {

    User create(CreateUserParams params);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);
}
