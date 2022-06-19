package com.cafe.facade.dto;

import org.springframework.util.Assert;

import java.util.Objects;

public final class CreateProductInOrderRequestDto {
    private Long orderId;
    private String productName;
    private Long productAmount;

    public CreateProductInOrderRequestDto() {
    }

    public CreateProductInOrderRequestDto(Long orderId, String productName, Long productAmount) {
        Assert.hasText(
                productName,
                "Class - CreateProductInOrderRequestDto, method - constructor " +
                        "productName should not be null or empty"
        );
        Assert.notNull(
                orderId,
                "Class - CreateProductInOrderRequestDto, method - constructor " +
                        "orderId should not be null"
        );
        Assert.notNull(
                productAmount,
                "Class - CreateProductInOrderRequestDto, method - constructor " +
                        "productAmount should not be null"
        );
        this.orderId = orderId;
        this.productName = productName;
        this.productAmount = productAmount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateProductInOrderRequestDto{");
        sb.append("orderId=").append(orderId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productAmount=").append(productAmount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateProductInOrderRequestDto)) return false;
        CreateProductInOrderRequestDto that = (CreateProductInOrderRequestDto) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productName, that.productName) && Objects.equals(productAmount, that.productAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productName, productAmount);
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Long productAmount) {
        this.productAmount = productAmount;
    }
}
