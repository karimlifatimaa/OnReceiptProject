package com.citizenservice01.module.entity;

import com.citizenservice01.module.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "citizens", indexes = {
        @Index(name = "idx_citizen_fin_code", columnList = "fin_code", unique = true),
        @Index(name = "idx_citizen_last_name", columnList = "last_name")
})
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "fin_code", unique = true, nullable = false, length = 7)
    @NotBlank(message = "FIN kod boş ola bilməz")
    @Pattern(regexp = "^[A-Z0-9]{7}$", message = "FIN kod 7 simvoldan ibarət olmalıdır")
    private String finCode;

    @Column(name = "first_name", nullable = false, length = 100)
    @NotBlank(message = "Ad boş ola bilməz")
    @Size(max = 100, message = "Ad 100 simvoldan çox ola bilməz")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    @NotBlank(message = "Soyad boş ola bilməz")
    @Size(max = 100, message = "Soyad 100 simvoldan çox ola bilməz")
    private String lastName;

    @Column(name = "patronymic", length = 100)
    @Size(max = 100, message = "Ata adı 100 simvoldan çox ola bilməz")
    private String patronymic;

    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "Doğum tarixi boş ola bilməz")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender = Gender.UNKNOWN;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "contact_info", columnDefinition = "jsonb")
    private Map<String, Object> contactInfo;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "address", columnDefinition = "jsonb")
    private Map<String, Object> address;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
