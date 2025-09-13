package com.citizenservice01.module.dto;

import com.citizenservice01.module.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class CitizenUpdateDto {

    @Size(max = 100, message = "Ad 100 simvoldan çox ola bilməz")
    private String firstName;

    @Size(max = 100, message = "Soyad 100 simvoldan çox ola bilməz")
    private String lastName;

    @Size(max = 100, message = "Ata adı 100 simvoldan çox ola bilməz")
    private String patronymic;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Gender gender;

    private Map<String, Object> contactInfo;

    private Map<String, Object> address;

    private Boolean isActive;
}
