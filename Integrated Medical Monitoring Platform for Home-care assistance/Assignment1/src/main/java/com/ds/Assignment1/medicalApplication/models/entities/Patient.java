package com.ds.Assignment1.medicalApplication.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID patient_id;

    @Column(name = "medical_record")
    private String medical_record;

    @ManyToOne()
    @JoinColumn(name = "user_id_caregiver")
    private User userCaregiver;

    @OneToOne()
    @JoinColumn(name = "user_id_patient")
    private User userPatient;

    public Patient() {
    }

    public Patient(String medical_record, User userCaregiver, User userPatient) {
        this.medical_record = medical_record;
        this.userCaregiver = userCaregiver;
        this.userPatient = userPatient;
    }

    public UUID getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(UUID patient_id) {
        this.patient_id = patient_id;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public User getUserCaregiver() {
        return userCaregiver;
    }

    public void setUserCaregiver(User userCaregiver) {
        this.userCaregiver = userCaregiver;
    }

    public User getUserPatient() {
        return userPatient;
    }

    public void setUserPatient(User userPatient) {
        this.userPatient = userPatient;
    }
}
