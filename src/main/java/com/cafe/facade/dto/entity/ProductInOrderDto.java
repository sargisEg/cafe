package com.cafe.facade.dto.entity;

import com.cafe.entity.ProductInOrderStatus;
import io.jsonwebtoken.lang.Assert;

import java.util.List;
import java.util.Objects;

public final class ProductInOrderDto {

    private Long id;
    private OrderDto orderDto;
    private ProductDto productDto;
    private Long amount;
    private ProductInOrderStatus productInOrderStatus;

    private List<String> errors;

    public ProductInOrderDto(List<String> errors) {
        Assert.notNull(
                errors,
                "Class - ProductInOrderDto, method constructor " +
                        "errors should not be null"
        );
        this.errors = errors;
    }

    public ProductInOrderDto(
            Long id,
            OrderDto orderDto,
            ProductDto productDto,
            Long amount,
            ProductInOrderStatus productInOrderStatus) {
        Assert.notNull(
                id,
                "Class - ProductInOrderDto, method constructor " +
                        "id should not be null"
        );
        Assert.notNull(
                orderDto,
                "Class - ProductInOrderDto, method constructor " +
                        "orderDto should not be null"
        );
        Assert.notNull(
                productDto,
                "Class - ProductInOrderDto, method constructor " +
                        "productDto should not be null"
        );
        Assert.notNull(
                amount,
                "Class - ProductInOrderDto, method constructor " +
                        "amount should not be null"
        );
        Assert.notNull(
                productInOrderStatus,
                "Class - ProductInOrderDto, method constructor " +
                        "productInOrderStatus should not be null"
        );
        this.id = id;
        this.orderDto = orderDto;
        this.productDto = productDto;
        this.amount = amount;
        this.productInOrderStatus = productInOrderStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductInOrderDto{");
        sb.append("id=").append(id);
        sb.append(", orderDto=").append(orderDto);
        sb.append(", productDto=").append(productDto);
        sb.append(", amount=").append(amount);
        sb.append(", productInOrderStatus=").append(productInOrderStatus);
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInOrderDto)) return false;
        ProductInOrderDto that = (ProductInOrderDto) o;
        return Objects.equals(id, that.id) && Objects.equals(orderDto, that.orderDto) && Objects.equals(productDto, that.productDto) && Objects.equals(amount, that.amount) && productInOrderStatus == that.productInOrderStatus && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDto, productDto, amount, productInOrderStatus, errors);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ProductInOrderStatus getProductInOrderStatus() {
        return productInOrderStatus;
    }

    public void setProductInOrderStatus(ProductInOrderStatus productInOrderStatus) {
        this.productInOrderStatus = productInOrderStatus;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
