package com.cafe.service.core.product;

import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public class CreateProductParams {

    private final String name;
    private final Long amount;

    public CreateProductParams(String name, Long amount) {
        Assert.hasText(
                name,
                "Class - CreateProductParams, method - constructor " +
                        "name should not be null or empty"
        );
        Assert.notNull(
                amount,
                "Class - CreateProductParams, method - constructor " +
                        "amount should not be null"
        );
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateProductParams{");
        sb.append("name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateProductParams)) return false;
        CreateProductParams that = (CreateProductParams) o;
        return Objects.equals(name, that.name) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }

    public Long getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
