package com.example.ecommerce.controller;

import com.example.ecommerce.dto.PaymentDto.PaymentInDto;
import com.example.ecommerce.dto.PaymentDto.PaymentOutDto;
import com.example.ecommerce.mapper.PaymentMapper;
import com.example.ecommerce.model.Payment;
import com.example.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private final PaymentService payService;
    @Autowired
    private final PaymentMapper mapper;

    @PostMapping("/payment")
    public ResponseEntity<PaymentOutDto> createPayment(@RequestBody PaymentInDto dto){
        PaymentOutDto result = payService.createPayment(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get-payment-by-id/{id}")
    public ResponseEntity<PaymentOutDto> getPaymentById(@PathVariable Long id){
        Payment payment = payService.getPaymentById(id);
        PaymentOutDto dto = mapper.toDto(payment);
        return ResponseEntity.ok().body(dto);
    }
    @GetMapping("/payment")
    public ResponseEntity<List<PaymentOutDto>> getAllPayments(){
        List<PaymentOutDto> result = payService.getAllPayments();
        return ResponseEntity.ok(result);
    }
}
