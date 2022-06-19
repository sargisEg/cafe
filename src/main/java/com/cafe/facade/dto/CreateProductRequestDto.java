package com.cafe.facade.dto;

import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public final class CreateProductRequestDto {

    private String name;
    private Long amount;

    public CreateProductRequestDto() {
    }

    public CreateProductRequestDto(String name, Long amount) {
        Assert.notNull(
                amount,
                "Class - CreateProductRequestDto, method - constructor " +
                        "amount should not be null"
        );
        Assert.hasText(
                name,
                "Class - CreateProductRequestDto, method - constructor " +
                        "name should not be null"
        );
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateProductRequestDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateProductRequestDto)) return false;
        CreateProductRequestDto that = (CreateProductRequestDto) o;
        return Objects.equals(name, that.name) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
