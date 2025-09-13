package com.example.receiptservice.controller;


import com.example.receiptservice.dto.ReceiptItemCreateDto;
import com.example.receiptservice.dto.ReceiptItemDto;
import com.example.receiptservice.dto.ReceiptItemUpdateDto;
import com.example.receiptservice.service.ReceiptItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipt-items")
@RequiredArgsConstructor
@Slf4j
public class ReceiptItemController {

    private final ReceiptItemService receiptItemService;

    @PostMapping
    public ResponseEntity<ReceiptItemDto> createReceiptItem(@RequestBody ReceiptItemCreateDto receiptItemCreateDto) {
        log.info("API request to create receipt item: {}", receiptItemCreateDto);
        ReceiptItemDto createdReceiptItem = receiptItemService.createReceiptItem(receiptItemCreateDto);
        return ResponseEntity.ok(createdReceiptItem);
    }

    @GetMapping
    public ResponseEntity<List<ReceiptItemDto>> getAllReceiptItems() {
        log.info("API request to fetch all receipt items");
        List<ReceiptItemDto> receiptItems = receiptItemService.getAllReceiptItems();
        return ResponseEntity.ok(receiptItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptItemDto> getReceiptItemById(@PathVariable Long id) {
        log.info("API request to fetch receipt item with id: {}", id);
        ReceiptItemDto receiptItem = receiptItemService.getReceiptItemById(id);
        return ResponseEntity.ok(receiptItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceiptItemDto> updateReceiptItem(
            @PathVariable Long id,
            @RequestBody ReceiptItemUpdateDto receiptItemUpdateDto) {
        log.info("API request to update receipt item with id: {} data: {}", id, receiptItemUpdateDto);
        ReceiptItemDto updatedReceiptItem = receiptItemService.updateReceiptItem(id, receiptItemUpdateDto);
        return ResponseEntity.ok(updatedReceiptItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceiptItem(@PathVariable Long id) {
        log.info("API request to delete receipt item with id: {}", id);
        receiptItemService.deleteReceiptItem(id);
        return ResponseEntity.noContent().build();
    }


}