package com.example.receiptservice.dto.drug;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DrugCreateDto {
    @NotBlank(message = "Trade name cannot be blank")
    @Size(max = 255, message = "Trade name must be less than 255 characters")
    private String tradeName;
    private boolean isControlledSubstance = false;
    private boolean requiresPrescription = true;
}
