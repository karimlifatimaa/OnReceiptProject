package com.citizenservice01.repository;

import com.citizenservice01.module.entity.Citizen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, UUID> {

    Optional<Citizen> findByFinCode(String finCode);

    boolean existsByFinCode(String finCode);

    @Query("SELECT c FROM Citizen c WHERE " +
            "(:firstName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
            "(:lastName IS NULL OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
            "(:finCode IS NULL OR c.finCode = :finCode) AND " +
            "(:isActive IS NULL OR c.isActive = :isActive)")
    Page<Citizen> findByCriteria(@Param("firstName") String firstName,
                                 @Param("lastName") String lastName,
                                 @Param("finCode") String finCode,
                                 @Param("isActive") Boolean isActive,
                                 Pageable pageable);
}
