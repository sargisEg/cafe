package com.cafe.service.core.user;

import com.cafe.entity.UserRole;
import com.cafe.entity.UserRoleTypes;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CreateUserParams {

    private final List<UserRoleTypes> userRoles;

    private final String username;

    private final String firstName;

    private final String secondName;

    private final LocalDate createdAt;

    public CreateUserParams(
            List<UserRoleTypes> userRoles,
            String username,
            String firstName,
            String secondName,
            LocalDate createdAt) {
        Assert.notNull(
                userRoles,
                "Class - CreateUserParams, method - constructor " +
                        "user roles should not be null"
        );
        Assert.notNull(
                createdAt,
                "Class - CreateUserParams, method - constructor " +
                        "createdAt date should not be null"
        );
        Assert.hasText(
                username,
                "Class - CreateUserParams, method - constructor " +
                        "username should not be null"
        );
        Assert.hasText(
                firstName,
                "Class - CreateUserParams, method - constructor " +
                        "first name should not be null"
        );
        Assert.hasText(
                secondName,
                "Class - CreateUserParams, method - constructor " +
                        "second name should not be null"
        );
        this.userRoles = userRoles;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateUserParams{");
        sb.append("userRoles=").append(userRoles);
        sb.append(", username='").append(username).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateUserParams)) return false;
        CreateUserParams that = (CreateUserParams) o;
        return userRoles == that.userRoles && Objects.equals(username, that.username) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoles, username, firstName, secondName, createdAt);
    }

    public List<UserRoleTypes> getUserRoles() {
        return userRoles;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
