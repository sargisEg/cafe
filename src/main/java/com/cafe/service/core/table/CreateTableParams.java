package com.cafe.service.core.table;

import com.cafe.entity.User;
import org.springframework.util.Assert;

import java.util.Objects;

public class CreateTableParams {

    private final Long waiterId;

    public CreateTableParams(Long waiterId) {
        Assert.notNull(
                waiterId,
                "Class - CreateTableParams, method - constructor " +
                        "waiterId should not be null"
        );
        this.waiterId = waiterId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateTableParams{");
        sb.append("waiterId=").append(waiterId);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateTableParams)) return false;
        CreateTableParams that = (CreateTableParams) o;
        return Objects.equals(waiterId, that.waiterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waiterId);
    }

    public Long getWaiterId() {
        return waiterId;
    }
}
