package com.ds.Assignment1.medicalApplication.repositories;

import com.ds.Assignment1.medicalApplication.models.entities.MedicationPlan;
import com.ds.Assignment1.medicalApplication.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID> {
    Optional<MedicationPlan> findById(UUID medication_plan_id);
    List<MedicationPlan> findAllByUserPatient(User user);
    MedicationPlan findByUserPatientAndUserDoctor(User patient, User doctor);

}
