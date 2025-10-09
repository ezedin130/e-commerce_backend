package com.example.ecommerce.repository;

import com.example.ecommerce.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant,Long> {
}
