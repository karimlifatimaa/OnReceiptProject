package com.example.receiptservice.controller;

import com.example.receiptservice.dto.*;
import com.example.receiptservice.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
@Slf4j
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping
    public ResponseEntity<ReceiptDto> createReceipt(@RequestBody ReceiptCreateDto receiptCreateDto) {
        log.info("API request to create receipt: {}", receiptCreateDto);
        ReceiptDto createdReceipt = receiptService.createReceipt(receiptCreateDto);
        return ResponseEntity.ok(createdReceipt);
    }

    @GetMapping
    public ResponseEntity<List<ReceiptDto>> getAllReceipts() {
        log.info("API request to fetch all receipts");
        List<ReceiptDto> receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptDto> getReceiptById(@PathVariable Long id) {
        log.info("API request to fetch receipt with id: {}", id);
        ReceiptDto receipt = receiptService.getReceiptById(id);
        return ResponseEntity.ok(receipt);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceiptDto> updateReceipt(
            @PathVariable Long id,
            @RequestBody ReceiptUpdateDto receiptUpdateDto) {
        log.info("API request to update receipt with id: {} data: {}", id, receiptUpdateDto);
        ReceiptDto updatedReceipt = receiptService.updateReceipt(id, receiptUpdateDto);
        return ResponseEntity.ok(updatedReceipt);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable Long id) {
        log.info("API request to delete receipt with id: {}", id);
        receiptService.deleteReceipt(id);
        return ResponseEntity.noContent().build();
    }

}