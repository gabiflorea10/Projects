package com.ds.Assignment4.models.dtos;

import java.util.Date;

public class MonitorPillsInfoDTO {
    private String patientName;
    private String medicationName;
    private String intakeDate;
    private String description;

    public MonitorPillsInfoDTO() {
    }

    public MonitorPillsInfoDTO(String patientName, String medicationName, String intakeDate, String description) {
        this.patientName = patientName;
        this.medicationName = medicationName;
        this.intakeDate = intakeDate;
        this.description = description;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getIntakeDate() {
        return intakeDate;
    }

    public void setIntakeDate(String intakeDate) {
        this.intakeDate = intakeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
