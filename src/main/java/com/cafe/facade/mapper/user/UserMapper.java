package com.cafe.facade.mapper.user;

import com.cafe.entity.User;
import com.cafe.facade.dto.entity.UserDto;

public interface UserMapper {

    UserDto map(User user);
}
