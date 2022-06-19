package com.cafe.service.core.order;

import com.cafe.entity.OrderStatus;
import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public class UpdateOrderParams {

    private final Long id;
    private final Long tableId;
    private final OrderStatus orderStatus;

    public UpdateOrderParams(Long id, Long tableId, OrderStatus orderStatus) {
        Assert.notNull(
                id,
                "Class - UpdateOrderParams, method - constructor " +
                        "id should not be null"
        );
        Assert.notNull(
                tableId,
                "Class - UpdateOrderParams, method - constructor " +
                        "tableId should not be null"
        );
        Assert.notNull(
                orderStatus,
                "Class - UpdateOrderParams, method - constructor " +
                        "orderStatus should not be null"
        );
        this.id = id;
        this.tableId = tableId;
        this.orderStatus = orderStatus;
    }

    public Long getId() {
        return id;
    }

    public Long getTableId() {
        return tableId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateOrderParams{");
        sb.append("id=").append(id);
        sb.append(", tableId=").append(tableId);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateOrderParams)) return false;
        UpdateOrderParams that = (UpdateOrderParams) o;
        return Objects.equals(id, that.id) && Objects.equals(tableId, that.tableId) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableId, orderStatus);
    }
}
