package com.cafe.entity;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ORDERS_ID_SEQUENCE"
    )
    @SequenceGenerator(name = "ORDERS_ID_SEQUENCE")
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "table_id",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "FK_ORDERS_TABLE_ID_ID")
    )
    private com.cafe.entity.Table table;

    @Enumerated(EnumType.STRING)
    @Column(name = "orderStatus", nullable = false, length = 10)
    private OrderStatus orderStatus;

    public Order() {
    }

    public Order(com.cafe.entity.Table table, OrderStatus orderStatus) {
        this.table = table;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", table=").append(table);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(table, order.table) && orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, table, orderStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.cafe.entity.Table getTable() {
        return table;
    }

    public void setTable(com.cafe.entity.Table table) {
        this.table = table;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
