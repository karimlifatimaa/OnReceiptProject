package com.example.receiptservice.service;

import com.example.receiptservice.dto.receipt.ReceiptCreateDto;
import com.example.receiptservice.dto.receipt.ReceiptResponseDto;
import com.example.receiptservice.dto.receipt.ReceiptUpdateDto;

import java.util.List;
import java.util.UUID;

public interface ReceiptService {
    ReceiptResponseDto createReceipt(ReceiptCreateDto receiptCreateDto);
    ReceiptResponseDto getReceiptById(UUID id);
    List<ReceiptResponseDto> getAllReceipts();
    ReceiptResponseDto updateReceipt(UUID id, ReceiptUpdateDto receiptUpdateDto);
    void deleteReceipt(UUID id);
}