package com.example.ecommerce.service;

import com.example.ecommerce.dto.PaymentDto.PaymentInDto;
import com.example.ecommerce.dto.PaymentDto.PaymentOutDto;
import com.example.ecommerce.mapper.PaymentMapper;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Payment;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final OrderRepository orderRepo;
    private final PaymentMapper mapper;

    public PaymentOutDto createPayment(PaymentInDto dto){
        Order order = orderRepo.findById(dto.getOrderId())
                .orElseThrow(()-> new RuntimeException("order not found"));
        Payment payment = mapper.toEntity(dto,order);
        Payment savedPayment = paymentRepo.save(payment);
        return mapper.toDto(savedPayment);
    }
    public List<PaymentOutDto> getAllPayments(){
        return paymentRepo.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
