package com.cafe.facade.mapper.order;

import com.cafe.entity.Order;
import com.cafe.facade.dto.entity.OrderDto;

public interface OrderMapper {

    OrderDto map(Order order);
}
