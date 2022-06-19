package com.cafe.service.impl;

import com.cafe.entity.Order;
import com.cafe.entity.Product;
import com.cafe.entity.ProductInOrder;
import com.cafe.entity.ProductInOrderStatus;
import com.cafe.repository.ProductInOrderRepository;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.product.ProductService;
import com.cafe.service.core.productinorder.CreateProductInOrderParams;
import com.cafe.service.core.productinorder.ProductInOrderService;
import com.cafe.service.core.productinorder.UpdateProductInOrderParams;
import com.cafe.service.impl.exceptions.OrderNotFoundException;
import com.cafe.service.impl.exceptions.ProductInOrderNotFoundException;
import com.cafe.service.impl.exceptions.ProductNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductInOrderServiceImplTest {

    private ProductInOrderService testSubject;

    @Mock
    private ProductInOrderRepository productInOrderRepository;

    @Mock
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void init() {
        testSubject = new ProductInOrderServiceImpl(
                productInOrderRepository,
                orderService,
                productService
        );
    }

    @Test
    public void testCreateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.create(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testUpdateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.update(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByIdWhenIdIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.findById(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByOrderIdWhenIdIsNull() {
        Assertions.assertThatThrownBy(() -> testSubject.findByOrderId(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateWhenOrderNotFound() {
        when(orderService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> testSubject.create(new CreateProductInOrderParams(
                        1L,
                        1L,
                        12L,
                        ProductInOrderStatus.ACTIVE
                ))
        ).isExactlyInstanceOf(OrderNotFoundException.class);

        verify(orderService).findById(1L);
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }

    @Test
    public void testCreateWhenProductNotFound() {
        when(orderService.findById(1L))
                .thenReturn(Optional.of(new Order()));
        when(productService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> testSubject.create(new CreateProductInOrderParams(
                        1L,
                        1L,
                        12L,
                        ProductInOrderStatus.ACTIVE
                ))
        ).isExactlyInstanceOf(ProductNotFoundException.class);

        verify(orderService).findById(1L);
        verify(productService).findById(1L);
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }

    @Test
    public void testCreate() {
        when(orderService.findById(1L))
                .thenReturn(Optional.of(new Order()));
        when(productService.findById(1L))
                .thenReturn(Optional.of(new Product()));
        when(productInOrderRepository.save(new ProductInOrder(
                new Order(),
                new Product(),
                12L,
                ProductInOrderStatus.ACTIVE
        ))).thenReturn(new ProductInOrder());

        Assertions.assertThat(
                testSubject.create(new CreateProductInOrderParams(
                        1L,
                        1L,
                        12L,
                        ProductInOrderStatus.ACTIVE
                ))
        ).isEqualTo(new ProductInOrder());

        verify(orderService).findById(1L);
        verify(productService).findById(1L);
        verify(productInOrderRepository).save(new ProductInOrder(
                new Order(),
                new Product(),
                12L,
                ProductInOrderStatus.ACTIVE
        ));
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }

    @Test
    public void testUpdateWhenProductInOrderNotFound() {
        when(productInOrderRepository.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> testSubject.update(
                        new UpdateProductInOrderParams(
                                1L,
                                1L,
                                1L,
                                10L,
                                ProductInOrderStatus.ACTIVE
                        )
                )
        ).isExactlyInstanceOf(ProductInOrderNotFoundException.class);

        verify(productInOrderRepository).findById(1L);
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }

    @Test
    public void testUpdateWhenOrderNotFound() {
        when(productInOrderRepository.findById(1L))
                .thenReturn(Optional.of(new ProductInOrder()));

        when(orderService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> testSubject.update(
                        new UpdateProductInOrderParams(
                                1L,
                                1L,
                                1L,
                                10L,
                                ProductInOrderStatus.ACTIVE
                        )
                )
        ).isExactlyInstanceOf(OrderNotFoundException.class);

        verify(productInOrderRepository).findById(1L);
        verify(orderService).findById(1L);
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }

    @Test
    public void testUpdateWhenProductNotFound() {
        when(productInOrderRepository.findById(1L))
                .thenReturn(Optional.of(new ProductInOrder()));

        when(orderService.findById(1L))
                .thenReturn(Optional.of(new Order()));

        when(productService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(
                () -> testSubject.update(
                        new UpdateProductInOrderParams(
                                1L,
                                1L,
                                1L,
                                10L,
                                ProductInOrderStatus.ACTIVE
                        )
                )
        ).isExactlyInstanceOf(ProductNotFoundException.class);

        verify(productInOrderRepository).findById(1L);
        verify(orderService).findById(1L);
        verify(productService).findById(1L);
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }

    @Test
    public void testUpdate() {
        final ProductInOrder productInOrder = new ProductInOrder();
        when(productInOrderRepository.findById(1L))
                .thenReturn(Optional.of(productInOrder));

        when(orderService.findById(1L))
                .thenReturn(Optional.of(new Order()));

        when(productService.findById(1L))
                .thenReturn(Optional.of(new Product()));

        productInOrder.setId(1L);
        productInOrder.setOrder(new Order());
        productInOrder.setProduct(new Product());
        productInOrder.setAmount(10L);
        productInOrder.setProductInOrderStatus(ProductInOrderStatus.ACTIVE);

        Assertions.assertThat(
                testSubject.update(
                        new UpdateProductInOrderParams(
                                1L,
                                1L,
                                1L,
                                10L,
                                ProductInOrderStatus.ACTIVE
                        )
                )
        ).isEqualTo(productInOrder);

        verify(productInOrderRepository).findById(1L);
        verify(orderService).findById(1L);
        verify(productService).findById(1L);
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }

    @Test
    public void testFindById() {
        when(productInOrderRepository.findById(1L))
                .thenReturn(Optional.of(new ProductInOrder()));

        Assertions.assertThat(testSubject.findById(1L))
                .isEqualTo(Optional.of(new ProductInOrder()));

        verify(productInOrderRepository).findById(1L);
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }

    @Test
    public void testFindByWaiterId() {
        when(productInOrderRepository.findByOrderId(1L))
                .thenReturn(List.of(new ProductInOrder()));

        Assertions.assertThat(testSubject.findByOrderId(1L))
                .isEqualTo(List.of(new ProductInOrder()));

        verify(productInOrderRepository).findByOrderId(1L);
        verifyNoMoreInteractions(productInOrderRepository, orderService, productService);
    }
}