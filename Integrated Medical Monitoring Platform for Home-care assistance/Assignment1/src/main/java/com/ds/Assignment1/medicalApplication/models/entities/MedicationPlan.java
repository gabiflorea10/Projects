package com.ds.Assignment1.medicalApplication.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "medication_plan")
public class MedicationPlan {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID medication_plan_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_patient")
    private User userPatient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_doctor")
    private User userDoctor;

    public MedicationPlan() {
    }

    public MedicationPlan(User userPatient, User userDoctor) {
        this.userPatient = userPatient;
        this.userDoctor = userDoctor;
    }

    public UUID getMedication_plan_id() {
        return medication_plan_id;
    }

    public void setMedication_plan_id(UUID medication_plan_id) {
        this.medication_plan_id = medication_plan_id;
    }

    public User getUserPatient() {
        return userPatient;
    }

    public void setUserPatient(User userPatient) {
        this.userPatient = userPatient;
    }

    public User getUserDoctor() {
        return userDoctor;
    }

    public void setUserDoctor(User userDoctor) {
        this.userDoctor = userDoctor;
    }
}
