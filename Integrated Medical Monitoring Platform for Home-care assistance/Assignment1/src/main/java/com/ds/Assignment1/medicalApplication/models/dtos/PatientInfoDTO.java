package com.ds.Assignment1.medicalApplication.models.dtos;

import java.sql.Date;
import java.util.UUID;

public class PatientInfoDTO {
    private UUID patient_id;
    private String name;
    private String username;
    private String password;
    private Date birthdate;
    private String gender;
    private String medical_record;
    private String caregiver_username;

    public PatientInfoDTO() {

    }

    public PatientInfoDTO(UUID patient_id, String name, String username, String password, Date birthdate, String gender, String medical_record, String caregiver_username) {
        this.patient_id = patient_id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.medical_record = medical_record;
        this.caregiver_username = caregiver_username;
    }

    public UUID getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(UUID patient_id) {
        this.patient_id = patient_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public String getCaregiver_username() {
        return caregiver_username;
    }

    public void setCaregiver_username(String caregiver_username) {
        this.caregiver_username = caregiver_username;
    }

    @Override
    public String toString() {
        return "PatientInfoDTO{" +
                "patient_id=" + patient_id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", medical_record='" + medical_record + '\'' +
                ", caregiver_username='" + caregiver_username + '\'' +
                '}';
    }
}
