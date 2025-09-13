package com.example.receiptservice.service;

import com.example.receiptservice.dto.ReceiptCreateDto;
import com.example.receiptservice.dto.ReceiptDto;
import com.example.receiptservice.dto.ReceiptUpdateDto;

import java.util.List;

public interface ReceiptService {
    ReceiptDto createReceipt(ReceiptCreateDto receiptCreateDto);
    ReceiptDto getReceiptById(Long id);
    List<ReceiptDto> getAllReceipts();
    ReceiptDto updateReceipt(Long id, ReceiptUpdateDto receiptUpdateDto);
    void deleteReceipt(Long id);
}
