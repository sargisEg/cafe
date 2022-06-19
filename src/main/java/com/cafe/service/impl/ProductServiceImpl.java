package com.cafe.service.impl;

import com.cafe.entity.Product;
import com.cafe.repository.ProductRepository;
import com.cafe.service.core.product.CreateProductParams;
import com.cafe.service.core.product.ProductService;
import com.cafe.service.core.product.UpdateProductParams;
import com.cafe.service.impl.exceptions.ProductNotFoundException;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
class ProductServiceImpl implements ProductService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product create(CreateProductParams params) {
        Assert.notNull(
                params,
                "Class - ProductServiceImpl, method - create(CreateProductParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Creating product with params - {}", params);

        final Product productFromParams = new Product(
                params.getName(),
                params.getAmount()
        );

        final Product product = productRepository.save(productFromParams);

        LOGGER.info("Successfully created product with params - {}, result - {}", params, product);
        return product;
    }

    @Override
    @Transactional
    public Product update(UpdateProductParams params) {
        Assert.notNull(
                params,
                "Class - ProductServiceImpl, method - update(UpdateProductParams params) " +
                        "params should not be null"
        );
        LOGGER.info("Updating product with params - {}", params);

        final Product product = productRepository.findById(params.getId())
                .orElseThrow(() -> {
                    throw new ProductNotFoundException(params.getId());
                });

        product.setId(params.getId());
        product.setName(params.getName());
        product.setAmount(params.getAmount());

        LOGGER.info("Successfully updated product with params - {}, result - {}", params, product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Assert.notNull(
                id,
                "Class - ProductServiceImpl, method - findById(Long id) " +
                        "id should not be null"
        );
        LOGGER.info("Finding product with id - {}", id);

        final Optional<Product> optionalProduct = productRepository.findById(id);

        LOGGER.info("Successfully found product with id - {}, result - {}", id, optionalProduct);
        return optionalProduct;
    }

    @Override
    public Optional<Product> findByName(String name) {
        Assert.hasText(
                name,
                "Class - ProductServiceImpl, method - findByName(String name) " +
                        "name should not be null or empty"
        );
        LOGGER.info("Finding product with name - {}", name);

        final Optional<Product> optionalProduct = productRepository.findByName(name);

        LOGGER.info("Successfully found product with name - {}, result - {}", name, optionalProduct);
        return optionalProduct;
    }
}
