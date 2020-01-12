package com.ds.Assignment1.pillDispenser.entity;

import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import com.ds.Assignment1.medicalApplication.models.entities.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "monitors")
public class MonitorPills {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID id;

    @OneToOne
    private User userPatient;

    @OneToOne
    private Medication medication;

    @Column
    private Date intakeDate;

    @Column(name = "description")
    private String description;

    public MonitorPills() {
    }

    public MonitorPills(User userPatient, Medication medication, Date intakeDate, String description) {
        this.userPatient = userPatient;
        this.medication = medication;
        this.intakeDate = intakeDate;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUserPatient() {
        return userPatient;
    }

    public void setUserPatient(User userPatient) {
        this.userPatient = userPatient;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Date getIntakeDate() {
        return intakeDate;
    }

    public void setIntakeDate(Date intakeDate) {
        this.intakeDate = intakeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
