package com.cafe.facade.dto;

import io.jsonwebtoken.lang.Assert;

import java.util.Objects;

public final class AssignTableRequestDto {

    private Long tableId;
    private String waiterUsername;

    public AssignTableRequestDto() {
    }

    public AssignTableRequestDto(Long tableId, String waiterUsername) {
        Assert.hasText(
                waiterUsername,
                "Class - AssignTableRequestDto, method - constructor " +
                        "waiter username should not be null"
        );
        Assert.notNull(
                tableId,
                "Class - AssignTableRequestDto, method - constructor " +
                        "table id should not be null"
        );
        this.tableId = tableId;
        this.waiterUsername = waiterUsername;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AssignTableRequestDto{");
        sb.append("tableId=").append(tableId);
        sb.append(", waiterUsername=").append(waiterUsername);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignTableRequestDto)) return false;
        AssignTableRequestDto that = (AssignTableRequestDto) o;
        return Objects.equals(tableId, that.tableId) && Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, waiterUsername);
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        this.waiterUsername = waiterUsername;
    }
}
