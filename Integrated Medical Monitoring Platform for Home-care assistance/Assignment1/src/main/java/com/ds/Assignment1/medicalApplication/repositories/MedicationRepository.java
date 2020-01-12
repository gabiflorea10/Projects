package com.ds.Assignment1.medicalApplication.repositories;

import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, UUID> {
    Optional<Medication> findById(UUID medication_id);
    Medication findByName(String name);
    Integer deleteByName(String name);
}
