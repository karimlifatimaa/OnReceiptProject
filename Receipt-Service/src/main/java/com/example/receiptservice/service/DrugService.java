package com.example.receiptservice.service;

import com.example.receiptservice.dto.drug.DrugCreateDto;
import com.example.receiptservice.dto.drug.DrugResponseDto;
import com.example.receiptservice.dto.drug.DrugUpdateDto;

import java.util.List;
import java.util.UUID;

public interface DrugService {
    DrugResponseDto createDrug(DrugCreateDto drugCreateDto);
    DrugResponseDto getDrugById(UUID id);
    List<DrugResponseDto> getAllDrugs();
    DrugResponseDto updateDrug(UUID id, DrugUpdateDto drugUpdateDto);
    void deleteDrug(UUID id);
}