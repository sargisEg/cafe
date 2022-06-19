package com.cafe.service.impl;

import com.cafe.entity.Order;
import com.cafe.entity.Product;
import com.cafe.entity.ProductInOrder;
import com.cafe.repository.ProductInOrderRepository;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.product.ProductService;
import com.cafe.service.core.productinorder.CreateProductInOrderParams;
import com.cafe.service.core.productinorder.ProductInOrderService;
import com.cafe.service.core.productinorder.UpdateProductInOrderParams;
import com.cafe.service.impl.exceptions.OrderNotFoundException;
import com.cafe.service.impl.exceptions.ProductInOrderNotFoundException;
import com.cafe.service.impl.exceptions.ProductNotFoundException;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
class ProductInOrderServiceImpl implements ProductInOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInOrderServiceImpl.class);

    private final ProductInOrderRepository productInOrderRepository;

    private final OrderService orderService;

    private final ProductService productService;

    public ProductInOrderServiceImpl(ProductInOrderRepository productInOrderRepository, OrderService orderService, ProductService productService) {
        this.productInOrderRepository = productInOrderRepository;
        this.orderService = orderService;
        this.productService = productService;
    }

    @Override
    @Transactional
    public ProductInOrder create(CreateProductInOrderParams params) {
        Assert.notNull(
                params,
                "Class - ProductInOrderServiceImpl, method - create(CreateProductInOrderParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Creating product in order with params - {}", params);

        final Order order = orderService.findById(params.getOrderId())
                .orElseThrow(() -> {
                    throw new OrderNotFoundException(params.getOrderId());
                });

        final Product product = productService.findById(params.getProductId())
                .orElseThrow(() -> {
                    throw new ProductNotFoundException(params.getProductId());
                });

        final ProductInOrder productInOrderFromParams = new ProductInOrder(
                order,
                product,
                params.getAmount(),
                params.getProductInOrderStatus()
        );

        final ProductInOrder productInOrder = productInOrderRepository.save(productInOrderFromParams);

        LOGGER.info("Successfully created product in order with params - {}, result - {}", params, productInOrder);
        return productInOrder;
    }

    @Override
    @Transactional
    public ProductInOrder update(UpdateProductInOrderParams params) {
        Assert.notNull(
                params,
                "Class - ProductInOrderServiceImpl, method - update(UpdateProductInOrderParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Updating product in order with params - {}", params);

        final ProductInOrder productInOrder = productInOrderRepository.findById(params.getId())
                .orElseThrow(() -> {
                    throw new ProductInOrderNotFoundException(params.getId());
                });

        final Order order = orderService.findById(params.getOrderId())
                .orElseThrow(() -> {
                    throw new OrderNotFoundException(params.getOrderId());
                });

        final Product product = productService.findById(params.getProductId())
                .orElseThrow(() -> {
                    throw new ProductNotFoundException(params.getProductId());
                });

        productInOrder.setOrder(order);
        productInOrder.setProduct(product);
        productInOrder.setAmount(params.getAmount());
        productInOrder.setProductInOrderStatus(params.getProductInOrderStatus());

        LOGGER.info("Successfully updated product in order with params - {}, result- {}", params, productInOrder);
        return productInOrder;
    }

    @Override
    public Optional<ProductInOrder> findById(Long id) {
        Assert.notNull(
                id,
                "Class - ProductInOrderServiceImpl, method - findById(Long id) " +
                        "id should not be null"
        );
        LOGGER.info("Finding product in order with id - {}", id);

        final Optional<ProductInOrder> optionalProductInOrder = productInOrderRepository.findById(id);

        LOGGER.info("Successfully found product in order with id - {}, result - {}", id, optionalProductInOrder);
        return optionalProductInOrder;
    }

    @Override
    public List<ProductInOrder> findByOrderId(Long id) {
        Assert.notNull(
                id,
                "Class - ProductInOrderServiceImpl, method - findByOrderId(Long id) " +
                        "id should not be null"
        );
        LOGGER.info("Finding product in order with order id - {}", id);

        final List<ProductInOrder> productsInOrder = productInOrderRepository.findByOrderId(id);

        LOGGER.info("Successfully found product in order with order id - {}, result - {}", id, productsInOrder);
        return productsInOrder;
    }
}
