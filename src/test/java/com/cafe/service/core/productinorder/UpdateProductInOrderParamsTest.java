package com.cafe.service.core.productinorder;

import com.cafe.entity.ProductInOrderStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UpdateProductInOrderParamsTest {

    @Test
    public void testWhenIdIsNull() {
        Assertions.assertThatThrownBy(
                () -> new UpdateProductInOrderParams(
                        null,
                        1L,
                        1L,
                        12L,
                        ProductInOrderStatus.ACTIVE
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenOrderIdIsNull() {
        Assertions.assertThatThrownBy(
                () -> new UpdateProductInOrderParams(
                        1L,
                        null,
                        1L,
                        12L,
                        ProductInOrderStatus.ACTIVE
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenProductIdIsNull() {
        Assertions.assertThatThrownBy(
                () -> new UpdateProductInOrderParams(
                        1L,
                        1L,
                        null,
                        12L,
                        ProductInOrderStatus.ACTIVE
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenAmountIsNull() {
        Assertions.assertThatThrownBy(
                () -> new UpdateProductInOrderParams(
                        1L,
                        1L,
                        1L,
                        null,
                        ProductInOrderStatus.ACTIVE
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenProductInOrderStatusIsNull() {
        Assertions.assertThatThrownBy(
                () -> new UpdateProductInOrderParams(
                        1L,
                        1L,
                        1L,
                        12L,
                        null
                )
        ).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}