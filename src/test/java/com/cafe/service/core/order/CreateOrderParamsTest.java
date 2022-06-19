package com.cafe.service.core.order;

import com.cafe.entity.OrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateOrderParamsTest {

    @Test
    public void testWhenTableIdIsNull() {
        Assertions.assertThatThrownBy(()->new CreateOrderParams(null, OrderStatus.CANCELLED))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenOrderStatusIsNull() {
        Assertions.assertThatThrownBy(()->new CreateOrderParams(1L, null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}