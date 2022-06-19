package com.cafe.service.core.table;

import org.springframework.util.Assert;

import java.util.Objects;

public class UpdateTableParams {

    private final Long id;
    private final Long waiterId;

    public UpdateTableParams(Long id, Long waiterId) {
        Assert.notNull(
                id,
                "Class - UpdateTableParams, method - constructor " +
                        "id should not be null"
        );
        Assert.notNull(
                waiterId,
                "Class - UpdateTableParams, method - constructor " +
                        "waiterId should not be null"
        );
        this.id = id;
        this.waiterId = waiterId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UpdateTableParams{");
        sb.append("id=").append(id);
        sb.append(", waiterId=").append(waiterId);
        sb.append('}');
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateTableParams)) return false;
        UpdateTableParams that = (UpdateTableParams) o;
        return Objects.equals(id, that.id) && Objects.equals(waiterId, that.waiterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, waiterId);
    }

    public Long getId() {
        return id;
    }

    public Long getWaiterId() {
        return waiterId;
    }
}
