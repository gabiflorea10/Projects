package com.ds.Assignment1.medicalApplication.models.dtos;

import java.sql.Date;
import java.util.UUID;

public class MedicationPerPlanInfoDTO {
    private UUID medication_per_plan_id;
    private String medication_name;
    private UUID medication_plan_id;
    private String intake_interval;
    private Date start_time;
    private Date end_time;

    public MedicationPerPlanInfoDTO() {
    }

    public MedicationPerPlanInfoDTO(UUID medication_per_plan_id, String medication_name, UUID medication_plan_id, String intake_interval, Date start_time, Date end_time) {
        this.medication_per_plan_id = medication_per_plan_id;
        this.medication_name = medication_name;
        this.medication_plan_id = medication_plan_id;
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

    public String getMedication_name() {
        return medication_name;
    }

    public void setMedication_name(String medication_name) {
        this.medication_name = medication_name;
    }

    public UUID getMedication_plan_id() {
        return medication_plan_id;
    }

    public void setMedication_plan_id(UUID medication_plan_id) {
        this.medication_plan_id = medication_plan_id;
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

    @Override
    public String toString() {
        return "MedicationPerPlanInfoDTO{" +
                "medication_per_plan_id=" + medication_per_plan_id +
                ", medication_name='" + medication_name + '\'' +
                ", medication_plan_id=" + medication_plan_id +
                ", intake_interval=" + intake_interval +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
