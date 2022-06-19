package com.cafe.service.core.order;

import com.cafe.entity.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UpdateOrderParamsTest {

    @Test
    public void testWhenIdIsNull() {
        Assertions.assertThatThrownBy(()->new UpdateOrderParams(null,1L, OrderStatus.CANCELLED))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenTableIdIsNull() {
        Assertions.assertThatThrownBy(()->new UpdateOrderParams(1L,null, OrderStatus.CANCELLED))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenOrderStatusIsNull() {
        Assertions.assertThatThrownBy(()->new UpdateOrderParams(1L, 1L, null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}