package com.cafe.service.impl;

import com.cafe.entity.User;
import com.cafe.entity.UserRole;
import com.cafe.entity.UserRoleTypes;
import com.cafe.repository.UserRepository;
import com.cafe.service.core.user.CreateUserParams;
import com.cafe.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User create(CreateUserParams params) {
        Assert.notNull(
                params,
                "Class - UserServiceImpl, method create(CreateUserParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Creating user with params - {}", params);

        final User userFromParams = new User(
                params.getUsername(),
                params.getFirstName(),
                params.getSecondName(),
                params.getCreatedAt()
        );

        final List<UserRoleTypes> userRoleTypes = params.getUserRoles();
        final List<UserRole> userRoles = new LinkedList<>();

        userRoleTypes.forEach(userRoleType -> {
            userRoles.add(new UserRole(userFromParams, userRoleType));
        });

        userFromParams.setUserRoles(userRoles);

        final User user = userRepository.save(userFromParams);

        LOGGER.info("Successfully created user with params - {}, result - {}", params, user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        Assert.notNull(
                id,
                "Class - UserServiceImpl, method findById(Long id) " +
                        "id should not be null"
        );
        LOGGER.info("Finding user with id - {}", id);

        final Optional<User> optionalUser = userRepository.findById(id);

        LOGGER.info("Successfully found user with id - {}, result - {}", id, optionalUser);
        return optionalUser;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        Assert.notNull(
                username,
                "Class - UserServiceImpl, method findByUsername(String username) " +
                        "username should not be null"
        );
        LOGGER.info("Finding user with username - {}", username);

        final Optional<User> optionalUser = userRepository.findByUsername(username);

        LOGGER.info("Successfully found user with username - {}, result - {}", username, optionalUser);
        return optionalUser;
    }
}
