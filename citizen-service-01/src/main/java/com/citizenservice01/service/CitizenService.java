package com.citizenservice01.service;

import com.citizenservice01.module.dto.CitizenRequestDto;
import com.citizenservice01.module.dto.CitizenResponseDto;
import com.citizenservice01.module.dto.CitizenUpdateDto;
import com.citizenservice01.module.entity.Citizen;
import com.citizenservice01.exception.CitizenNotFoundException;
import com.citizenservice01.exception.DuplicateFinCodeException;
import com.citizenservice01.mapper.CitizenMapper;
import com.citizenservice01.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    public CitizenResponseDto createCitizen(CitizenRequestDto requestDto) {
        log.info("Yeni vətəndaş yaradılır: FIN - {}", requestDto.getFinCode());

        if (citizenRepository.existsByFinCode(requestDto.getFinCode())) {
            throw new DuplicateFinCodeException("Bu FIN kod artıq mövcuddur: " + requestDto.getFinCode());
        }

        Citizen citizen = citizenMapper.toEntity(requestDto);
        Citizen savedCitizen = citizenRepository.save(citizen);

        log.info("Vətəndaş yaradıldı: ID - {}, FIN - {}", savedCitizen.getId(), savedCitizen.getFinCode());
        return citizenMapper.toResponseDto(savedCitizen);
    }

    @Transactional(readOnly = true)
    public CitizenResponseDto getCitizenById(UUID id) {
        log.info("Vətəndaş axtarılır: ID - {}", id);

        Citizen citizen = citizenRepository.findById(id)
                .orElseThrow(() -> new CitizenNotFoundException("Vətəndaş tapılmadı: " + id));

        return citizenMapper.toResponseDto(citizen);
    }

    @Transactional(readOnly = true)
    public CitizenResponseDto getCitizenByFinCode(String finCode) {
        log.info("Vətəndaş axtarılır: FIN - {}", finCode);

        Citizen citizen = citizenRepository.findByFinCode(finCode)
                .orElseThrow(() -> new CitizenNotFoundException("Vətəndaş tapılmadı: " + finCode));

        return citizenMapper.toResponseDto(citizen);
    }

    public CitizenResponseDto updateCitizen(UUID id, CitizenUpdateDto updateDto) {
        log.info("Vətəndaş məlumatları yenilənir: ID - {}", id);

        Citizen citizen = citizenRepository.findById(id)
                .orElseThrow(() -> new CitizenNotFoundException("Vətəndaş tapılmadı: " + id));

        citizenMapper.updateEntityFromDto(updateDto, citizen);
        Citizen updatedCitizen = citizenRepository.save(citizen);

        log.info("Vətəndaş məlumatları yeniləndi: ID - {}", id);
        return citizenMapper.toResponseDto(updatedCitizen);
    }

    public void deleteCitizen(UUID id) {
        log.info("Vətəndaş silinir: ID - {}", id);

        if (!citizenRepository.existsById(id)) {
            throw new CitizenNotFoundException("Vətəndaş tapılmadı: " + id);
        }

        citizenRepository.deleteById(id);
        log.info("Vətəndaş silindi: ID - {}", id);
    }

    @Transactional(readOnly = true)
    public Page<CitizenResponseDto> searchCitizens(String firstName, String lastName,
                                                   String finCode, Boolean isActive,
                                                   Pageable pageable) {
        log.info("Vətəndaş axtarışı: firstName={}, lastName={}, finCode={}, isActive={}",
                firstName, lastName, finCode, isActive);

        Page<Citizen> citizens = citizenRepository.findByCriteria(firstName, lastName, finCode, isActive, pageable);
        return citizens.map(citizenMapper::toResponseDto);
    }

    @Transactional(readOnly = true)
    public Page<CitizenResponseDto> getAllCitizens(Pageable pageable) {
        log.info("Bütün vətəndaşlar sorğulanır");

        Page<Citizen> citizens = citizenRepository.findAll(pageable);
        return citizens.map(citizenMapper::toResponseDto);
    }
}
