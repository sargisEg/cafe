package com.cafe.entity;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTS_IN_ORDERS")
public class ProductInOrder {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PRODUCT_IN_ORDER_ID_SEQUENCE"
    )
    @SequenceGenerator(name = "PRODUCT_IN_ORDER_ID_SEQUENCE")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PRODUCTS_IN_ORDER_ORDER_ID_ID")
    )
    private Order order;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PRODUCTS_IN_ORDER_PRODUCT_ID_ID")
    )
    private Product product;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_in_order_status")
    private ProductInOrderStatus productInOrderStatus;

    public ProductInOrder() {
    }

    public ProductInOrder(Order order, Product product, Long amount, ProductInOrderStatus productInOrderStatus) {
        this.order = order;
        this.product = product;
        this.amount = amount;
        this.productInOrderStatus = productInOrderStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductInOrder{");
        sb.append("id=").append(id);
        sb.append(", order=").append(order);
        sb.append(", product=").append(product);
        sb.append(", amount=").append(amount);
        sb.append(", productInOrderStatus=").append(productInOrderStatus);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInOrder)) return false;
        ProductInOrder that = (ProductInOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(product, that.product) && Objects.equals(amount, that.amount) && productInOrderStatus == that.productInOrderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, amount, productInOrderStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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
}
