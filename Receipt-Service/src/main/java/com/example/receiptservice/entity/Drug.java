package com.example.receiptservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "drugs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drug {

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "trade_name", nullable = false, length = 255)
    private String tradeName;

    @Column(name = "is_controlled_substance", nullable = false)

    private Boolean isControlledSubstance = false;

    @Column(name = "requires_prescription", nullable = false)
    private Boolean requiresPrescription = true;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

}
