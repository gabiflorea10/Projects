package com.ds.Assignment1.medicalApplication.models.dtos;

import java.util.UUID;

public class MedicationInfoDTO {
    private UUID medication_id;
    private String name;
    private String side_effects;
    private Integer dosage;

    public MedicationInfoDTO() {
    }

    public MedicationInfoDTO(UUID medication_id, String name, String side_effects, Integer dosage) {
        this.medication_id = medication_id;
        this.name = name;
        this.side_effects = side_effects;
        this.dosage = dosage;
    }

    public UUID getMedication_id() {
        return medication_id;
    }

    public void setMedication_id(UUID medication_id) {
        this.medication_id = medication_id;
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
        return "MedicationInfoDTO{" +
                "medication_id=" + medication_id +
                ", name='" + name + '\'' +
                ", side_effects='" + side_effects + '\'' +
                ", dosage=" + dosage +
                '}';
    }
}
