package com.cafe.facade.dto.entity;

import com.cafe.entity.OrderStatus;
import io.jsonwebtoken.lang.Assert;

import java.util.List;
import java.util.Objects;

public final class OrderDto {

    private Long id;
    private TableDto tableDto;
    private OrderStatus orderStatus;

    private List<String> errors;

    public OrderDto(List<String> errors) {
        Assert.notNull(
                errors,
                "Class - OrderDto, method - constructor " +
                        "errors should not be null"
        );
        this.errors = errors;
    }

    public OrderDto(Long id, TableDto tableDto, OrderStatus orderStatus) {
        Assert.notNull(
                id,
                "Class - OrderDto, method - constructor " +
                        "id should not be null"
        );
        Assert.notNull(
                tableDto,
                "Class - OrderDto, method - constructor " +
                        "tableDto should not be null"
        );
        Assert.notNull(
                orderStatus,
                "Class - OrderDto, method - constructor " +
                        "order status should not be null"
        );
        this.id = id;
        this.tableDto = tableDto;
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(tableDto, orderDto.tableDto) && orderStatus == orderDto.orderStatus && Objects.equals(errors, orderDto.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableDto, orderStatus, errors);
    }

    @Override
    public String
    toString() {
        final StringBuilder sb = new StringBuilder("OrderDto{");
        sb.append("id=").append(id);
        sb.append(", tableDto=").append(tableDto);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TableDto getTableDto() {
        return tableDto;
    }

    public void setTableDto(TableDto tableDto) {
        this.tableDto = tableDto;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
