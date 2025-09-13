package com.example.receiptservice.service;

import com.example.receiptservice.dto.ReceiptItemCreateDto;
import com.example.receiptservice.dto.ReceiptItemDto;
import com.example.receiptservice.dto.ReceiptItemUpdateDto;

import java.util.List;

public interface ReceiptItemService {
    ReceiptItemDto createReceiptItem(ReceiptItemCreateDto receiptItemCreateDto);
    ReceiptItemDto getReceiptItemById(Long id);
    List<ReceiptItemDto> getAllReceiptItems();
    ReceiptItemDto updateReceiptItem(Long id, ReceiptItemUpdateDto receiptItemUpdateDto);
    void deleteReceiptItem(Long id);
}
