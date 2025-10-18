package com.example.ecommerce.controller;

import com.example.ecommerce.dto.PaymentDto.PaymentInDto;
import com.example.ecommerce.dto.PaymentDto.PaymentOutDto;
import com.example.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService payService;
    @PostMapping("/payment")
    public ResponseEntity<PaymentOutDto> createPayment(@RequestBody PaymentInDto dto){
        PaymentOutDto result = payService.createPayment(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/payment")
    public ResponseEntity<List<PaymentOutDto>> getAllPayments(){
        List<PaymentOutDto> result = payService.getAllPayments();
        return ResponseEntity.ok(result);
    }
}
