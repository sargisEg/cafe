package com.cafe.facade.dto.entity;

import com.cafe.entity.UserRole;
import com.cafe.entity.UserRoleTypes;
import io.jsonwebtoken.lang.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public final class UserDto {

    private Long id;
    private List<UserRoleTypes> userRoles;
    private String username;
    private String firstName;
    private String secondName;
    private LocalDate createdAt;

    private List<String> errors;

    public UserDto(List<String> errors) {
        Assert.notNull(
                errors,
                "Class - UserDto, method - constructor " +
                        "errors should not be null"
        );
        this.errors = errors;
    }

    public UserDto(Long id, List<UserRoleTypes> userRoles, String username, String firstName, String secondName, LocalDate createdAt) {
        Assert.notNull(
                id,
                "Class - UserDto, method - constructor " +
                        "id should not be null"
        );
        Assert.notNull(
                userRoles,
                "Class - UserDto, method - constructor " +
                        "user roles should not be null"
        );
        Assert.hasText(
                username,
                "Class - UserDto, method - constructor " +
                        "username should not be null or empty"
        );
        Assert.hasText(
                firstName,
                "Class - UserDto, method - constructor " +
                        "first name should not be null or empty"
        );
        Assert.hasText(
                secondName,
                "Class - UserDto, method - constructor " +
                        "second name should not be null or empty"
        );
        Assert.notNull(
                createdAt,
                "Class - UserDto, method - constructor " +
                        "creation date should not be null"
        );
        this.id = id;
        this.userRoles = userRoles;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserDto{");
        sb.append("id=").append(id);
        sb.append(", userRoles=").append(userRoles);
        sb.append(", username='").append(username).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && userRoles == userDto.userRoles && Objects.equals(username, userDto.username) && Objects.equals(firstName, userDto.firstName) && Objects.equals(secondName, userDto.secondName) && Objects.equals(createdAt, userDto.createdAt) && Objects.equals(errors, userDto.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userRoles, username, firstName, secondName, createdAt, errors);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserRoleTypes> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleTypes> userRoles) {
        this.userRoles = userRoles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
