package com.example.receiptservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;
import java.time.OffsetDateTime;

@Entity
@Table(name = "drugs")
@Data
public class Drug {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "trade_name", nullable = false)
    private String tradeName;

    @Column(name = "is_controlled_substance", columnDefinition = "boolean default false")
    private boolean isControlledSubstance = false;

    @Column(name = "requires_prescription", columnDefinition = "boolean default true")
    private boolean requiresPrescription = true;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}