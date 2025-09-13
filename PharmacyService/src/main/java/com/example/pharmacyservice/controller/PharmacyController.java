package com.example.pharmacyservice.controller;

import com.example.pharmacyservice.dto.PharmacyDto;
import com.example.pharmacyservice.service.PharmacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pharmacies")
@RequiredArgsConstructor
public class PharmacyController {

    private final PharmacyService pharmacyService;

    /**
     * Yeni bir aptek yaradır.
     * Request body-dən gələn məlumatlar @Valid ilə yoxlanılır.
     * @param pharmacyDto yaradılacaq aptek məlumatları.
     * @return Yaradılmış aptek və HTTP 201 (Created) statusu.
     */
    @PostMapping
    public ResponseEntity<PharmacyDto> createPharmacy(@Valid @RequestBody PharmacyDto pharmacyDto) {
        PharmacyDto createdPharmacy = pharmacyService.createPharmacy(pharmacyDto);
        return new ResponseEntity<>(createdPharmacy, HttpStatus.CREATED);
    }

    /**
     * Mövcud apteki ID-yə görə yeniləyir.
     * @param id Yenilənəcək aptekin UUID-si.
     * @param pharmacyDto Yeni məlumatlar.
     * @return Yenilənmiş aptek və HTTP 200 (OK) statusu.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PharmacyDto> updatePharmacy(@PathVariable UUID id, @Valid @RequestBody PharmacyDto pharmacyDto) {
        PharmacyDto updatedPharmacy = pharmacyService.updatePharmacy(id, pharmacyDto);
        return ResponseEntity.ok(updatedPharmacy);
    }

    /**
     * Bir apteki ID-yə görə gətirir.
     * @param id Axtarılan aptekin UUID-si.
     * @return Tapılarsa aptek və HTTP 200 (OK), tapılmazsa HTTP 404 (Not Found) statusu.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PharmacyDto> getPharmacyById(@PathVariable UUID id) {
        return pharmacyService.getPharmacyById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Bütün aptekləri siyahı şəklində qaytarır.
     * @return Apteklərin siyahısı və HTTP 200 (OK) statusu.
     */
    @GetMapping("/all")
    public ResponseEntity<List<PharmacyDto>> getAllPharmacies() {
        List<PharmacyDto> pharmacies = pharmacyService.getAllPharmacies();
        return ResponseEntity.ok(pharmacies);
    }

    /**
     * Bütün aptekləri səhifələmə (pagination) ilə qaytarır.
     * Nümunə istifadə: /api/v1/pharmacies?page=0&size=10&sort=name,asc
     * @param pageable Səhifə nömrəsi, ölçüsü və sıralama məlumatları.
     * @return Səhifələnmiş aptek məlumatları və HTTP 200 (OK) statusu.
     */
    @GetMapping
    public ResponseEntity<Page<PharmacyDto>> getAllPharmaciesPaginated(Pageable pageable) {
        Page<PharmacyDto> pharmaciesPage = pharmacyService.getAllPharmacies(pageable);
        return ResponseEntity.ok(pharmaciesPage);
    }

    /**
     * Yalnız aktiv olan aptekləri qaytarır.
     * @return Aktiv apteklərin siyahısı və HTTP 200 (OK) statusu.
     */
    @GetMapping("/active")
    public ResponseEntity<List<PharmacyDto>> getActivePharmacies() {
        List<PharmacyDto> activePharmacies = pharmacyService.getActivePharmacies();
        return ResponseEntity.ok(activePharmacies);
    }

    /**
     * Bir apteki ID-yə görə silir.
     * @param id Silinəcək aptekin UUID-si.
     * @return HTTP 204 (No Content) statusu.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacy(@PathVariable UUID id) {
        pharmacyService.deletePharmacy(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Bir apteki ID-yə görə deaktiv edir.
     * @param id Deaktiv ediləcək aptekin UUID-si.
     * @return HTTP 200 (OK) statusu.
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivatePharmacy(@PathVariable UUID id) {
        pharmacyService.deactivatePharmacy(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Bir apteki ID-yə görə aktivləşdirir.
     * @param id Aktivləşdiriləcək aptekin UUID-si.
     * @return HTTP 200 (OK) statusu.
     */
    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activatePharmacy(@PathVariable UUID id) {
        pharmacyService.activatePharmacy(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Verilmiş lisenziya nömrəsinin mövcud olub-olmadığını yoxlayır.
     * @param licenseNumber Yoxlanılacaq lisenziya nömrəsi.
     * @return `true` və ya `false` cavabı və HTTP 200 (OK) statusu.
     */
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkLicenseExists(@RequestParam String licenseNumber) {
        boolean exists = pharmacyService.existsByLicenseNumber(licenseNumber);
        return ResponseEntity.ok(exists);
    }

    /**
     * Aktiv apteklərin sayını qaytarır.
     * @return Aktiv apteklərin sayı və HTTP 200 (OK) statusu.
     */
    @GetMapping("/active/count")
    public ResponseEntity<Long> countActivePharmacies() {
        long count = pharmacyService.countActivePharmacies();
        return ResponseEntity.ok(count);
    }
}