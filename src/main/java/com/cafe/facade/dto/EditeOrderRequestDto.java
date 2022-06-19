package com.cafe.facade.dto;

import com.cafe.entity.OrderStatus;
import org.springframework.util.Assert;

import java.util.Objects;

public final class EditeOrderRequestDto {

    private Long id;
    private OrderStatus orderStatus;

    public EditeOrderRequestDto() {
    }

    public EditeOrderRequestDto(Long id, OrderStatus orderStatus) {
        Assert.notNull(
                id,
                "Class - EditeOrderRequestDto, method - constructor " +
                        "id should not be null"
        );
        Assert.notNull(
                orderStatus,
                "Class - EditeOrderRequestDto, method - constructor " +
                        "orderStatus should not be null"
        );
        this.id = id;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EditeOrderRequestDto{");
        sb.append("id=").append(id);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EditeOrderRequestDto)) return false;
        EditeOrderRequestDto that = (EditeOrderRequestDto) o;
        return Objects.equals(id, that.id) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
