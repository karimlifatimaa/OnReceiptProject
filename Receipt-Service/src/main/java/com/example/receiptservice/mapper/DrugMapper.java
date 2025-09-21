package com.example.receiptservice.mapper;


import com.example.receiptservice.entity.Drug;
import com.example.receiptservice.dto.drug.DrugCreateDto;
import com.example.receiptservice.dto.drug.DrugResponseDto;
import com.example.receiptservice.dto.drug.DrugUpdateDto;
import org.springframework.stereotype.Component;


@Component
public class DrugMapper {


    public Drug toEntity(DrugCreateDto dto) {
        if (dto == null) return null;
        Drug drug = new Drug();
        drug.setTradeName(dto.getTradeName());
        drug.setControlledSubstance(dto.isControlledSubstance());
        drug.setRequiresPrescription(dto.isRequiresPrescription());
        return drug;
    }


    public DrugResponseDto toDto(Drug entity) {
        if (entity == null) return null;
        DrugResponseDto dto = new DrugResponseDto();
        dto.setId(entity.getId());
        dto.setTradeName(entity.getTradeName());
        dto.setControlledSubstance(entity.isControlledSubstance());
        dto.setRequiresPrescription(entity.isRequiresPrescription());
        dto.setActive(entity.isActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }


    public void updateEntityFromDto(DrugUpdateDto dto, Drug entity) {
        if (dto == null || entity == null) return;
        if (dto.getTradeName() != null) entity.setTradeName(dto.getTradeName());
        if (dto.getIsControlledSubstance() != null) entity.setControlledSubstance(dto.getIsControlledSubstance());
        if (dto.getRequiresPrescription() != null) entity.setRequiresPrescription(dto.getRequiresPrescription());
        if (dto.getIsActive() != null) entity.setActive(dto.getIsActive());
    }
}