package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.DrugCreateDto;
import com.example.receiptservice.dto.DrugDto;
import com.example.receiptservice.dto.DrugUpdateDto;
import com.example.receiptservice.entity.Drug;
import com.example.receiptservice.repository.DrugRepository;
import com.example.receiptservice.service.DrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;

    @Override
    public DrugDto createDrug(DrugCreateDto drugCreateDto) {
        log.info("Creating drug with details: {}", drugCreateDto);
        Drug drug = new Drug();
        drug.setTradeName(drugCreateDto.getTradeName());
        drug.setIsControlledSubstance(drugCreateDto.getIsControlledSubstance());
        drug.setRequiresPrescription(drugCreateDto.getRequiresPrescription());
        drug.setIsActive(true);
        Drug savedDrug = drugRepository.save(drug);
        log.info("Saved drug with id: {}", savedDrug.getId());
        return convertToDto(savedDrug);
    }

    @Override
    public DrugDto getDrugById(UUID id) {
        log.info("Fetching drug with id: {}", id);
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + id));
        log.info("Found drug: {}", drug);
        return convertToDto(drug);
    }

    @Override
    public List<DrugDto> getAllDrugs() {
        log.info("Fetching all drugs");
        List<Drug> drugs = drugRepository.findAll();
        log.info("Found {} drugs", drugs.size());
        return drugs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DrugDto updateDrug(UUID id, DrugUpdateDto drugUpdateDto) {
        log.info("Updating drug with id: {} and details: {}", id, drugUpdateDto);
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + id));

        if (drugUpdateDto.getTradeName() != null) {
            drug.setTradeName(drugUpdateDto.getTradeName());
        }
        if (drugUpdateDto.getIsControlledSubstance() != null) {
            drug.setIsControlledSubstance(drugUpdateDto.getIsControlledSubstance());
        }
        if (drugUpdateDto.getRequiresPrescription() != null) {
            drug.setRequiresPrescription(drugUpdateDto.getRequiresPrescription());
        }
        if (drugUpdateDto.getIsActive() != null) {
            drug.setIsActive(drugUpdateDto.getIsActive());
        }

        Drug updatedDrug = drugRepository.save(drug);
        log.info("Updated drug with id: {}", updatedDrug.getId());
        return convertToDto(updatedDrug);
    }

    @Override
    public void deleteDrug(UUID id) {
        log.info("Deleting drug with id: {}", id);
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drug not found with id: " + id));
        drugRepository.delete(drug);
        log.info("Deleted drug with id: {}", id);
    }

    private DrugDto convertToDto(Drug drug) {
        DrugDto drugDto = new DrugDto();
        drugDto.setId(drug.getId());
        drugDto.setTradeName(drug.getTradeName());
        drugDto.setIsControlledSubstance(drug.getIsControlledSubstance());
        drugDto.setRequiresPrescription(drug.getRequiresPrescription());
        drugDto.setIsActive(drug.getIsActive());
        drugDto.setCreatedAt(drug.getCreatedAt());
        drugDto.setUpdatedAt(drug.getUpdatedAt());
        return drugDto;
    }
}