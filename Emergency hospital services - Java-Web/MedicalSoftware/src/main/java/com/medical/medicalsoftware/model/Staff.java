package com.medical.medicalsoftware.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Staff {
    private String medUsername;
    private String medPassword;
    private String medName;
    private String medType;
    private String medSpecialization;
    private String medYearOfPracticeBeginning;

    public Staff(String medUsername, String medPassword, String medName, String medType, String medSpecialization, String medYearOfPracticeBeginning) {
        this.medUsername = medUsername;
        this.medPassword = medPassword;
        this.medName = medName;
        this.medType = medType;
        this.medSpecialization = medSpecialization;
        this.medYearOfPracticeBeginning = medYearOfPracticeBeginning;
    }

    public String getMedUsername() {
        return medUsername;
    }

    public void setMedUsername(String medUsername) {
        this.medUsername = medUsername;
    }

    public String getMedPassword() {
        return medPassword;
    }

    public void setMedPassword(String medPassword) {
        this.medPassword = medPassword;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedType() {
        return medType;
    }

    public void setMedType(String medType) {
        this.medType = medType;
    }

    public String getMedSpecialization() {
        return medSpecialization;
    }

    public void setMedSpecialization(String medSpecialization) {
        this.medSpecialization = medSpecialization;
    }

    public String getMedYearOfPracticeBeginning() {
        return medYearOfPracticeBeginning;
    }

    public void setMedYearOfPracticeBeginning(String medYearOfPracticeBeginning) {
        this.medYearOfPracticeBeginning = medYearOfPracticeBeginning;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "medUsername='" + medUsername + '\'' +
                ", medPassword='" + medPassword + '\'' +
                ", medName='" + medName + '\'' +
                ", medType='" + medType + '\'' +
                ", medSpecialization='" + medSpecialization + '\'' +
                ", medYearOfPracticeBeginning='" + medYearOfPracticeBeginning + '\'' +
                '}';
    }
}


