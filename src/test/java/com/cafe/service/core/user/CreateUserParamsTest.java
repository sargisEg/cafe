package com.cafe.service.core.user;

import com.cafe.entity.UserRoleTypes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CreateUserParamsTest {

    @Test
    public void testWhenUserTypeIsNull() {
        Assertions.assertThatThrownBy(
                () -> new CreateUserParams(
                        null,
                        "username",
                        "name",
                        "secondName",
                        LocalDate.now()
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenLocalDateIsNull() {
        Assertions.assertThatThrownBy(
                () -> new CreateUserParams(
                        UserRoleTypes.ROLE_MANAGER,
                        "username",
                        "name",
                        "secondName",
                        null
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenUsernameIsNull() {
        Assertions.assertThatThrownBy(
                () -> new CreateUserParams(
                        UserRoleTypes.ROLE_MANAGER,
                        null,
                        "name",
                        "secondName",
                        LocalDate.now()
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenUsernameIsEmpty() {
        Assertions.assertThatThrownBy(
                () -> new CreateUserParams(
                        UserRoleTypes.ROLE_MANAGER,
                        "",
                        "name",
                        "secondName",
                        LocalDate.now()
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenFirstNameIsNull() {
        Assertions.assertThatThrownBy(
                () -> new CreateUserParams(
                        UserRoleTypes.ROLE_MANAGER,
                        "username",
                        null,
                        "secondName",
                        LocalDate.now()
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenFirstNameIsEmpty() {
        Assertions.assertThatThrownBy(
                () -> new CreateUserParams(
                        UserRoleTypes.ROLE_MANAGER,
                        "username",
                        "",
                        "secondName",
                        LocalDate.now()
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenSecondNameIsNull() {
        Assertions.assertThatThrownBy(
                () -> new CreateUserParams(
                        UserRoleTypes.ROLE_MANAGER,
                        "username",
                        "firstName",
                        null,
                        LocalDate.now()
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenSecondNameIsEmpty() {
        Assertions.assertThatThrownBy(
                () -> new CreateUserParams(
                        UserRoleTypes.ROLE_MANAGER,
                        "username",
                        "firstName",
                        "",
                        LocalDate.now()
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

}