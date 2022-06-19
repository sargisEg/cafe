package com.cafe.facade.mapper.productinorder;

import com.cafe.entity.ProductInOrder;
import com.cafe.facade.dto.entity.ProductInOrderDto;

public interface ProductInOrderMapper {

    ProductInOrderDto map(ProductInOrder productInOrder);
}
