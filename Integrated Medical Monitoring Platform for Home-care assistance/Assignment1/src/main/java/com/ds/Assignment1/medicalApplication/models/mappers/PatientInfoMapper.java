package com.ds.Assignment1.medicalApplication.models.mappers;

import com.ds.Assignment1.medicalApplication.models.dtos.PatientInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.Patient;
import com.ds.Assignment1.medicalApplication.models.entities.User;
import com.ds.Assignment1.medicalApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PatientInfoMapper {

    @Autowired
    UserService userService;

    public static PatientInfoDTO mapToDTO (User user, Patient patient){
        PatientInfoDTO patientInfoDTO = new PatientInfoDTO();

        patientInfoDTO.setPatient_id(patient.getPatient_id());
        patientInfoDTO.setMedical_record(patient.getMedical_record());
        patientInfoDTO.setName(user.getName());
        patientInfoDTO.setUsername(user.getUsername());
        patientInfoDTO.setPassword(user.getPassword());
        patientInfoDTO.setBirthdate(user.getBirthdate());
        patientInfoDTO.setGender(user.getGender());
        patientInfoDTO.setCaregiver_username(patient.getUserCaregiver().getUsername());

        return patientInfoDTO;
    }

    public static List<PatientInfoDTO> mapAllToDTO(List<Patient> patients){
        List<PatientInfoDTO> patientInfoDTOS = new ArrayList<>();
        for(Patient patient: patients){
            patientInfoDTOS.add(mapToDTO(patient.getUserPatient(), patient));
        }
        return patientInfoDTOS;
    }

    public static Patient mapFromDTO (PatientInfoDTO patientInfoDTO, User insertedUser, User caregiverUser){
        Patient patient = new Patient();

        patient.setPatient_id(patientInfoDTO.getPatient_id());
        patient.setMedical_record(patientInfoDTO.getMedical_record());
        patient.setUserCaregiver(caregiverUser);
        patient.setUserPatient(insertedUser);

        return patient;
    }
}
