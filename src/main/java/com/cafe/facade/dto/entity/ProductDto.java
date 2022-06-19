package com.cafe.facade.dto.entity;

import io.jsonwebtoken.lang.Assert;

import java.util.List;
import java.util.Objects;

public final class ProductDto {

    private Long id;
    private String name;
    private Long amount;

    private List<String> errors;

    public ProductDto(List<String> errors) {
        Assert.notNull(
                errors,
                "Class - ProductDto, method - constructor " +
                        "errors should not be null"
        );
        this.errors = errors;
    }

    public ProductDto(Long id, String name, Long amount) {
        Assert.notNull(
                id,
                "Class - ProductDto, method - constructor " +
                        "id should not be null"
        );
        Assert.notNull(
                amount,
                "Class - ProductDto, method - constructor " +
                        "amount should not be null"
        );
        Assert.hasText(
                name,
                "Class - ProductDto, method - constructor " +
                        "name should not be null or empty"
        );
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductDto{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDto)) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(amount, that.amount) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, errors);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
