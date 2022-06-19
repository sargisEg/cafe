package com.cafe.service.impl;

import com.cafe.entity.Order;
import com.cafe.entity.OrderStatus;
import com.cafe.entity.Table;
import com.cafe.repository.OrderRepository;
import com.cafe.service.core.order.CreateOrderParams;
import com.cafe.service.core.order.OrderService;
import com.cafe.service.core.order.UpdateOrderParams;
import com.cafe.service.core.table.TableService;
import com.cafe.service.impl.exceptions.OrderNotFoundException;
import com.cafe.service.impl.exceptions.TableNotFoundException;
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
class OrderServiceImplTest {

    private OrderService testSubject;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private TableService tableService;

    @BeforeEach
    public void init() {
        testSubject = new OrderServiceImpl(orderRepository, tableService);
    }

    @Test
    public void testCreateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(()->testSubject.create(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testUpdateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(()->testSubject.update(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByIdWhenIdIsNull() {
        Assertions.assertThatThrownBy(()->testSubject.findById(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByTableIdWhenIdIsNull() {
        Assertions.assertThatThrownBy(()->testSubject.findByTableId(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreateWhenTableNotFound() {
        when(tableService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(()->testSubject.create(new CreateOrderParams(1L, OrderStatus.CANCELLED)))
                .isExactlyInstanceOf(TableNotFoundException.class);

        verify(tableService).findById(1L);
        verifyNoMoreInteractions(orderRepository, tableService);
    }

    @Test
    public void testCreate() {
        final Order order = new Order();
        order.setId(1L);

        when(tableService.findById(1L))
                .thenReturn(Optional.of(new Table()));

        when(orderRepository.save(new Order(new Table(), OrderStatus.CANCELLED)))
                .thenReturn(order);

        Assertions.assertThat(testSubject.create(new CreateOrderParams(1L, OrderStatus.CANCELLED)))
                .isEqualTo(order);

        verify(orderRepository).save(new Order(new Table(), OrderStatus.CANCELLED));
        verify(tableService).findById(1L);
        verifyNoMoreInteractions(orderRepository, tableService);
    }

    @Test
    public void testUpdateWhenOrderNotFound() {
        when(orderRepository.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> testSubject.update(
                new UpdateOrderParams(1L, 1L, OrderStatus.CANCELLED)
        )).isExactlyInstanceOf(OrderNotFoundException.class);

        verify(orderRepository).findById(1L);
        verifyNoMoreInteractions(orderRepository, tableService);
    }

    @Test
    public void testUpdateWhenTableNotFound() {

        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(new Order()));

        when(tableService.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> testSubject.update(
                new UpdateOrderParams(1L, 1L, OrderStatus.CANCELLED)
        )).isExactlyInstanceOf(TableNotFoundException.class);

        verify(orderRepository).findById(1L);
        verify(tableService).findById(1L);
        verifyNoMoreInteractions(orderRepository, tableService);
    }

    @Test
    public void testUpdate() {
        final Order order = new Order();
        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(order));

        when(tableService.findById(1L))
                .thenReturn(Optional.of(new Table()));
        order.setId(1L);
        order.setTable(new Table());
        order.setOrderStatus(OrderStatus.CANCELLED);
        Assertions.assertThat(testSubject.update(
                new UpdateOrderParams(1L, 1L, OrderStatus.CANCELLED)
        )).isEqualTo(order);

        verify(orderRepository).findById(1L);
        verify(tableService).findById(1L);
        verifyNoMoreInteractions(orderRepository, tableService);
    }

    @Test
    public void testFindById() {
        when(orderRepository.findById(1L))
                .thenReturn(Optional.of(new Order()));

        Assertions.assertThat(testSubject.findById(1L))
                .isEqualTo(Optional.of(new Order()));

        verify(orderRepository).findById(1L);
        verifyNoMoreInteractions(orderRepository, tableService);
    }

    @Test
    public void testFindByTableId() {
        when(orderRepository.findByTableId(1L))
                .thenReturn(List.of(new Order()));

        Assertions.assertThat(testSubject.findByTableId(1L))
                .isEqualTo(List.of(new Order()));

        verify(orderRepository).findByTableId(1L);
        verifyNoMoreInteractions(orderRepository, tableService);
    }
}