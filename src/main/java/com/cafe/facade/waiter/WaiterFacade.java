package com.cafe.facade.waiter;

import com.cafe.facade.dto.CreateOrderRequestDto;
import com.cafe.facade.dto.CreateProductInOrderRequestDto;
import com.cafe.facade.dto.EditeOrderRequestDto;
import com.cafe.facade.dto.EditeProductInOrderRequestDto;
import com.cafe.facade.dto.entity.OrderDto;
import com.cafe.facade.dto.entity.ProductInOrderDto;
import com.cafe.facade.dto.entity.TableDto;

import java.util.List;

public interface WaiterFacade {

    List<TableDto> getAssignedTables(String username);

    OrderDto createOrder(CreateOrderRequestDto dto);

    ProductInOrderDto createProductInOrder(CreateProductInOrderRequestDto dto);

    ProductInOrderDto editProductInOrder(EditeProductInOrderRequestDto dto);

    OrderDto editeOrder(EditeOrderRequestDto dto);
}
