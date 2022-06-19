package com.cafe.service.core.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UpdateProductParamsTest {

    @Test
    public void testWhenIdIsNull() {
        Assertions.assertThatThrownBy(() -> new UpdateProductParams(null, "name", 12L))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenNameIsNull() {
        Assertions.assertThatThrownBy(() -> new UpdateProductParams(1L, null, 12L))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenNameIsEmpty() {
        Assertions.assertThatThrownBy(() -> new UpdateProductParams(1L, "", 12L))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenAmountIsNull() {
        Assertions.assertThatThrownBy(() -> new UpdateProductParams(1L, "name", null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}