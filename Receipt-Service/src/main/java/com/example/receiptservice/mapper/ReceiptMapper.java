package com.example.receiptservice.mapper;

import com.example.receiptservice.dto.receipt.ReceiptCreateDto;
import com.example.receiptservice.dto.receipt.ReceiptResponseDto;
import com.example.receiptservice.dto.receiptItem.ReceiptItemCreateDto;
import com.example.receiptservice.dto.receiptItem.ReceiptItemResponseDto;
import com.example.receiptservice.entity.Receipt;
import com.example.receiptservice.entity.ReceiptItem;
import com.example.receiptservice.entity.ReceiptStatus;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptMapper {

    private final ReceiptItemMapper itemMapper;

    public ReceiptMapper(ReceiptItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public Receipt toEntity(ReceiptCreateDto dto) {
        if (dto == null) return null;
        Receipt r = new Receipt();
        r.setSerialNumber(dto.getSerialNumber());
        r.setCitizenId(dto.getCitizenId());
        r.setDoctorId(dto.getDoctorId());
        r.setHospitalId(dto.getHospitalId());
        r.setFundingSource(dto.getFundingSource());
        r.setQrCodePayload(dto.getQrCodePayload());
        r.setIssueDate(OffsetDateTime.now());
        r.setStatus(ReceiptStatus.DRAFT);

        if (dto.getItems() != null && !dto.getItems().isEmpty()) {
            List<ReceiptItem> items = new ArrayList<>();
            for (ReceiptItemCreateDto ic : dto.getItems()) {
                ReceiptItem it = itemMapper.toEntity(ic);
                it.setReceipt(r);
                items.add(it);
            }
            r.setItems(items);
        }
        return r;
    }

    public ReceiptResponseDto toDto(Receipt entity) {
        if (entity == null) return null;
        ReceiptResponseDto dto = new ReceiptResponseDto();
        dto.setId(entity.getId());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setCitizenId(entity.getCitizenId());
        dto.setDoctorId(entity.getDoctorId());
        dto.setHospitalId(entity.getHospitalId());
        dto.setStatus(entity.getStatus());
        dto.setFundingSource(entity.getFundingSource());
        dto.setQrCodePayload(entity.getQrCodePayload());
        dto.setIssueDate(entity.getIssueDate());
        dto.setFulfilledAt(entity.getFulfilledAt());
        dto.setFulfilledByPharmacyId(entity.getFulfilledByPharmacyId());

        if (entity.getItems() != null) {
            List<ReceiptItemResponseDto> items = new ArrayList<>();
            for (ReceiptItem it : entity.getItems()) {
                items.add(itemMapper.toDto(it));
            }
            dto.setItems(items);
        }
        return dto;
    }

    public List<ReceiptItem> toReceiptItemEntities(List<ReceiptItemCreateDto> dtos) {
        return dtos.stream()
                .map(itemMapper::toEntity)
                .collect(Collectors.toList());
    }
}