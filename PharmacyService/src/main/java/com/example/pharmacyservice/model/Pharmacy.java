package com.example.pharmacyservice.model;

import com.example.pharmacyservice.dto.AddressDto;
import com.example.pharmacyservice.dto.ContactInfoDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pharmacies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pharmacy {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    String name;

    @Column(unique = true, name = "license_number")
    String licenseNumber;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private AddressDto address;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "contact_info", columnDefinition = "jsonb")
    ContactInfoDto contactInfo;

    @Column(name = "is_active")
    boolean active = true;



}
