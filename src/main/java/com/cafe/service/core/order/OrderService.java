package com.cafe.service.core.order;

import com.cafe.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order create(CreateOrderParams params);

    Order update(UpdateOrderParams params);
    Optional<Order> findById(Long id);

    List<Order> findByTableId(Long id);
}
