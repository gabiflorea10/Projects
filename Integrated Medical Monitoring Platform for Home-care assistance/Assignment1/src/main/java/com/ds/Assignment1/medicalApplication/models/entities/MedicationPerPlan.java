package com.ds.Assignment1.medicalApplication.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "medication_per_plan")
public class MedicationPerPlan {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID medication_per_plan_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @OneToOne()
    @JoinColumn(name = "medication_plan_id")
    private MedicationPlan medicationPlan;

    @Column(name = "intake_interval")
    private String intake_interval;

    @Column(name = "start_time", columnDefinition = "DATETIME")
    private Date start_time;

    @Column(name = "end_time", columnDefinition = "DATETIME")
    private Date end_time;


    public MedicationPerPlan() {
    }

    public MedicationPerPlan(Medication medication, MedicationPlan medicationPlan, String intake_interval, Date start_time, Date end_time) {
        this.medication = medication;
        this.medicationPlan = medicationPlan;
        this.intake_interval = intake_interval;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public UUID getMedication_per_plan_id() {
        return medication_per_plan_id;
    }

    public void setMedication_per_plan_id(UUID medication_per_plan_id) {
        this.medication_per_plan_id = medication_per_plan_id;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public MedicationPlan getMedicationPlan() {
        return medicationPlan;
    }

    public void setMedicationPlan(MedicationPlan medicationPlan) {
        this.medicationPlan = medicationPlan;
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
}
