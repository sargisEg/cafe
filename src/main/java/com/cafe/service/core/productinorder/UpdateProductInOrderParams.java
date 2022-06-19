package com.cafe.service.core.productinorder;

import com.cafe.entity.ProductInOrderStatus;
import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public class UpdateProductInOrderParams {

    private final Long id;
    private final Long orderId;
    private final Long productId;
    private final Long amount;
    private final ProductInOrderStatus productInOrderStatus;

    public UpdateProductInOrderParams(Long id, Long orderId, Long productId, Long amount, ProductInOrderStatus productInOrderStatus) {
        Assert.notNull(
                id,
                "Class - UpdateProductInOrderParams, method - constructor " +
                        "id should not be null or empty"
        );
        Assert.notNull(
                orderId,
                "Class - UpdateProductInOrderParams, method - constructor " +
                        "orderId should not be null or empty"
        );
        Assert.notNull(
                productId,
                "Class - UpdateProductInOrderParams, method - constructor " +
                        "productId should not be null or empty"
        );
        Assert.notNull(
                amount,
                "Class - UpdateProductInOrderParams, method - constructor " +
                        "amount should not be null or empty"
        );
        Assert.notNull(
                productInOrderStatus,
                "Class - UpdateProductInOrderParams, method - constructor " +
                        "productInOrderStatus should not be null or empty"
        );
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
        this.productInOrderStatus = productInOrderStatus;
    }

    public Long getId() {
        return id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateProductInOrderParams)) return false;
        UpdateProductInOrderParams that = (UpdateProductInOrderParams) o;
        return Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(amount, that.amount) && productInOrderStatus == that.productInOrderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, productId, amount, productInOrderStatus);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateProductInOrderParams{");
        sb.append("id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", productId=").append(productId);
        sb.append(", amount=").append(amount);
        sb.append(", productInOrderStatus=").append(productInOrderStatus);
        sb.append('}');
        return sb.toString();
    }

}
