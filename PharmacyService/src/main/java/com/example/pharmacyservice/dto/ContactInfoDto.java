package com.example.pharmacyservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactInfoDto {
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;

    @Email(message = "Invalid email format")
    private String email;
}
