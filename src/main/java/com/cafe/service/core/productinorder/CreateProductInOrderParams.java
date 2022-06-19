package com.cafe.service.core.productinorder;

import com.cafe.entity.ProductInOrderStatus;
import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public class CreateProductInOrderParams {

    private final Long orderId;
    private final Long productId;
    private final Long amount;
    private final ProductInOrderStatus productInOrderStatus;

    public CreateProductInOrderParams(Long orderId, Long productId, Long amount, ProductInOrderStatus productInOrderStatus) {
        Assert.notNull(
                orderId,
                "Class - CreateProductInOrderParams, method - constructor " +
                        "orderId should not be null or empty"
        );
        Assert.notNull(
                productId,
                "Class - CreateProductInOrderParams, method - constructor " +
                        "productId should not be null or empty"
        );
        Assert.notNull(
                amount,
                "Class - CreateProductInOrderParams, method - constructor " +
                        "amount should not be null or empty"
        );
        Assert.notNull(
                productInOrderStatus,
                "Class - CreateProductInOrderParams, method - constructor " +
                        "productInOrderStatus should not be null or empty"
        );
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
        this.productInOrderStatus = productInOrderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getAmount() {
        return amount;
    }

    public ProductInOrderStatus getProductInOrderStatus() {
        return productInOrderStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateProductInOrderParams{");
        sb.append("orderId=").append(orderId);
        sb.append(", productId=").append(productId);
        sb.append(", amount=").append(amount);
        sb.append(", productInOrderStatus=").append(productInOrderStatus);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateProductInOrderParams)) return false;
        CreateProductInOrderParams that = (CreateProductInOrderParams) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(amount, that.amount) && productInOrderStatus == that.productInOrderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, amount, productInOrderStatus);
    }
}
