package com.cafe.service.core.table;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UpdateTableParamsTest {

    @Test
    public void testWhenIdIsNull() {
        Assertions.assertThatThrownBy(() -> new UpdateTableParams(null, 1L))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testWhenWaiterIdIsNull() {
        Assertions.assertThatThrownBy(() -> new UpdateTableParams(1L, null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}