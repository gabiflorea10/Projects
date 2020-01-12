package com.ds.Assignment1.sensorMonitoring.consumer.model.dto;

import java.io.Serializable;


public class ActivityMonitorInfoDTO implements Serializable {
    private String patient_name;
    private Double hours;
    private String activity_label;
    private String caregiver_username;

    public ActivityMonitorInfoDTO() {
    }

    public ActivityMonitorInfoDTO(String patient_name, Double hours, String activity_label, String caregiver_username) {
        this.patient_name = patient_name;
        this.hours = hours;
        this.activity_label = activity_label;
        this.caregiver_username = caregiver_username;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public String getActivity_label() {
        return activity_label;
    }

    public void setActivity_label(String activity_label) {
        this.activity_label = activity_label;
    }

    public String getCaregiver_username() {
        return caregiver_username;
    }

    public void setCaregiver_username(String caregiver_username) {
        this.caregiver_username = caregiver_username;
    }

    @Override
    public String toString() {
        return "ActivityMonitorInfoDTO{" +
                "patient_name='" + patient_name + '\'' +
                ", hours=" + hours +
                ", activity_label='" + activity_label + '\'' +
                ", caregiver_username='" + caregiver_username + '\'' +
                '}';
    }
}
