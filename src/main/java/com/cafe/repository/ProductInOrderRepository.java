package com.cafe.repository;

import com.cafe.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

    List<ProductInOrder> findByOrderId(Long id);

}
