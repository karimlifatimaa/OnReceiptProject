package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.receipt.ReceiptCreateDto;
import com.example.receiptservice.dto.receipt.ReceiptResponseDto;
import com.example.receiptservice.dto.receipt.ReceiptUpdateDto;
import com.example.receiptservice.entity.Receipt;
import com.example.receiptservice.entity.ReceiptItem;
import com.example.receiptservice.exception.BusinessException;
import com.example.receiptservice.exception.ErrorCode;
import com.example.receiptservice.mapper.ReceiptMapper;
import com.example.receiptservice.repository.ReceiptRepository;
import com.example.receiptservice.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptMapper receiptMapper;

    @Override
    @Transactional
    public ReceiptResponseDto createReceipt(ReceiptCreateDto receiptCreateDto) {
        log.info("Creating a new receipt with serial number: {}", receiptCreateDto.getSerialNumber());

        if (receiptRepository.findBySerialNumber(receiptCreateDto.getSerialNumber()).isPresent()) {
            log.warn("Attempted to create a receipt with a duplicate serial number: {}", receiptCreateDto.getSerialNumber());
            throw new BusinessException(ErrorCode.DUPLICATE_SERIAL);
        }

        Receipt receipt = receiptMapper.toEntity(receiptCreateDto);
        if (receiptCreateDto.getItems() != null && !receiptCreateDto.getItems().isEmpty()) {
            List<ReceiptItem> receiptItems = receiptMapper.toReceiptItemEntities(receiptCreateDto.getItems());
            receiptItems.forEach(item -> item.setReceipt(receipt));
            receipt.setItems(receiptItems);
        }

        Receipt savedReceipt = receiptRepository.save(receipt);
        log.info("Successfully created receipt with ID: {}", savedReceipt.getId());
        return receiptMapper.toDto(savedReceipt);
    }

    @Override
    public ReceiptResponseDto getReceiptById(UUID id) {
        log.info("Fetching receipt with ID: {}", id);
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Attempted to fetch non-existent receipt with ID: {}", id);
                    return new BusinessException(ErrorCode.RECEIPT_NOT_FOUND);
                });
        log.info("Successfully fetched receipt with ID: {}", id);
        return receiptMapper.toDto(receipt);
    }

    @Override
    public List<ReceiptResponseDto> getAllReceipts() {
        log.info("Fetching all receipts.");
        List<ReceiptResponseDto> receipts = receiptRepository.findAll().stream()
                .map(receiptMapper::toDto)
                .collect(Collectors.toList());
        log.info("Successfully fetched {} receipts.", receipts.size());
        return receipts;
    }

    @Override
    @Transactional
    public ReceiptResponseDto updateReceipt(UUID id, ReceiptUpdateDto receiptUpdateDto) {
        log.info("Updating receipt with ID: {}", id);
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Attempted to update non-existent receipt with ID: {}", id);
                    return new BusinessException(ErrorCode.RECEIPT_NOT_FOUND);
                });

        if (receiptUpdateDto.getStatus() == null) {
            log.warn("Attempted to update receipt status with a null value for ID: {}", id);
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        receipt.setStatus(receiptUpdateDto.getStatus());
        if (receiptUpdateDto.getFulfilledByPharmacyId() != null) {
            receipt.setFulfilledByPharmacyId(receiptUpdateDto.getFulfilledByPharmacyId());
        }

        Receipt updatedReceipt = receiptRepository.save(receipt);
        log.info("Successfully updated status to {} for receipt with ID: {}", updatedReceipt.getStatus(), updatedReceipt.getId());
        return receiptMapper.toDto(updatedReceipt);
    }

    @Override
    @Transactional
    public void deleteReceipt(UUID id) {
        log.info("Deleting receipt with ID: {}", id);
        if (!receiptRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent receipt with ID: {}", id);
            throw new BusinessException(ErrorCode.RECEIPT_NOT_FOUND);
        }
        receiptRepository.deleteById(id);
        log.info("Successfully deleted receipt with ID: {}", id);
    }
}