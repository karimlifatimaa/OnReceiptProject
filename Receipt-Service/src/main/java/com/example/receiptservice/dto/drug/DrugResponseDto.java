package com.example.receiptservice.dto.drug;

import lombok.Data;
import java.util.UUID;
import java.time.OffsetDateTime;

@Data
public class DrugResponseDto {
    private UUID id;
    private String tradeName;
    private boolean isControlledSubstance;
    private boolean requiresPrescription;
    private boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}