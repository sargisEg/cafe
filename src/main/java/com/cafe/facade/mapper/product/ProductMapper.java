package com.cafe.facade.mapper.product;

import com.cafe.entity.Product;
import com.cafe.facade.dto.entity.ProductDto;

public interface ProductMapper {

    ProductDto map(Product product);
}
