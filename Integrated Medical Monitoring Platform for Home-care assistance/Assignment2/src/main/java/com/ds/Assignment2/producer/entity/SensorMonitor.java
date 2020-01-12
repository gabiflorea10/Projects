package com.ds.Assignment2.producer.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class SensorMonitor implements Serializable {
    private UUID patient_id;
    private Date start_time;
    private Date end_time;
    private String activity_label;

    public SensorMonitor() {
    }

    public SensorMonitor(UUID patient_id, Date start_time, Date end_time, String activity_label) {
        this.patient_id = patient_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.activity_label = activity_label;
    }

    public UUID getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(UUID patient_id) {
        this.patient_id = patient_id;
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

    public String getActivity_label() {
        return activity_label;
    }

    public void setActivity_label(String activity_label) {
        this.activity_label = activity_label;
    }

    @Override
    public String toString() {
        return "SensorMonitor{" +
                "patient_id=" + patient_id +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", activity_label='" + activity_label + '\'' +
                '}';
    }
}
