package com.cafe.facade.dto;

import org.springframework.util.Assert;

import java.util.Objects;

public final class CreateTableRequestDto {

    private String waiterUsername;

    public CreateTableRequestDto() {
    }

    public CreateTableRequestDto(String waiterUsername) {
        Assert.notNull(
                waiterUsername,
                "Class - CreateTableRequestDto, method - constructor"
        );
        this.waiterUsername = waiterUsername;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateTableRequestDto{");
        sb.append("waiterUsername=").append(waiterUsername);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateTableRequestDto)) return false;
        CreateTableRequestDto that = (CreateTableRequestDto) o;
        return Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waiterUsername);
    }

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        this.waiterUsername = waiterUsername;
    }
}