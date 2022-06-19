package com.cafe.service.core.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateProductParamsTest {

    @Test
    public void testWhenNameIsNull() {
        Assertions.assertThatThrownBy(() -> new CreateProductParams(null, 12L))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenNameIsEmpty() {
        Assertions.assertThatThrownBy(() -> new CreateProductParams("", 12L))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenAmountIsNull() {
        Assertions.assertThatThrownBy(() -> new CreateProductParams("name", null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}