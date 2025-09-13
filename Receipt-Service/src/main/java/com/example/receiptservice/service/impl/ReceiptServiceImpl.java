package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.ReceiptCreateDto;
import com.example.receiptservice.dto.ReceiptDto;
import com.example.receiptservice.dto.ReceiptUpdateDto;
import com.example.receiptservice.entity.Receipt;
import com.example.receiptservice.repository.ReceiptRepository;
import com.example.receiptservice.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Override
    public ReceiptDto createReceipt(ReceiptCreateDto receiptCreateDto) {
        log.info("Creating new receipt with data: {}", receiptCreateDto);
        Receipt receipt = new Receipt();
        
        // Map from ReceiptCreateDto to Receipt entity
        receipt.setSerialNumber(receiptCreateDto.getSerialNumber());
        receipt.setCitizenId(receiptCreateDto.getCitizenId());
        receipt.setDoctorId(receiptCreateDto.getDoctorId());
        receipt.setHospitalId(receiptCreateDto.getHospitalId());
        receipt.setStatus(receiptCreateDto.getStatus());
        receipt.setFundingSource(receiptCreateDto.getFundingSource());
        receipt.setQrCodePayload(receiptCreateDto.getQrCodePayload());
        receipt.setIssueDate(receiptCreateDto.getIssueDate());

        Receipt savedReceipt = receiptRepository.save(receipt);
        log.info("Successfully created receipt with id: {}", savedReceipt.getId());
        return convertToDto(savedReceipt);
    }

    @Override
    public ReceiptDto getReceiptById(Long id) {
        log.info("Fetching receipt with id: {}", id);
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Receipt not found with id: {}", id);
                    return new RuntimeException("Receipt not found with id: " + id);
                });
        log.info("Successfully fetched receipt with id: {}", id);
        return convertToDto(receipt);
    }

    @Override
    public List<ReceiptDto> getAllReceipts() {
        log.info("Fetching all receipts");
        List<Receipt> receipts = receiptRepository.findAll();
        log.info("Found {} receipts", receipts.size());
        return receipts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptDto updateReceipt(Long id, ReceiptUpdateDto receiptUpdateDto) {
        log.info("Updating receipt with id: {} with data: {}", id, receiptUpdateDto);
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Receipt not found with id: {}", id);
                    return new RuntimeException("Receipt not found with id: " + id);
                });

        // Partial update from ReceiptUpdateDto
        if (receiptUpdateDto.getSerialNumber() != null) {
            receipt.setSerialNumber(receiptUpdateDto.getSerialNumber());
        }
        if (receiptUpdateDto.getCitizenId() != null) {
            receipt.setCitizenId(receiptUpdateDto.getCitizenId());
        }
        if (receiptUpdateDto.getDoctorId() != null) {
            receipt.setDoctorId(receiptUpdateDto.getDoctorId());
        }
        if (receiptUpdateDto.getHospitalId() != null) {
            receipt.setHospitalId(receiptUpdateDto.getHospitalId());
        }
        if (receiptUpdateDto.getStatus() != null) {
            receipt.setStatus(receiptUpdateDto.getStatus());
        }
        if (receiptUpdateDto.getFundingSource() != null) {
            receipt.setFundingSource(receiptUpdateDto.getFundingSource());
        }
        if (receiptUpdateDto.getQrCodePayload() != null) {
            receipt.setQrCodePayload(receiptUpdateDto.getQrCodePayload());
        }
        if (receiptUpdateDto.getIssueDate() != null) {
            receipt.setIssueDate(receiptUpdateDto.getIssueDate());
        }
        if (receiptUpdateDto.getFulfilledAt() != null) {
            receipt.setFulfilledAt(receiptUpdateDto.getFulfilledAt());
        }
        if (receiptUpdateDto.getFulfilledByPharmacyId() != null) {
            receipt.setFulfilledByPharmacyId(receiptUpdateDto.getFulfilledByPharmacyId());
        }

        Receipt updatedReceipt = receiptRepository.save(receipt);
        log.info("Successfully updated receipt with id: {}", updatedReceipt.getId());
        return convertToDto(updatedReceipt);
    }

    @Override
    public void deleteReceipt(Long id) {
        log.info("Deleting receipt with id: {}", id);
        if (!receiptRepository.existsById(id)) {
            log.error("Receipt with id: {} not found for deletion", id);
            throw new RuntimeException("Receipt not found with id: " + id);
        }
        receiptRepository.deleteById(id);
        log.info("Successfully deleted receipt with id: {}", id);
    }

    private ReceiptDto convertToDto(Receipt receipt) {
        ReceiptDto dto = new ReceiptDto();
        dto.setId(receipt.getId());
        dto.setSerialNumber(receipt.getSerialNumber());
        dto.setCitizenId(receipt.getCitizenId());
        dto.setDoctorId(receipt.getDoctorId());
        dto.setHospitalId(receipt.getHospitalId());
        dto.setStatus(receipt.getStatus());
        dto.setFundingSource(receipt.getFundingSource());
        dto.setQrCodePayload(receipt.getQrCodePayload());
        dto.setIssueDate(receipt.getIssueDate());
        dto.setFulfilledAt(receipt.getFulfilledAt());
        dto.setFulfilledByPharmacyId(receipt.getFulfilledByPharmacyId());
        dto.setCreatedAt(receipt.getCreatedAt());
        dto.setUpdatedAt(receipt.getUpdatedAt());
        return dto;
    }
}
