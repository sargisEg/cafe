package com.cafe.entity;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PRODUCTS_ID_SEQUENCE"
    )
    @SequenceGenerator(name = "PRODUCTS_ID_SEQUENCE")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 80)
    private String name;

    @Column(name = "amount", nullable = false)
    private Long amount;

    public Product() {
    }

    public Product(String name, Long amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(amount, product.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
