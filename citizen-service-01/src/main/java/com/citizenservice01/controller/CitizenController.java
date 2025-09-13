package com.citizenservice01.controller;

import com.citizenservice01.module.dto.CitizenRequestDto;
import com.citizenservice01.module.dto.CitizenResponseDto;
import com.citizenservice01.module.dto.CitizenUpdateDto;
import com.citizenservice01.service.CitizenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/citizens")
@RequiredArgsConstructor
public class CitizenController {

    private final CitizenService citizenService;

    @PostMapping
    public ResponseEntity<CitizenResponseDto> createCitizen(@Valid @RequestBody CitizenRequestDto requestDto) {
        CitizenResponseDto responseDto = citizenService.createCitizen(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenResponseDto> getCitizenById(@PathVariable UUID id) {
        CitizenResponseDto responseDto = citizenService.getCitizenById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/fin/{finCode}")
    public ResponseEntity<CitizenResponseDto> getCitizenByFinCode(@PathVariable String finCode) {
        CitizenResponseDto responseDto = citizenService.getCitizenByFinCode(finCode);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitizenResponseDto> updateCitizen(@PathVariable UUID id,
                                                            @Valid @RequestBody CitizenUpdateDto updateDto) {
        CitizenResponseDto responseDto = citizenService.updateCitizen(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCitizen(@PathVariable UUID id) {
        citizenService.deleteCitizen(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CitizenResponseDto>> getAllCitizens(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "lastName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<CitizenResponseDto> citizens = citizenService.getAllCitizens(pageable);
        return ResponseEntity.ok(citizens);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CitizenResponseDto>> searchCitizens(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String finCode,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "lastName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<CitizenResponseDto> citizens = citizenService.searchCitizens(
                firstName, lastName, finCode, isActive, pageable);
        return ResponseEntity.ok(citizens);
    }
}
