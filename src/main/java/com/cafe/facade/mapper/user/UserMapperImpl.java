package com.cafe.facade.mapper.user;

import com.cafe.entity.User;
import com.cafe.entity.UserRole;
import com.cafe.facade.dto.entity.UserDto;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapperImpl.class);

    @Override
    public UserDto map(User user) {
        Assert.notNull(
                user,
                "Class - UserMapperImpl, method - map(User user) " +
                        "user should not be null"
        );
        LOGGER.debug("Mapping user - {}", user);

        final UserDto userDto = new UserDto(
                user.getId(),
                user.getUserRoles().stream()
                        .map(UserRole::getType)
                        .collect(Collectors.toList()),
                user.getUsername(),
                user.getFirstName(),
                user.getSecondName(),
                user.getCreatedAt()
        );

        LOGGER.debug("Successfully mapped user - {}, result - {}", user, userDto);
        return userDto;
    }
}
