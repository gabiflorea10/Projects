package com.medical.medicalsoftware.service;

import com.medical.medicalsoftware.model.Patient;
import com.medical.medicalsoftware.model.Staff;

import java.util.List;
import java.util.Set;

public interface PatientService {
    Patient findByPatientName(String patientName);
    List<Patient> getPatients();
    void addNewPatient(Patient patient);
    void deletePatient(String patientName);
}
