package com.example.receiptservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugCreateDto {
    @NotBlank(message = "Trade name cannot be blank")
    @Size(max = 255, message = "Trade name cannot exceed 255 characters")
    private String tradeName;
    private Boolean isControlledSubstance = false;
    private Boolean requiresPrescription = true;
}
