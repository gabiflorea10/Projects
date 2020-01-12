package com.ds.Assignment4.models.dtos;

import java.io.Serializable;

public class ActivityMonitorInfoDTO implements Serializable {
    private String activity_id;
    private String patient_name;
    private Double hours;
    private String activity_label;
    private String caregiver_username;
    private String is_anomalous;
    private String activity_date;

    public ActivityMonitorInfoDTO() {
    }

    public ActivityMonitorInfoDTO(String activity_id, String patient_name, Double hours, String activity_label, String caregiver_username, String is_anomalous, String activity_date) {
        this.activity_id = activity_id;
        this.patient_name = patient_name;
        this.hours = hours;
        this.activity_label = activity_label;
        this.caregiver_username = caregiver_username;
        this.is_anomalous = is_anomalous;
        this.activity_date = activity_date;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
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

    public String getIs_anomalous() {
        return is_anomalous;
    }

    public void setIs_anomalous(String is_anomalous) {
        this.is_anomalous = is_anomalous;
    }

    public String getActivity_date() {
        return activity_date;
    }

    public void setActivity_date(String activity_date) {
        this.activity_date = activity_date;
    }
}
