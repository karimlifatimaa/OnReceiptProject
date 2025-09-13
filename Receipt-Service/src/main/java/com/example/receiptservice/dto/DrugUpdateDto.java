package com.example.receiptservice.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugUpdateDto {
    @Size(max = 255, message = "Trade name cannot exceed 255 characters")
    private String tradeName;
    private Boolean isControlledSubstance;
    private Boolean requiresPrescription;
    private Boolean isActive;
}
