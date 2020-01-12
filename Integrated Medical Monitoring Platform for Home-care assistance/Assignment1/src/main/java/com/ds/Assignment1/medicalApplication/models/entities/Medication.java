package com.ds.Assignment1.medicalApplication.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", unique = true, nullable = false)
    private UUID medication_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "side_effects")
    private String side_effects;

    @Column(name = "dosage")
    private Integer dosage;

    public Medication() {
    }

    public Medication(String name, String side_effects, Integer dosage) {
        this.name = name;
        this.side_effects = side_effects;
        this.dosage = dosage;
    }

    public UUID getMedication_id() {
        return medication_id;
    }

    public void setMedication_id(UUID drug_id) {
        this.medication_id = drug_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public Integer getDosage() {
        return dosage;
    }

    public void setDosage(Integer dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medication_id=" + medication_id +
                ", name='" + name + '\'' +
                ", side_effects='" + side_effects + '\'' +
                ", dosage=" + dosage +
                '}';
    }
}
