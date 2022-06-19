package com.cafe.service.core.table;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateTableParamsTest {

    @Test
    public void testWhenWaiterIsNull() {
        Assertions.assertThatThrownBy(() -> new CreateTableParams(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

}