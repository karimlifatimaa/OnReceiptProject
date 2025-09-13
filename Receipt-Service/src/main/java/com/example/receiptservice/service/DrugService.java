package com.example.receiptservice.service;

import com.example.receiptservice.dto.DrugCreateDto;
import com.example.receiptservice.dto.DrugDto;
import com.example.receiptservice.dto.DrugUpdateDto;

import java.util.List;
import java.util.UUID;

public interface DrugService {
    DrugDto createDrug(DrugCreateDto drugCreateDto);
    DrugDto getDrugById(UUID id);
    List<DrugDto> getAllDrugs();
    DrugDto updateDrug(UUID id, DrugUpdateDto drugUpdateDto);
    void deleteDrug(UUID id);
}
