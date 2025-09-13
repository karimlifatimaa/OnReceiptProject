package com.citizenservice01.mapper;

import com.citizenservice01.module.dto.CitizenRequestDto;
import com.citizenservice01.module.dto.CitizenResponseDto;
import com.citizenservice01.module.dto.CitizenUpdateDto;
import com.citizenservice01.module.entity.Citizen;
import org.springframework.stereotype.Component;

@Component
public class CitizenMapper {

    public Citizen toEntity(CitizenRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Citizen citizen = new Citizen();
        citizen.setFinCode(dto.getFinCode());
        citizen.setFirstName(dto.getFirstName());
        citizen.setLastName(dto.getLastName());
        citizen.setPatronymic(dto.getPatronymic());
        citizen.setBirthDate(dto.getBirthDate());
        citizen.setGender(dto.getGender());
        citizen.setContactInfo(dto.getContactInfo());
        citizen.setAddress(dto.getAddress());
        citizen.setIsActive(dto.getIsActive());

        return citizen;
    }

    public CitizenResponseDto toResponseDto(Citizen citizen) {
        if (citizen == null) {
            return null;
        }

        CitizenResponseDto dto = new CitizenResponseDto();
        dto.setId(citizen.getId());
        dto.setFinCode(citizen.getFinCode());
        dto.setFirstName(citizen.getFirstName());
        dto.setLastName(citizen.getLastName());
        dto.setPatronymic(citizen.getPatronymic());
        dto.setBirthDate(citizen.getBirthDate());
        dto.setGender(citizen.getGender());
        dto.setContactInfo(citizen.getContactInfo());
        dto.setAddress(citizen.getAddress());
        dto.setIsActive(citizen.getIsActive());
        dto.setCreatedAt(citizen.getCreatedAt());
        dto.setUpdatedAt(citizen.getUpdatedAt());

        return dto;
    }

    public void updateEntityFromDto(CitizenUpdateDto dto, Citizen citizen) {
        if (dto == null || citizen == null) {
            return;
        }

        if (dto.getFirstName() != null) {
            citizen.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            citizen.setLastName(dto.getLastName());
        }
        if (dto.getPatronymic() != null) {
            citizen.setPatronymic(dto.getPatronymic());
        }
        if (dto.getBirthDate() != null) {
            citizen.setBirthDate(dto.getBirthDate());
        }
        if (dto.getGender() != null) {
            citizen.setGender(dto.getGender());
        }
        if (dto.getContactInfo() != null) {
            citizen.setContactInfo(dto.getContactInfo());
        }
        if (dto.getAddress() != null) {
            citizen.setAddress(dto.getAddress());
        }
        if (dto.getIsActive() != null) {
            citizen.setIsActive(dto.getIsActive());
        }
    }
}
