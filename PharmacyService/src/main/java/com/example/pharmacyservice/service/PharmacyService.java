package com.example.pharmacyservice.service;

import com.example.pharmacyservice.dto.PharmacyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PharmacyService {
    PharmacyDto createPharmacy(PharmacyDto pharmacyDto);
    PharmacyDto updatePharmacy(UUID id, PharmacyDto pharmacyDto);
    Optional<PharmacyDto> getPharmacyById(UUID id);
    List<PharmacyDto> getAllPharmacies();
    Page<PharmacyDto> getAllPharmacies(Pageable pageable);
    List<PharmacyDto> getActivePharmacies();

    void deletePharmacy(UUID id);

    void deactivatePharmacy(UUID id);

    void activatePharmacy(UUID id);

    boolean existsByLicenseNumber(String licenseNumber);

    long countActivePharmacies();
}
