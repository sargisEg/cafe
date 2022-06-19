package com.cafe.service.impl;

import com.cafe.entity.Order;
import com.cafe.entity.Table;
import com.cafe.repository.OrderRepository;
import com.cafe.service.core.order.CreateOrderParams;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.order.UpdateOrderParams;
import com.cafe.service.core.table.TableService;
import com.cafe.service.impl.exceptions.OrderNotFoundException;
import com.cafe.service.impl.exceptions.TableNotFoundException;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final TableService tableService;

    public OrderServiceImpl(OrderRepository orderRepository, TableService tableService) {
        this.orderRepository = orderRepository;
        this.tableService = tableService;
    }

    @Override
    @Transactional
    public Order create(CreateOrderParams params) {
        Assert.notNull(
                params,
                "Class - OrderServiceImpl, method - create(CreateOrderParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Creating order with params - {}", params);

        final Table table = tableService.findById(params.getTableId())
                .orElseThrow(() -> {
                    throw new TableNotFoundException(params.getTableId());
                });

        final Order orderFromParams = new Order(table, params.getOrderStatus());

        final Order order = orderRepository.save(orderFromParams);

        LOGGER.info("Successfully created order with params - {}, result - {}", params, order);
        return order;
    }

    @Override
    @Transactional
    public Order update(UpdateOrderParams params) {
        Assert.notNull(
                params,
                "Class - OrderServiceImpl, method - update(UpdateOrderParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Updating order with params - {}", params);

        final Order order = orderRepository.findById(params.getId())
                .orElseThrow(() -> {
                    throw new OrderNotFoundException(params.getId());
                });

        final Table table = tableService.findById(params.getTableId())
                .orElseThrow(() -> {
                    throw new TableNotFoundException(params.getTableId());
                });

        order.setTable(table);
        order.setOrderStatus(params.getOrderStatus());

        LOGGER.info("Successfully updated order with params - {}, result - {}", params, order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        Assert.notNull(
                id,
                "Class - OrderServiceImpl, method - findById(Long id) " +
                        "id should not be null"
        );
        LOGGER.info("Finding order with id - {}", id);

        final Optional<Order> optionalOrder = orderRepository.findById(id);

        LOGGER.info("Successfully found order with id - {}, result - {}", id, optionalOrder);
        return optionalOrder;
    }

    @Override
    public List<Order> findByTableId(Long id) {
        Assert.notNull(
                id,
                "Class - OrderServiceImpl, method - findByTableId(Long id) " +
                        "id should not be null"
        );
        LOGGER.info("Finding order for table with id - {}", id);

        final List<Order> orders = orderRepository.findByTableId(id);

        LOGGER.info("Successfully found order for table with id - {}, result - {}", id, orders);
        return orders;
    }
}
