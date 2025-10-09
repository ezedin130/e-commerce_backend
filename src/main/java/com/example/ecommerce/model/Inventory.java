package com.example.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "quantity is required")
    private Integer quantity;
    @NotNull(message = "store id is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
    @NotNull(message = "product varient id is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_varient_id")
    private ProductVariant productVariant;
}
