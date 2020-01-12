package com.ds.Assignment1.sensorMonitoring.consumer.model.entity;

import com.ds.Assignment1.medicalApplication.models.entities.Patient;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name= "activity_monitor")
public class ActivityMonitor {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID activity_monitor_id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "start_time", columnDefinition = "DATETIME")
    private Timestamp start_time;

    @Column(name = "end_time", columnDefinition = "DATETIME")
    private Timestamp end_time;

    @Column(name = "activity_label")
    private String activity_label;

    @Column(name = "is_anomalous")
    private String is_anomalous;

    public ActivityMonitor() {
    }

    public ActivityMonitor(Patient patient, Timestamp start_time, Timestamp end_time, String activity_label, String is_anomalous) {
        this.patient = patient;
        this.start_time = start_time;
        this.end_time = end_time;
        this.activity_label = activity_label;
        this.is_anomalous = is_anomalous;
    }

    public UUID getActivity_monitor_id() {
        return activity_monitor_id;
    }

    public void setActivity_monitor_id(UUID activity_monitor_id) {
        this.activity_monitor_id = activity_monitor_id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public String getActivity_label() {
        return activity_label;
    }

    public void setActivity_label(String activity_label) {
        this.activity_label = activity_label;
    }

    public String getIs_anomalous() {
        return is_anomalous;
    }

    public void setIs_anomalous(String is_anomalous) {
        this.is_anomalous = is_anomalous;
    }
}
