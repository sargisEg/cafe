package com.cafe.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@javax.persistence.Table(name = "TABLES")
public class Table {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TABLE_ID_SEQUENCE"
    )
    @SequenceGenerator(name = "TABLE_ID_SEQUENCE")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "waiter_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_TABLES_WAITER_ID_ID")
    )
    private User waiter;

    public Table() {
    }

    public Table(User waiter) {
        this.waiter = waiter;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Table{");
        sb.append("id=").append(id);
        sb.append(", waiter=").append(waiter);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id) && Objects.equals(waiter, table.waiter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, waiter);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWaiter() {
        return waiter;
    }

    public void setWaiter(User waiter) {
        this.waiter = waiter;
    }
}
