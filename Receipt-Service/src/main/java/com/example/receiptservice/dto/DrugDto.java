package com.example.receiptservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDto {
    private UUID id;
    private String tradeName;
    private Boolean isControlledSubstance;
    private Boolean requiresPrescription;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
