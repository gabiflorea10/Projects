package com.ds.Assignment1.medicalApplication.models.mappers;

import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPlanView;
import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPerPlan;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPlan;

public class MedicationPlanViewMapper {

    public static MedicationPlanView mapToDTO (Medication medication, MedicationPlan medicationPlan, MedicationPerPlan medicationPerPlan){
        MedicationPlanView medicationPlanView = new MedicationPlanView();

        medicationPlanView.setPatient_name(medicationPlan.getUserPatient().getName());
        medicationPlanView.setMedication_name(medication.getName());
        medicationPlanView.setSide_effects(medication.getSide_effects());
        medicationPlanView.setDosage(medication.getDosage());
        medicationPlanView.setIntake_interval(medicationPerPlan.getIntake_interval());
        medicationPlanView.setStart_time(medicationPerPlan.getStart_time());
        medicationPlanView.setEnd_time(medicationPerPlan.getEnd_time());
        medicationPlanView.setDoctor_name(medicationPlan.getUserDoctor().getName());

        return medicationPlanView;
    }



}
