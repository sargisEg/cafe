package com.cafe.facade.dto;

import com.cafe.entity.ProductInOrderStatus;
import org.springframework.util.Assert;

import java.util.Objects;

public final class EditeProductInOrderRequestDto {

    private Long orderId;
    private String productName;
    private Long productAmount;
    private ProductInOrderStatus productInOrderStatus;

    public EditeProductInOrderRequestDto() {
    }

    public EditeProductInOrderRequestDto(
            Long orderId,
            String productName,
            Long productAmount,
            ProductInOrderStatus productInOrderStatus) {
        Assert.hasText(
                productName,
                "Class - EditeProductInOrderRequestDto, method - constructor " +
                        "productName should not be null or empty"
        );
        Assert.notNull(
                orderId,
                "Class - EditeProductInOrderRequestDto, method - constructor " +
                        "order id should not be null"
        );
        Assert.notNull(
                productAmount,
                "Class - EditeProductInOrderRequestDto, method - constructor " +
                        "productAmount should not be null"
        );
        Assert.notNull(
                productInOrderStatus,
                "Class - EditeProductInOrderRequestDto, method - constructor " +
                        "productInOrderStatus should not be null"
        );
        this.orderId = orderId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productInOrderStatus = productInOrderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditeProductInOrderRequestDto)) return false;
        EditeProductInOrderRequestDto that = (EditeProductInOrderRequestDto) o;
        return Objects.equals(orderId, that.orderId)
                && Objects.equals(productName, that.productName)
                && Objects.equals(productAmount, that.productAmount)
                && productInOrderStatus == that.productInOrderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productName, productAmount, productInOrderStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EditeProductInOrderRequestDto{");
        sb.append("orderId=").append(orderId);
        sb.append(", productName='").append(productName).append('\'');
        sb.append(", productAmount=").append(productAmount);
        sb.append(", productInOrderStatus=").append(productInOrderStatus);
        sb.append('}');
        return sb.toString();
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

    public ProductInOrderStatus getProductInOrderStatus() {
        return productInOrderStatus;
    }

    public void setProductInOrderStatus(ProductInOrderStatus productInOrderStatus) {
        this.productInOrderStatus = productInOrderStatus;
    }
}
