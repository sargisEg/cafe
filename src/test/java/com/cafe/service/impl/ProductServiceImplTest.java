package com.cafe.service.impl;

import com.cafe.entity.Product;
import com.cafe.repository.ProductRepository;
import com.cafe.service.core.product.CreateProductParams;
import com.cafe.service.core.product.ProductService;
import com.cafe.service.core.product.UpdateProductParams;
import com.cafe.service.impl.exceptions.ProductNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private ProductService testSubject;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        testSubject = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testCreateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(()-> testSubject.create(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testUpdateWhenParamsIsNull() {
        Assertions.assertThatThrownBy(()-> testSubject.update(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByIdWhenIdIsNull() {
        Assertions.assertThatThrownBy(()-> testSubject.findById(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testFindByNameWhenNameIsNull() {
        Assertions.assertThatThrownBy(()-> testSubject.findByName(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testCreate() {
        final Product product = new Product("name", 12L);
        product.setId(1L);
        when(productRepository.save(new Product("name", 12L)))
                .thenReturn(product);

        Assertions.assertThat(testSubject.create(new CreateProductParams("name", 12L)))
                .isEqualTo(product);

        verify(productRepository).save(new Product("name", 12L));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testUpdateWhenProductNotFound() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> testSubject.update(new UpdateProductParams(
                1L,
                "name",
                200L
        ))).isExactlyInstanceOf(ProductNotFoundException.class);

        verify(productRepository).findById(1L);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testUpdate() {
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(new Product()));

        final Product expected = new Product("name", 200L);
        expected.setId(1L);
        Assertions.assertThat(testSubject.update(new UpdateProductParams(
                1L,
                "name",
                200L
        ))).isEqualTo(expected);

        verify(productRepository).findById(1L);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testFindById() {
        final Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        Assertions.assertThat(testSubject.findById(1L))
                        .isEqualTo(Optional.of(product));

        verify(productRepository).findById(1L);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testFindByName() {
        final Product product = new Product();
        product.setId(1L);
        when(productRepository.findByName("name"))
                .thenReturn(Optional.of(product));

        Assertions.assertThat(testSubject.findByName("name"))
                        .isEqualTo(Optional.of(product));

        verify(productRepository).findByName("name");
        verifyNoMoreInteractions(productRepository);
    }
}