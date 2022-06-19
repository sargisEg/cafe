package com.cafe.facade.dto.entity;

import io.jsonwebtoken.lang.Assert;

import java.util.List;
import java.util.Objects;

public final class TableDto {

    private Long id;
    private UserDto waiterDto;

    private List<String> errors;

    public TableDto(List<String> errors) {
        Assert.notNull(
                errors,
                "Class - TableDto, method - constructor " +
                        "errors should not be null"
        );
        this.errors = errors;
    }

    public TableDto(Long id, UserDto waiterDto) {
        Assert.notNull(
                id,
                "Class - TableDto, method - constructor " +
                        "id should not be null"
        );
        Assert.notNull(
                waiterDto,
                "Class - TableDto, method - constructor " +
                        "waiterDto should not be null"
        );
        this.id = id;
        this.waiterDto = waiterDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableDto)) return false;
        TableDto tableDto = (TableDto) o;
        return Objects.equals(id, tableDto.id) && Objects.equals(waiterDto, tableDto.waiterDto) && Objects.equals(errors, tableDto.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, waiterDto, errors);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TableDto{");
        sb.append("id=").append(id);
        sb.append(", waiterDto=").append(waiterDto);
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getWaiterDto() {
        return waiterDto;
    }

    public void setWaiterDto(UserDto waiterDto) {
        this.waiterDto = waiterDto;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
