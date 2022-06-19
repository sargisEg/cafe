package com.cafe.facade.dto;

import org.springframework.util.Assert;

import java.util.Objects;

public final class CreateOrderRequestDto {
    private Long tableId;

    public CreateOrderRequestDto() {
    }

    public CreateOrderRequestDto(Long tableId) {
        Assert.notNull(
                tableId,
                "Class - CreateOrderRequestDto, method - constructor " +
                        "tableId should not be null"
        );
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateOrderRequestDto{");
        sb.append(", tableId=").append(tableId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateOrderRequestDto)) return false;
        CreateOrderRequestDto that = (CreateOrderRequestDto) o;
        return Objects.equals(tableId, that.tableId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId);
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
