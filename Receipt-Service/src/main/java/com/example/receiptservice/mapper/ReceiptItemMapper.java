package com.example.receiptservice.mapper;


import com.example.receiptservice.dto.receiptItem.ReceiptItemCreateDto;
import com.example.receiptservice.dto.receiptItem.ReceiptItemResponseDto;
import com.example.receiptservice.dto.receiptItem.ReceiptItemUpdateDto;
import com.example.receiptservice.entity.ReceiptItem;
import org.springframework.stereotype.Component;



@Component
public class ReceiptItemMapper {


    public ReceiptItem toEntity(ReceiptItemCreateDto dto) {
        if (dto == null) return null;
        ReceiptItem item = new ReceiptItem();
        item.setDrugId(dto.getDrugId());
        item.setDosage(dto.getDosage());
        item.setQuantity(dto.getQuantity());
        item.setInstructions(dto.getInstructions());
        return item;
    }


    public ReceiptItemResponseDto toDto(ReceiptItem entity) {
        if (entity == null) return null;
        ReceiptItemResponseDto dto = new ReceiptItemResponseDto();
        dto.setId(entity.getId());
        dto.setDrugId(entity.getDrugId());
        dto.setDosage(entity.getDosage());
        dto.setQuantity(entity.getQuantity());
        dto.setInstructions(entity.getInstructions());
        return dto;
    }


    public void updateEntityFromDto(ReceiptItemUpdateDto dto, ReceiptItem entity) {
        if (dto == null || entity == null) return;
        if (dto.getDosage() != null) entity.setDosage(dto.getDosage());
        if (dto.getQuantity() != null) entity.setQuantity(dto.getQuantity());
        if (dto.getInstructions() != null) entity.setInstructions(dto.getInstructions());
    }
}