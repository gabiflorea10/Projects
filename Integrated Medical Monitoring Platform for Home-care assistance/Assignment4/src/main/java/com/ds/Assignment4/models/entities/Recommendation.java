package com.ds.Assignment4.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "recommendation")
public class Recommendation {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID recommendation_id;

    @OneToOne
    private User userPatient;

    @Column
    private String patientName;

    @Column
    private String description;

    @Column
    private String caregiverUsername;

    public Recommendation() {
    }

    public Recommendation(User userPatient, String patientName, String description, String caregiverUsername) {
        this.userPatient = userPatient;
        this.patientName = patientName;
        this.description = description;
        this.caregiverUsername = caregiverUsername;
    }

    public UUID getRecommendation_id() {
        return recommendation_id;
    }

    public void setRecommendation_id(UUID recommendation_id) {
        this.recommendation_id = recommendation_id;
    }

    public User getUserPatient() {
        return userPatient;
    }

    public void setUserPatient(User userPatient) {
        this.userPatient = userPatient;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaregiverUsername() {
        return caregiverUsername;
    }

    public void setCaregiverUsername(String caregiverUsername) {
        this.caregiverUsername = caregiverUsername;
    }
}
