package com.medical.medicalsoftware.service;

import com.medical.medicalsoftware.model.Patient;
import com.medical.medicalsoftware.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PatientServiceImplementation implements PatientService {

    private PatientRepository patientRepository;

    public PatientServiceImplementation(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findByPatientName(String patientName){
        return patientRepository.findByPatientName(patientName);
    }

    @Override
    public List<Patient> getPatients(){
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().iterator().forEachRemaining(patients::add);
        return patients;
    }

    @Override
    public void addNewPatient(Patient patient){
        patientRepository.save(patient);
    }

    @Override
    public void deletePatient(String patientName){
        patientRepository.deletePatientByPatientName(patientName);
    }

}
