package com.example.pharmacyservice.service;

import com.example.pharmacyservice.dto.PharmacyDto;
import com.example.pharmacyservice.model.Pharmacy;
import com.example.pharmacyservice.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;


    private PharmacyDto toDto(Pharmacy pharmacy) {
        if (pharmacy == null) {
            return null;
        }

        PharmacyDto dto = new PharmacyDto();
        dto.setName(pharmacy.getName());
        dto.setLicenseNumber(pharmacy.getLicenseNumber());
        dto.setAddress(pharmacy.getAddress());
        dto.setContactInfo(pharmacy.getContactInfo());
        dto.setActive(pharmacy.isActive());

        return dto;
    }

    private Pharmacy toEntity(PharmacyDto dto) {
        if (dto == null) {
            return null;
        }

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(dto.getName());
        pharmacy.setLicenseNumber(dto.getLicenseNumber());
        pharmacy.setAddress(dto.getAddress());
        pharmacy.setContactInfo(dto.getContactInfo());
        pharmacy.setActive(dto.isActive());

        return pharmacy;
    }


    @Override
    public PharmacyDto createPharmacy(PharmacyDto pharmacyDto) {
        log.info("Creating new pharmacy with license number: {}", pharmacyDto.getLicenseNumber());

        // Check if pharmacy with same license number already exists
        if (existsByLicenseNumber(pharmacyDto.getLicenseNumber())) {
            throw new RuntimeException("Pharmacy with license number " + pharmacyDto.getLicenseNumber() + " already exists");
        }

        try {
            Pharmacy pharmacy = toEntity(pharmacyDto);
            Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);

            log.info("Successfully created pharmacy with ID: {}", savedPharmacy.getId());
            return toDto(savedPharmacy);
        } catch (Exception e) {
            log.error("Error creating pharmacy: {}", e.getMessage());
            throw new RuntimeException("Failed to create pharmacy", e);
        }
    }

    @Override
    public PharmacyDto updatePharmacy(UUID id, PharmacyDto pharmacyDto) {
        log.info("Updating pharmacy with ID: {}", id);

        Pharmacy existingPharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found with ID: " + id));

        // Check if license number is being changed and if new license number already exists
        if (!existingPharmacy.getLicenseNumber().equals(pharmacyDto.getLicenseNumber())
                && existsByLicenseNumber(pharmacyDto.getLicenseNumber())) {
            throw new RuntimeException("Pharmacy with license number " + pharmacyDto.getLicenseNumber() + " already exists");
        }

        try {
            // Update fields
            existingPharmacy.setName(pharmacyDto.getName());
            existingPharmacy.setLicenseNumber(pharmacyDto.getLicenseNumber());
            existingPharmacy.setAddress(pharmacyDto.getAddress());
            existingPharmacy.setContactInfo(pharmacyDto.getContactInfo());
            existingPharmacy.setActive(pharmacyDto.isActive());

            Pharmacy updatedPharmacy = pharmacyRepository.save(existingPharmacy);

            log.info("Successfully updated pharmacy with ID: {}", id);
            return toDto(updatedPharmacy);
        } catch (Exception e) {
            log.error("Error updating pharmacy with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to update pharmacy", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PharmacyDto> getPharmacyById(UUID id) {

        log.info("Fetching pharmacy with ID: {}", id);

        return pharmacyRepository.findById(id)
                .map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PharmacyDto> getAllPharmacies() {
        log.info("Fetching all pharmacies");

        return pharmacyRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PharmacyDto> getAllPharmacies(Pageable pageable) {
        log.info("Fetching pharmacies with pagination: page {}, size {}",
                pageable.getPageNumber(), pageable.getPageSize());

        return pharmacyRepository.findAll(pageable)
                .map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PharmacyDto> getActivePharmacies() {
        log.info("Fetching all active pharmacies");

        return pharmacyRepository.findAll()
                .stream()
                .filter(Pharmacy::isActive)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePharmacy(UUID id) {
        log.info("Deleting pharmacy with ID: {}", id);

        if (!pharmacyRepository.existsById(id)) {
            throw new RuntimeException("Pharmacy not found with ID: " + id);
        }

        try {
            pharmacyRepository.deleteById(id);
            log.info("Successfully deleted pharmacy with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting pharmacy with ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Failed to delete pharmacy", e);
        }
    }

    @Override
    public void deactivatePharmacy(UUID id) {
            log.info("Deactivating pharmacy with ID: {}", id);

            Pharmacy pharmacy = pharmacyRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pharmacy not found with ID: " + id));

            pharmacy.setActive(false);
            pharmacyRepository.save(pharmacy);

            log.info("Successfully deactivated pharmacy with ID: {}", id);
    }


    @Override
    public void activatePharmacy(UUID id) {
        log.info("Activating pharmacy with ID: {}", id);

        Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found with ID: " + id));

        pharmacy.setActive(true);
        pharmacyRepository.save(pharmacy);

        log.info("Successfully activated pharmacy with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByLicenseNumber(String licenseNumber) {
        return pharmacyRepository.findAll()
                .stream()
                .anyMatch(pharmacy -> pharmacy.getLicenseNumber().equals(licenseNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public long countActivePharmacies() {
        return pharmacyRepository.findAll()
                .stream()
                .filter(Pharmacy::isActive)
                .count();
    }
}
