package com.example.receiptservice.dto.drug;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DrugUpdateDto {
    @Size(max = 255, message = "Trade name must be less than 255 characters")
    private String tradeName;
    private Boolean isControlledSubstance;
    private Boolean requiresPrescription;
    private Boolean isActive;
}
