package com.ds.Assignment1.medicalApplication.models.dtos;

import java.util.UUID;

public class MedicationPlanInfoDTO {
    private UUID medication_plan_id;
    private String patient_username;
    private String doctor_username;

    public MedicationPlanInfoDTO() {
    }

    public MedicationPlanInfoDTO(UUID medication_plan_id, String patient_username, String doctor_username) {
        this.medication_plan_id = medication_plan_id;
        this.patient_username = patient_username;
        this.doctor_username = doctor_username;
    }

    public UUID getMedication_plan_id() {
        return medication_plan_id;
    }

    public void setMedication_plan_id(UUID medication_plan_id) {
        this.medication_plan_id = medication_plan_id;
    }

    public String getPatient_username() {
        return patient_username;
    }

    public void setPatient_username(String patient_username) {
        this.patient_username = patient_username;
    }

    public String getDoctor_username() {
        return doctor_username;
    }

    public void setDoctor_username(String doctor_username) {
        this.doctor_username = doctor_username;
    }

    @Override
    public String toString() {
        return "MedicationPlanInfoDTO{" +
                "medication_plan_id=" + medication_plan_id +
                ", patient_username='" + patient_username + '\'' +
                ", doctor_username='" + doctor_username + '\'' +
                '}';
    }
}
