package com.cafe.service.core.product;

import com.cafe.entity.Product;

import java.util.Optional;

public interface ProductService {

    Product create(CreateProductParams params);

    Product update(UpdateProductParams params);

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);
}
