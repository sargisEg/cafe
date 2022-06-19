package com.cafe.facade.dto;

import com.cafe.entity.UserRole;
import com.cafe.entity.UserRoleTypes;
import io.jsonwebtoken.lang.Assert;

import java.util.List;
import java.util.Objects;

public final class CreateUserRequestDto {

    private String firstName;
    private String secondName;
    private List<UserRoleTypes> userRoles;

    public CreateUserRequestDto() {
    }

    public CreateUserRequestDto(String firstName, String secondName, List<UserRoleTypes> userRoles) {
        Assert.hasText(
                firstName,
                "Class - CreateUserRequestDto, method - constructor " +
                        "first name should not be null or empty"
        );
        Assert.hasText(
                secondName,
                "Class - CreateUserRequestDto, method - constructor " +
                        "second name should not be null or empty"
        );
        Assert.notNull(
                userRoles,
                "Class - CreateUserRequestDto, method - constructor " +
                        "user roles should not be null"
        );
        this.firstName = firstName;
        this.secondName = secondName;
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateUserRequestDto{");
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", userRoles=").append(userRoles);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserRequestDto)) return false;
        CreateUserRequestDto that = (CreateUserRequestDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && userRoles == that.userRoles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, userRoles);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public List<UserRoleTypes> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleTypes> userRoles) {
        this.userRoles = userRoles;
    }
}
