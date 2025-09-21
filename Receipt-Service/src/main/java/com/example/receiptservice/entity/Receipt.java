package com.example.receiptservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "receipts")
@Data
public class Receipt {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "serial_number", unique = true, nullable = false, length = 20)
    private String serialNumber;

    @Column(name = "citizen_id", nullable = false)
    private UUID citizenId;

    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;

    @Column(name = "hospital_id", nullable = false)
    private UUID hospitalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReceiptStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "funding_source", nullable = false)
    private FundingSource fundingSource;

    @Column(name = "qr_code_payload", unique = true, nullable = false, columnDefinition = "TEXT")
    private String qrCodePayload;

    @Column(name = "issue_date", nullable = false)
    private OffsetDateTime issueDate;

    @Column(name = "fulfilled_at")
    private OffsetDateTime fulfilledAt;

    @Column(name = "fulfilled_by_pharmacy_id")
    private UUID fulfilledByPharmacyId;

    // Tərkib Hissəsi Əlaqəsi:
    // `orphanRemoval` true olduğundan, əlaqəli `ReceiptItem` silinir.
    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItem> items;

}