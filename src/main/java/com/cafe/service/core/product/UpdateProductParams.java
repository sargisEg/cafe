package com.cafe.service.core.product;

import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public class UpdateProductParams {

    private final Long id;
    private final String name;
    private final Long amount;

    public UpdateProductParams(Long id, String name, Long amount) {
        Assert.notNull(
                id,
                "Class - UpdateProductParams, method - constructor " +
                        "id should not be null or empty"
        );
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
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateProductParams{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateProductParams)) return false;
        UpdateProductParams that = (UpdateProductParams) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount);
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
