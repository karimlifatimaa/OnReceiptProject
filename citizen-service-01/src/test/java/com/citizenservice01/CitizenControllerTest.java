package com.citizenservice01;

import com.citizenservice01.controller.CitizenController;
import com.citizenservice01.module.dto.CitizenRequestDto;
import com.citizenservice01.module.dto.CitizenResponseDto;
import com.citizenservice01.module.enums.Gender;
import com.citizenservice01.service.CitizenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CitizenController.class)
class CitizenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CitizenService citizenService;

    @Autowired
    private ObjectMapper objectMapper;

    private CitizenRequestDto requestDto;
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

        responseDto = new CitizenResponseDto();
        responseDto.setId(citizenId);
        responseDto.setFinCode("ABC1234");
        responseDto.setFirstName("Əli");
        responseDto.setLastName("Məmmədov");
        responseDto.setBirthDate(LocalDate.of(1985, 5, 15));
        responseDto.setGender(Gender.MALE);
    }

    @Test
    void createCitizen_Success() throws Exception {
        // Given
        when(citizenService.createCitizen(any(CitizenRequestDto.class))).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(post("/api/citizens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.finCode").value("ABC1234"))
                .andExpect(jsonPath("$.firstName").value("Əli"))
                .andExpect(jsonPath("$.lastName").value("Məmmədov"));
    }

    @Test
    void createCitizen_InvalidData_BadRequest() throws Exception {
        // Given - Invalid request (missing required fields)
        CitizenRequestDto invalidRequest = new CitizenRequestDto();
        invalidRequest.setFinCode(""); // Invalid - empty

        // When & Then
        mockMvc.perform(post("/api/citizens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCitizenById_Success() throws Exception {
        // Given
        when(citizenService.getCitizenById(citizenId)).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(get("/api/citizens/{id}", citizenId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(citizenId.toString()))
                .andExpect(jsonPath("$.finCode").value("ABC1234"));
    }

    @Test
    void getCitizenByFinCode_Success() throws Exception {
        // Given
        when(citizenService.getCitizenByFinCode("ABC1234")).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(get("/api/citizens/fin/{finCode}", "ABC1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.finCode").value("ABC1234"));
    }

    @Test
    void deleteCitizen_Success() throws Exception {
        // When & Then
        mockMvc.perform(delete("/api/citizens/{id}", citizenId))
                .andExpect(status().isNoContent());
    }
}