package com.citizenservice01;

import com.citizenservice01.exception.CitizenNotFoundException;
import com.citizenservice01.exception.DuplicateFinCodeException;
import com.citizenservice01.mapper.CitizenMapper;
import com.citizenservice01.module.dto.CitizenRequestDto;
import com.citizenservice01.module.dto.CitizenResponseDto;
import com.citizenservice01.module.entity.Citizen;
import com.citizenservice01.module.enums.Gender;
import com.citizenservice01.repository.CitizenRepository;
import com.citizenservice01.service.CitizenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitizenServiceTest {

    @Mock
    private CitizenRepository citizenRepository;

    @Mock
    private CitizenMapper citizenMapper;

    @InjectMocks
    private CitizenService citizenService;

    private CitizenRequestDto requestDto;
    private Citizen citizen;
    private CitizenResponseDto responseDto;
    private UUID citizenId;

    @BeforeEach
    void setUp() {
        citizenId = UUID.randomUUID();

        requestDto = new CitizenRequestDto();
        requestDto.setFinCode("ABC1234");
        requestDto.setFirstName("Əli");
        requestDto.setLastName("Məmmədov");
        requestDto.setBirthDate(LocalDate.of(1985, 5, 15));
        requestDto.setGender(Gender.MALE);

        citizen = new Citizen();
        citizen.setId(citizenId);
        citizen.setFinCode("ABC1234");
        citizen.setFirstName("Əli");
        citizen.setLastName("Məmmədov");
        citizen.setBirthDate(LocalDate.of(1985, 5, 15));
        citizen.setGender(Gender.MALE);

        responseDto = new CitizenResponseDto();
        responseDto.setId(citizenId);
        responseDto.setFinCode("ABC1234");
        responseDto.setFirstName("Əli");
        responseDto.setLastName("Məmmədov");
    }

    @Test
    void createCitizen_Success() {
        // Given
        when(citizenRepository.existsByFinCode(requestDto.getFinCode())).thenReturn(false);
        when(citizenMapper.toEntity(requestDto)).thenReturn(citizen);
        when(citizenRepository.save(citizen)).thenReturn(citizen);
        when(citizenMapper.toResponseDto(citizen)).thenReturn(responseDto);

        // When
        CitizenResponseDto result = citizenService.createCitizen(requestDto);

        // Then
        assertNotNull(result);
        assertEquals(requestDto.getFinCode(), result.getFinCode());
        assertEquals(requestDto.getFirstName(), result.getFirstName());

        verify(citizenRepository).existsByFinCode(requestDto.getFinCode());
        verify(citizenRepository).save(citizen);
    }

    @Test
    void createCitizen_DuplicateFinCode_ThrowsException() {
        // Given
        when(citizenRepository.existsByFinCode(requestDto.getFinCode())).thenReturn(true);

        // When & Then
        assertThrows(DuplicateFinCodeException.class,
                () -> citizenService.createCitizen(requestDto));

        verify(citizenRepository, never()).save(any());
    }

    @Test
    void getCitizenById_Success() {
        // Given
        when(citizenRepository.findById(citizenId)).thenReturn(Optional.of(citizen));
        when(citizenMapper.toResponseDto(citizen)).thenReturn(responseDto);

        // When
        CitizenResponseDto result = citizenService.getCitizenById(citizenId);

        // Then
        assertNotNull(result);
        assertEquals(citizenId, result.getId());
        verify(citizenRepository).findById(citizenId);
    }

    @Test
    void getCitizenById_NotFound_ThrowsException() {
        // Given
        when(citizenRepository.findById(citizenId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CitizenNotFoundException.class,
                () -> citizenService.getCitizenById(citizenId));
    }

    @Test
    void getCitizenByFinCode_Success() {
        // Given
        String finCode = "ABC1234";
        when(citizenRepository.findByFinCode(finCode)).thenReturn(Optional.of(citizen));
        when(citizenMapper.toResponseDto(citizen)).thenReturn(responseDto);

        // When
        CitizenResponseDto result = citizenService.getCitizenByFinCode(finCode);

        // Then
        assertNotNull(result);
        assertEquals(finCode, result.getFinCode());
        verify(citizenRepository).findByFinCode(finCode);
    }

    @Test
    void deleteCitizen_Success() {
        // Given
        when(citizenRepository.existsById(citizenId)).thenReturn(true);

        // When
        citizenService.deleteCitizen(citizenId);

        // Then
        verify(citizenRepository).existsById(citizenId);
        verify(citizenRepository).deleteById(citizenId);
    }

    @Test
    void deleteCitizen_NotFound_ThrowsException() {
        // Given
        when(citizenRepository.existsById(citizenId)).thenReturn(false);

        // When & Then
        assertThrows(CitizenNotFoundException.class,
                () -> citizenService.deleteCitizen(citizenId));

        verify(citizenRepository, never()).deleteById(any());
    }
}