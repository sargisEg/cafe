package com.cafe.service.core.productinorder;

import com.cafe.entity.ProductInOrder;

import java.util.List;
import java.util.Optional;

public interface ProductInOrderService {

    ProductInOrder create(CreateProductInOrderParams params);

    ProductInOrder update(UpdateProductInOrderParams params);

    Optional<ProductInOrder> findById(Long id);

    List<ProductInOrder> findByOrderId(Long id);
}
