package com.citizenservice01.module.dto;

import com.citizenservice01.module.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class CitizenRequestDto {

    @NotBlank(message = "FIN kod boş ola bilməz")
    @Pattern(regexp = "^[A-Z0-9]{7}$", message = "FIN kod 7 simvoldan ibarət olmalıdır")
    private String finCode;

    @NotBlank(message = "Ad boş ola bilməz")
    @Size(max = 100, message = "Ad 100 simvoldan çox ola bilməz")
    private String firstName;

    @NotBlank(message = "Soyad boş ola bilməz")
    @Size(max = 100, message = "Soyad 100 simvoldan çox ola bilməz")
    private String lastName;

    @Size(max = 100, message = "Ata adı 100 simvoldan çox ola bilməz")
    private String patronymic;

    @NotNull(message = "Doğum tarixi boş ola bilməz")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Gender gender;

    private Map<String, Object> contactInfo;

    private Map<String, Object> address;

    private Boolean isActive = true;
}
