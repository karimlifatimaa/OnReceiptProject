package com.example.receiptservice.controller;

import com.example.receiptservice.dto.drug.DrugCreateDto;
import com.example.receiptservice.dto.drug.DrugResponseDto;
import com.example.receiptservice.dto.drug.DrugUpdateDto;
import com.example.receiptservice.service.DrugService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
@Slf4j
public class DrugController {

    private final DrugService drugService;

    @PostMapping
    public ResponseEntity<DrugResponseDto> createDrug(@Valid @RequestBody DrugCreateDto drugCreateDto) {
        log.info("API call to create a new drug with name: {}", drugCreateDto.getTradeName());
        DrugResponseDto createdDrug = drugService.createDrug(drugCreateDto);
        return new ResponseEntity<>(createdDrug, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugResponseDto> getDrugById(@PathVariable UUID id) {
        log.info("API call to get drug with ID: {}", id);
        DrugResponseDto drug = drugService.getDrugById(id);
        return ResponseEntity.ok(drug);
    }

    @GetMapping
    public ResponseEntity<List<DrugResponseDto>> getAllDrugs() {
        log.info("API call to get all drugs.");
        List<DrugResponseDto> drugs = drugService.getAllDrugs();
        return ResponseEntity.ok(drugs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugResponseDto> updateDrug(@PathVariable UUID id, @Valid @RequestBody DrugUpdateDto drugUpdateDto) {
        log.info("API call to update drug with ID: {}", id);
        DrugResponseDto updatedDrug = drugService.updateDrug(id, drugUpdateDto);
        return ResponseEntity.ok(updatedDrug);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable UUID id) {
        log.info("API call to delete drug with ID: {}", id);
        drugService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }
}