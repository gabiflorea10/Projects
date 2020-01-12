package com.ds.Assignment1.medicalApplication.repositories;

import com.ds.Assignment1.medicalApplication.models.entities.Patient;
import com.ds.Assignment1.medicalApplication.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Optional<Patient> findById(UUID patient_id);
    Optional<Patient> findByUserPatient(User user);
    Patient findPatientByUserPatient(User user);
    Patient findByUserCaregiver(User user);
    List<Patient> findAllByUserCaregiver(User user);
    Integer deleteByUserPatient(User user);
}
