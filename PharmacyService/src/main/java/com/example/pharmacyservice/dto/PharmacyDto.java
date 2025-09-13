package com.example.pharmacyservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PharmacyDto {

    @NotBlank(message = "Please enter the name!")
    String name;

    @NotBlank(message = "Please enter the license number!")
    String licenseNumber;

    @Valid
    @NotNull(message = "Please enter the address!")
    AddressDto address;

    @Valid
    @NotNull(message = "Please enter the contact info!")
    ContactInfoDto contactInfo;

    boolean active = true;
}
