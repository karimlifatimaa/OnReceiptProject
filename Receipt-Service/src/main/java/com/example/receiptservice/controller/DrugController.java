package com.example.receiptservice.controller;

import com.example.receiptservice.dto.DrugCreateDto;
import com.example.receiptservice.dto.DrugDto;
import com.example.receiptservice.dto.DrugUpdateDto;
import com.example.receiptservice.service.DrugService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.UUID;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/drugs")
public class DrugController {

    private final DrugService drugService;

    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @PostMapping
    public ResponseEntity<DrugDto> createDrug(@Valid @RequestBody DrugCreateDto drugCreateDto) {
        log.info("createDrug request received: {}", drugCreateDto);
        DrugDto createdDrug = drugService.createDrug(drugCreateDto);
        log.info("createDrug response: {}", createdDrug);
        return new ResponseEntity<>(createdDrug, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugDto> getDrugById(@PathVariable UUID id) {
        log.info("getDrugById request received for id: {}", id);
        DrugDto drugDto = drugService.getDrugById(id);
        log.info("getDrugById response: {}", drugDto);
        return ResponseEntity.ok(drugDto);
    }

    @GetMapping
    public ResponseEntity<List<DrugDto>> getAllDrugs() {
        log.info("getAllDrugs request received");
        List<DrugDto> drugs = drugService.getAllDrugs();
        log.info("getAllDrugs response size: {}", drugs.size());
        return ResponseEntity.ok(drugs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugDto> updateDrug(@PathVariable UUID id, @Valid @RequestBody DrugUpdateDto drugUpdateDto) {
        log.info("updateDrug request received for id: {}: {}", id, drugUpdateDto);
        DrugDto updatedDrug = drugService.updateDrug(id, drugUpdateDto);
        log.info("updateDrug response: {}", updatedDrug);
        return ResponseEntity.ok(updatedDrug);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable UUID id) {
        log.info("deleteDrug request received for id: {}", id);
        drugService.deleteDrug(id);
        log.info("deleteDrug successful for id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
