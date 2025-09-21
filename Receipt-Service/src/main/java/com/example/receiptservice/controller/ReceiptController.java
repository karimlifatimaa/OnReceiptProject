package com.example.receiptservice.controller;

import com.example.receiptservice.dto.receipt.ReceiptCreateDto;
import com.example.receiptservice.dto.receipt.ReceiptResponseDto;
import com.example.receiptservice.dto.receipt.ReceiptUpdateDto;
import com.example.receiptservice.service.ReceiptService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
@Slf4j
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping
    public ResponseEntity<ReceiptResponseDto> createReceipt(@Valid @RequestBody ReceiptCreateDto receiptCreateDto) {
        log.info("API call to create a new receipt with serial number: {}", receiptCreateDto.getSerialNumber());
        ReceiptResponseDto createdReceipt = receiptService.createReceipt(receiptCreateDto);
        return new ResponseEntity<>(createdReceipt, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptResponseDto> getReceiptById(@PathVariable UUID id) {
        log.info("API call to get receipt with ID: {}", id);
        ReceiptResponseDto receipt = receiptService.getReceiptById(id);
        return ResponseEntity.ok(receipt);
    }

    @GetMapping
    public ResponseEntity<List<ReceiptResponseDto>> getAllReceipts() {
        log.info("API call to get all receipts.");
        List<ReceiptResponseDto> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceiptResponseDto> updateReceipt(@PathVariable UUID id, @Valid @RequestBody ReceiptUpdateDto receiptUpdateDto) {
        log.info("API call to update receipt with ID: {}", id);
        ReceiptResponseDto updatedReceipt = receiptService.updateReceipt(id, receiptUpdateDto);
        return ResponseEntity.ok(updatedReceipt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable UUID id) {
        log.info("API call to delete receipt with ID: {}", id);
        receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }
}