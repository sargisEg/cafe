package com.cafe.facade.mapper.product;

import com.cafe.entity.Product;
import com.cafe.facade.dto.entity.ProductDto;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMapperImpl.class);

    @Override
    public ProductDto map(Product product) {
        Assert.notNull(
                product,
                "Class - ProductMapperImpl, method - map(Product product) " +
                        "product should not be null"
        );
        LOGGER.debug("Mapping product - {}", product);

        final ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getAmount());

        LOGGER.debug("Successfully mapped product - {}, result - {}", product, productDto);
        return productDto;
    }
}
