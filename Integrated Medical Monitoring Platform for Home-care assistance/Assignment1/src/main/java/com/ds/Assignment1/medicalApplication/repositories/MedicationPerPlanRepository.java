package com.ds.Assignment1.medicalApplication.repositories;

import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPerPlan;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicationPerPlanRepository extends JpaRepository<MedicationPerPlan, UUID> {
    Optional<MedicationPerPlan> findById(UUID medication_per_plan_id);
    List<MedicationPerPlan> findAllByMedicationPlan(MedicationPlan medicationPlan);
    Integer deleteByMedication(Medication medication);
    MedicationPerPlan findByMedication(Medication medication);
}
