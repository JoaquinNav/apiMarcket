package com.joaDev.apiMarket.repository;

import com.joaDev.apiMarket.model.ProductEntity;
import org.hibernate.id.BulkInsertionCapableIdentifierGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByPriceBetween(BigDecimal start, BigDecimal end);

    Optional<ProductEntity> findByName(String name);
}
