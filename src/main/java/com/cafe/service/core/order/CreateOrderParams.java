package com.cafe.service.core.order;

import com.cafe.entity.OrderStatus;
import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public class CreateOrderParams {

    private final Long tableId;
    private final OrderStatus orderStatus;

    public CreateOrderParams(Long tableId, OrderStatus orderStatus) {
        Assert.notNull(
                tableId,
                "Class - CreateOrderParams, method - constructor " +
                        "tableId should not be null"
        );
        Assert.notNull(
                orderStatus,
                "Class - CreateOrderParams, method - constructor " +
                        "orderStatus should not be null"
        );
        this.tableId = tableId;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateOrderParams{");
        sb.append("tableId=").append(tableId);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateOrderParams)) return false;
        CreateOrderParams that = (CreateOrderParams) o;
        return Objects.equals(tableId, that.tableId) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, orderStatus);
    }

    public Long getTableId() {
        return tableId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
