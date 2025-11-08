package com.example.ecommerce.model;

import com.example.ecommerce.constant.OrderStatus;
import com.example.ecommerce.constant.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "order status is required")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @NotNull(message = "payment status is required")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @NotNull(message = "Order date is required")
    private LocalDate orderDate;
    @NotNull(message = "total Price is required")
    private double totalPrice;
    @NotNull(message = "product variant id is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;
    //ToDo:add relationship with the user after user is created
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
