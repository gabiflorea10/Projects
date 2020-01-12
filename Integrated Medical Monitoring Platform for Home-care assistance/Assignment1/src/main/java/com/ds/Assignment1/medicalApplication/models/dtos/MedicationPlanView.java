package com.ds.Assignment1.medicalApplication.models.dtos;

import java.sql.Date;

public class MedicationPlanView {
    private String patient_name;
    private String medication_name;
    private String side_effects;
    private Integer dosage;
    private String intake_interval;
    private Date start_time;
    private Date end_time;
    private String doctor_name;

    public MedicationPlanView() {
    }

    public MedicationPlanView(String patient_name, String medication_name, String side_effects, Integer dosage, String intake_interval, Date start_time, Date end_time, String doctor_name) {
        this.patient_name = patient_name;
        this.medication_name = medication_name;
        this.side_effects = side_effects;
        this.dosage = dosage;
        this.intake_interval = intake_interval;
        this.start_time = start_time;
        this.end_time = end_time;
        this.doctor_name = doctor_name;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getMedication_name() {
        return medication_name;
    }

    public void setMedication_name(String medication_name) {
        this.medication_name = medication_name;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }

    public String getIntake_interval() {
        return intake_interval;
    }

    public void setIntake_interval(String intake_interval) {
        this.intake_interval = intake_interval;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    @Override
    public String toString() {
        return "MedicationPlanView{" +
                "patient_name='" + patient_name + '\'' +
                ", medication_name='" + medication_name + '\'' +
                ", side_effects='" + side_effects + '\'' +
                ", dosage=" + dosage +
                ", intake_interval=" + intake_interval +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", doctor_name='" + doctor_name + '\'' +
                '}';
    }
}
