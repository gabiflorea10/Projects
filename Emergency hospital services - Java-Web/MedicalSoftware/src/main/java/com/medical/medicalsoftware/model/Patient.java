package com.medical.medicalsoftware.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Patient {
    private String patientName;
    private String processingState;
    private String physiologicalCondition;
    private String emergencyType;
    private String location;
    private String patientHistory;
    private String treatmentsPerformed;

    public Patient(String patientName, String processingState, String physiologicalCondition, String emergencyType, String location, String patientHistory, String treatmentsPerformed) {
        this.patientName = patientName;
        this.processingState = processingState;
        this.physiologicalCondition = physiologicalCondition;
        this.emergencyType = emergencyType;
        this.location = location;
        this.patientHistory = patientHistory;
        this.treatmentsPerformed = treatmentsPerformed;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String name) {
        this.patientName = name;
    }

    public String getProcessingState() {
        return processingState;
    }

    public void setProcessingState(String processingState) {
        this.processingState = processingState;
    }

    public String getPhysiologicalCondition() {
        return physiologicalCondition;
    }

    public void setPhysiologicalCondition(String physiologicalCondition) {
        this.physiologicalCondition = physiologicalCondition;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPatientHistory() {
        return patientHistory;
    }

    public void setPatientHistory(String patientHistory) {
        this.patientHistory = patientHistory;
    }

    public String getTreatmentsPerformed() {
        return treatmentsPerformed;
    }

    public void setTreatmentsPerformed(String treatmentsPerformed) {
        this.treatmentsPerformed = treatmentsPerformed;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + patientName + '\'' +
                ", processingState='" + processingState + '\'' +
                ", physiologicalCondition='" + physiologicalCondition + '\'' +
                ", emergencyType='" + emergencyType + '\'' +
                ", location='" + location + '\'' +
                ", patientHistory='" + patientHistory + '\'' +
                ", treatmentsPerformed='" + treatmentsPerformed + '\'' +
                '}';
    }
}
