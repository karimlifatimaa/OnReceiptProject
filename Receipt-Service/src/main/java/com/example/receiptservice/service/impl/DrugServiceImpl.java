package com.example.receiptservice.service.impl;

import com.example.receiptservice.dto.drug.DrugCreateDto;
import com.example.receiptservice.dto.drug.DrugResponseDto;
import com.example.receiptservice.dto.drug.DrugUpdateDto;
import com.example.receiptservice.entity.Drug;
import com.example.receiptservice.exception.BusinessException;
import com.example.receiptservice.exception.ErrorCode;
import com.example.receiptservice.mapper.DrugMapper;
import com.example.receiptservice.repository.DrugRepository;
import com.example.receiptservice.service.DrugService;
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
public class DrugServiceImpl implements DrugService {

    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;

    @Override
    @Transactional
    public DrugResponseDto createDrug(DrugCreateDto drugCreateDto) {
        log.info("Creating new drug with trade name: {}", drugCreateDto.getTradeName());
        Drug drug = drugMapper.toEntity(drugCreateDto);
        Drug savedDrug = drugRepository.save(drug);
        log.info("Successfully created drug with ID: {}", savedDrug.getId());
        return drugMapper.toDto(savedDrug);
    }

    @Override
    public DrugResponseDto getDrugById(UUID id) {
        log.info("Fetching drug with ID: {}", id);
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Attempted to fetch non-existent drug with ID: {}", id);
                    return new BusinessException(ErrorCode.DRUG_NOT_FOUND);
                });
        log.info("Successfully fetched drug with ID: {}", id);
        return drugMapper.toDto(drug);
    }

    @Override
    public List<DrugResponseDto> getAllDrugs() {
        log.info("Fetching all drugs.");
        List<DrugResponseDto> drugs = drugRepository.findAll().stream()
                .map(drugMapper::toDto)
                .collect(Collectors.toList());
        log.info("Successfully fetched {} drugs.", drugs.size());
        return drugs;
    }

    @Override
    @Transactional
    public DrugResponseDto updateDrug(UUID id, DrugUpdateDto drugUpdateDto) {
        log.info("Updating drug with ID: {}", id);
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Attempted to update non-existent drug with ID: {}", id);
                    return new BusinessException(ErrorCode.DRUG_NOT_FOUND);
                });

        drugMapper.updateEntityFromDto(drugUpdateDto, drug);
        Drug updatedDrug = drugRepository.save(drug);
        log.info("Successfully updated drug with ID: {}", updatedDrug.getId());
        return drugMapper.toDto(updatedDrug);
    }

    @Override
    @Transactional
    public void deleteDrug(UUID id) {
        log.info("Deleting drug with ID: {}", id);
        if (!drugRepository.existsById(id)) {
            log.warn("Attempted to delete non-existent drug with ID: {}", id);
            throw new BusinessException(ErrorCode.DRUG_NOT_FOUND);
        }
        drugRepository.deleteById(id);
        log.info("Successfully deleted drug with ID: {}", id);
    }
}