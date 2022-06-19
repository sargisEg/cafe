package com.cafe.facade.mapper.productinorder;

import com.cafe.entity.ProductInOrder;
import com.cafe.facade.dto.entity.ProductInOrderDto;
import com.cafe.facade.mapper.order.OrderMapper;
import com.cafe.facade.mapper.product.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ProductInOrderMapperImpl implements ProductInOrderMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInOrderMapperImpl.class);

    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    public ProductInOrderMapperImpl(OrderMapper orderMapper, ProductMapper productMapper) {
        this.orderMapper = orderMapper;
        this.productMapper = productMapper;
    }

    @Override
    public ProductInOrderDto map(ProductInOrder productInOrder) {
        Assert.notNull(
                productInOrder,
                "Class - ProductInOrderMapperImpl, method - map(ProductInOrder productInOrder) " +
                        "productInOrder should not be null"
        );
        LOGGER.debug("Mapping productInOrder - {}", productInOrder);

        final ProductInOrderDto productInOrderDto = new ProductInOrderDto(
                productInOrder.getId(),
                orderMapper.map(productInOrder.getOrder()),
                productMapper.map(productInOrder.getProduct()),
                productInOrder.getAmount(),
                productInOrder.getProductInOrderStatus()
        );

        LOGGER.debug("Successfully mapped productInOrder - {}, result - {}", productInOrder, productInOrderDto);
        return productInOrderDto;
    }
}
