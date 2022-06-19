package com.cafe.facade.waiter;

import com.cafe.entity.*;
import com.cafe.facade.dto.CreateOrderRequestDto;
import com.cafe.facade.dto.CreateProductInOrderRequestDto;
import com.cafe.facade.dto.EditeOrderRequestDto;
import com.cafe.facade.dto.EditeProductInOrderRequestDto;
import com.cafe.facade.dto.entity.OrderDto;
import com.cafe.facade.dto.entity.ProductInOrderDto;
import com.cafe.facade.dto.entity.TableDto;
import com.cafe.facade.mapper.order.OrderMapper;
import com.cafe.facade.mapper.productinorder.ProductInOrderMapper;
import com.cafe.facade.mapper.table.TableMapper;
import com.cafe.service.core.order.CreateOrderParams;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.order.UpdateOrderParams;
import com.cafe.service.core.product.ProductService;
import com.cafe.service.core.product.UpdateProductParams;
import com.cafe.service.core.productinorder.CreateProductInOrderParams;
import com.cafe.service.core.productinorder.ProductInOrderService;
import com.cafe.service.core.productinorder.UpdateProductInOrderParams;
import com.cafe.service.core.table.TableService;
import com.cafe.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class WaiterFacadeImpl implements WaiterFacade {

    private final static Logger LOGGER = LoggerFactory.getLogger(WaiterFacadeImpl.class);

    private final TableService tableService;
    private final OrderService orderService;
    private final ProductInOrderService productInOrderService;
    private final ProductService productService;
    private final UserService userService;
    private final TableMapper tableMapper;
    private final OrderMapper orderMapper;
    private final ProductInOrderMapper productInOrderMapper;


    public WaiterFacadeImpl(
            TableService tableService,
            OrderService orderService,
            ProductInOrderService productInOrderService,
            ProductService productService,
            UserService userService,
            TableMapper tableMapper,
            OrderMapper orderMapper,
            ProductInOrderMapper productInOrderMapper) {
        this.tableService = tableService;
        this.orderService = orderService;
        this.productInOrderService = productInOrderService;
        this.productService = productService;
        this.userService = userService;
        this.tableMapper = tableMapper;
        this.orderMapper = orderMapper;
        this.productInOrderMapper = productInOrderMapper;
    }

    @Override
    public List<TableDto> getAssignedTables(String username) {
        Assert.hasText(
                username,
                "Class - WaiterFacadeImpl, method - getAssignedTables(String username) " +
                        "username should not be null"
        );
        LOGGER.info("Getting assigned tables for waiter - {} for provided request", username);

        final Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return List.of(new TableDto(List.of("Not found user with username - " + username)));
        }

        final List<Table> tables = tableService.findByWaiterId(optionalUser.get().getId());
        final List<TableDto> tableDtos = new LinkedList<>();

        tables.forEach(table -> {
            tableDtos.add(tableMapper.map(table));
        });

        LOGGER.info("Successfully got assigned tables for waiter - {} for provided request, response - {}", username, tableDtos);
        return tableDtos;
    }

    @Override
    public OrderDto createOrder(CreateOrderRequestDto dto) {
        Assert.notNull(
                dto,
                "Class - WaiterFacadeImpl, method - createOrder(CreateOrderRequestDto dto) " +
                        "dto should not be null"
        );
        LOGGER.info("Creating order for provided request - {}", dto);

        final Optional<Table> optionalTable = tableService.findById(dto.getTableId());

        if (optionalTable.isEmpty()) {
            return new OrderDto(List.of("Not found table with id - " + dto.getTableId()));
        }

        final List<Order> orderList = orderService.findByTableId(dto.getTableId());

        for (Order order : orderList) {
            if (order.getOrderStatus().equals(OrderStatus.OPEN)) {
                return new OrderDto(List.of("Table with id - " + dto.getTableId() + " already has open order"));
            }
        }

        final Order order = orderService.create(new CreateOrderParams(dto.getTableId(), OrderStatus.OPEN));

        final OrderDto orderDto = orderMapper.map(order);

        LOGGER.info("Successfully created order for provided request - {}, response - {}", dto, orderDto);
        return orderDto;
    }

    @Override
    public ProductInOrderDto createProductInOrder(CreateProductInOrderRequestDto dto) {
        Assert.notNull(
                dto,
                "Class - WaiterFacadeImpl, method - createProductInOrder(CreateProductInOrderRequestDto dto) " +
                        "dto should not be null"
        );
        LOGGER.info("Creating product for provided request - {}", dto);

        final Optional<Order> optionalOrder = orderService.findById(dto.getOrderId());

        if (optionalOrder.isEmpty()) {
            return new ProductInOrderDto(List.of("Not found order with id - " + dto.getOrderId()));
        }

        if (optionalOrder.get().getOrderStatus().equals(OrderStatus.CLOSED) ||
                optionalOrder.get().getOrderStatus().equals(OrderStatus.CANCELLED)) {
            return new ProductInOrderDto(List.of("Order with id - " + dto.getOrderId() + " already closed or cancelled"));
        }

        final List<ProductInOrder> productInOrderList = productInOrderService.findByOrderId(dto.getOrderId());

        for (ProductInOrder productInOrder : productInOrderList) {
            if (productInOrder.getProductInOrderStatus().equals(ProductInOrderStatus.ACTIVE)) {
                if (productInOrder.getProduct().getName().equals(dto.getProductName())) {
                    return new ProductInOrderDto(List.of("Product with name - " + dto.getProductName()
                            + " already exist in this order"));
                }
            }
        }

        final Optional<Product> optionalProduct = productService.findByName(dto.getProductName());

        if (optionalProduct.isEmpty()) {
            return new ProductInOrderDto(List.of("Not found product with name - " + dto.getProductName()));
        }

        if (optionalProduct.get().getAmount() < dto.getProductAmount()) {
            return new ProductInOrderDto(List.of("Not enough product - " + dto.getProductName() + " in the amount of " + dto.getProductAmount()));
        }

        final ProductInOrder productInOrder = productInOrderService.create(new CreateProductInOrderParams(
                dto.getOrderId(),
                optionalProduct.get().getId(),
                dto.getProductAmount(),
                ProductInOrderStatus.ACTIVE
        ));

        productService.update(new UpdateProductParams(
                optionalProduct.get().getId(),
                optionalProduct.get().getName(),
                optionalProduct.get().getAmount() - dto.getProductAmount()
        ));

        final ProductInOrderDto productInOrderDto = productInOrderMapper.map(productInOrder);

        LOGGER.info("Successfully created product for provided request - {}, response - {}", dto, productInOrderDto);
        return productInOrderDto;
    }

    @Override
    public ProductInOrderDto editProductInOrder(EditeProductInOrderRequestDto dto) {
        Assert.notNull(
                dto,
                "Class - WaiterFacadeImpl, method - editProductInOrder(EditeProductInOrderRequestDto dto) " +
                        "dto should not be null"
        );
        LOGGER.info("Editing product in order for provided request - {}", dto);

        final Optional<Order> order = orderService.findById(dto.getOrderId());

        if (order.isEmpty()) {
            return new ProductInOrderDto(List.of("Not found order with id - " + dto.getOrderId()));
        }

        if (order.get().getOrderStatus().equals(OrderStatus.CLOSED) ||
                order.get().getOrderStatus().equals(OrderStatus.CANCELLED)) {
            return new ProductInOrderDto(List.of("Order with id - " + dto.getOrderId() + " already closed or cancelled"));
        }

        final List<ProductInOrder> productInOrderList = productInOrderService.findByOrderId(order.get().getId());

        ProductInOrder oldProductInOrder = null;

        for (ProductInOrder productInOrder : productInOrderList) {
            if (productInOrder.getProductInOrderStatus().equals(ProductInOrderStatus.ACTIVE)) {
                if (productInOrder.getProduct().getName().equals(dto.getProductName())) {
                    oldProductInOrder = productInOrder;
                }
            }
        }

        if (oldProductInOrder == null) {
            return new ProductInOrderDto(List.of("Not found active product in order with product - "
                    + dto.getProductName()));
        }

        if (dto.getProductInOrderStatus().equals(ProductInOrderStatus.CLOSED)) {
            return new ProductInOrderDto(List.of("Cannot close product in order while order is open"));
        }


        final Optional<Product> optionalProductFromDto = productService.findByName(dto.getProductName());

        if (optionalProductFromDto.isEmpty()) {
            return new ProductInOrderDto(List.of("Not found product with name - " + dto.getProductName()));
        }

        final Product productFromDto = optionalProductFromDto.get();

        if (productFromDto.getAmount() + oldProductInOrder.getAmount() < dto.getProductAmount()) {
            return new ProductInOrderDto(List.of("Not enough product - " + productFromDto.getName() + " in the amount of " + dto.getProductAmount()));
        }


        productService.update(new UpdateProductParams(
                oldProductInOrder.getProduct().getId(),
                oldProductInOrder.getProduct().getName(),
                oldProductInOrder.getProduct().getAmount() + oldProductInOrder.getAmount()
        ));

        if (dto.getProductInOrderStatus().equals(ProductInOrderStatus.ACTIVE)) {
            productService.update(new UpdateProductParams(
                    productFromDto.getId(),
                    productFromDto.getName(),
                    productFromDto.getAmount() - dto.getProductAmount()
            ));
        }

        final ProductInOrder updatedProductInOrder = productInOrderService.update(new UpdateProductInOrderParams(
                oldProductInOrder.getId(),
                oldProductInOrder.getOrder().getId(),
                productFromDto.getId(),
                dto.getProductAmount(),
                dto.getProductInOrderStatus()
        ));


        final ProductInOrderDto productInOrderDto = productInOrderMapper.map(updatedProductInOrder);

        LOGGER.info("Successfully edited product in order for provided request - {}, response - {}", dto, productInOrderDto);
        return productInOrderDto;
    }

    @Override
    public OrderDto editeOrder(EditeOrderRequestDto dto) {
        Assert.notNull(
                dto,
                "Class - WaiterFacadeImpl, method - editeOrder(EditeOrderRequestDto dto) " +
                        "dto should not be null"
        );
        LOGGER.info("Editing order for provided request - {}", dto);

        final Optional<Order> optionalOrder = orderService.findById(dto.getId());

        if (optionalOrder.isEmpty()) {
            return new OrderDto(List.of("Not found order with id - " + dto.getId()));
        }

        // TODO: 15/06/2022 handle case when order already closed or cancelled

        final Order updatedOrder = orderService.update(new UpdateOrderParams(
                dto.getId(),
                optionalOrder.get().getTable().getId(),
                dto.getOrderStatus()
        ));

        if (dto.getOrderStatus().equals(OrderStatus.CLOSED)) {
            final List<ProductInOrder> productInOrderList = productInOrderService.findByOrderId(dto.getId());
            productInOrderList.forEach(productInOrder -> {
                productInOrderService.update(new UpdateProductInOrderParams(
                        productInOrder.getId(),
                        productInOrder.getOrder().getId(),
                        productInOrder.getProduct().getId(),
                        productInOrder.getAmount(),
                        ProductInOrderStatus.CLOSED
                ));
            });
        }

        if (dto.getOrderStatus().equals(OrderStatus.CANCELLED)) {
            final List<ProductInOrder> productInOrderList = productInOrderService.findByOrderId(dto.getId());
            productInOrderList.forEach(productInOrder -> {
                if (productInOrder.getProductInOrderStatus().equals(ProductInOrderStatus.ACTIVE)) {
                    final Product product = productInOrder.getProduct();
                    productInOrderService.update(new UpdateProductInOrderParams(
                            productInOrder.getId(),
                            productInOrder.getOrder().getId(),
                            product.getId(),
                            productInOrder.getAmount(),
                            ProductInOrderStatus.CANCELLED
                    ));
                    productService.update(new UpdateProductParams(
                            product.getId(),
                            product.getName(),
                            product.getAmount() + productInOrder.getAmount()
                    ));
                }
            });
        }

        final OrderDto orderDto = orderMapper.map(updatedOrder);

        LOGGER.info("Successfully edited order for provided request - {}, response - {}", dto, orderDto);
        return orderDto;
    }
}
