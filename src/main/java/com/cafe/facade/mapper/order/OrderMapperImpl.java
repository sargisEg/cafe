package com.cafe.facade.mapper.order;

import com.cafe.entity.Order;
import com.cafe.facade.dto.entity.OrderDto;
import com.cafe.facade.mapper.table.TableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class OrderMapperImpl implements OrderMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderMapperImpl.class);

    private final TableMapper tableMapper;

    public OrderMapperImpl(TableMapper tableMapper) {
        this.tableMapper = tableMapper;
    }

    @Override
    public OrderDto map(Order order) {
        Assert.notNull(
                order,
                "Class - OrderMapperImpl, method - map(Order order) " +
                        "order should not be null"
        );
        LOGGER.debug("Mapping order - {}", order);

        final OrderDto orderDto = new OrderDto(
                order.getId(),
                tableMapper.map(order.getTable()),
                order.getOrderStatus()
        );

        LOGGER.debug("Successfully mapped order - {}, result - {}", order, orderDto);
        return orderDto;
    }
}
