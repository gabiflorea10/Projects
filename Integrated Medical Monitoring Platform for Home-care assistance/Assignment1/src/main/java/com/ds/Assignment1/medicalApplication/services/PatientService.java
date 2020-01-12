package com.ds.Assignment1.medicalApplication.services;

import com.ds.Assignment1.medicalApplication.errorHandlers.ResourceNotFoundException;
import com.ds.Assignment1.medicalApplication.models.dtos.PatientInfoDTO;
import com.ds.Assignment1.medicalApplication.models.dtos.UserInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.Patient;
import com.ds.Assignment1.medicalApplication.models.entities.User;
import com.ds.Assignment1.medicalApplication.models.mappers.PatientInfoMapper;
import com.ds.Assignment1.medicalApplication.models.mappers.UserInfoMapper;
import com.ds.Assignment1.medicalApplication.repositories.PatientRepository;
import com.ds.Assignment1.medicalApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PatientService {

    private PatientRepository patientRepository;
    private UserRepository userRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public PatientInfoDTO findPatientById (UUID patient_id){
        Optional<Patient> patient = patientRepository.findById(patient_id);

        if(!patient.isPresent()){
            throw new ResourceNotFoundException("Patient", "patient_id", patient_id);
        }

        Optional<User> user = userRepository.findById(patient.get().getUserPatient().getUser_id());

        if(!user.isPresent()){
            throw new ResourceNotFoundException("User", "user_id", patient.get().getUserPatient().getUser_id());
        }


        return PatientInfoMapper.mapToDTO(user.get(), patient.get());
    }

    public PatientInfoDTO findPatientByUsername(String username){

        User user = userRepository.findByUsername(username).get();

        Patient patient = patientRepository.findByUserPatient(user).get();

        return PatientInfoMapper.mapToDTO(user, patient);
    }

    public List<PatientInfoDTO> findAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return PatientInfoMapper.mapAllToDTO(patients);
    }

    public List<PatientInfoDTO> findAllPatientsByCaregiverUsername(String username){
        User user = userRepository.findByUsername(username).get();
        List<Patient> patients = patientRepository.findAllByUserCaregiver(user);
        return PatientInfoMapper.mapAllToDTO(patients);
    }

    public UserInfoDTO findUserOfPatientById (UUID patient_id){
        Optional<User> user = userRepository.findById(patient_id);

        if(!user.isPresent()){
            throw new ResourceNotFoundException("User", "user_id", patient_id);
        }

        return UserInfoMapper.mapToDTO(user.get());

    }

    public String insertPatient(PatientInfoDTO patientInfoDTO){

        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setUser_id(UUID.randomUUID());
        userInfoDTO.setName(patientInfoDTO.getName());
        userInfoDTO.setUsername(patientInfoDTO.getUsername());
        userInfoDTO.setPassword(patientInfoDTO.getPassword());
        userInfoDTO.setBirthdate(patientInfoDTO.getBirthdate());
        userInfoDTO.setGender(patientInfoDTO.getGender());
        userInfoDTO.setRole("patient");

        User insertedUser = userRepository.save(UserInfoMapper.mapFromDTO(userInfoDTO));

        User caregiverUser = userRepository.findByUsername(patientInfoDTO.getCaregiver_username()).get();

        patientRepository.save(PatientInfoMapper.mapFromDTO(patientInfoDTO, insertedUser, caregiverUser));
        return "Patient inserted!";
    }

    public String deletePatient(String username){

        User user = userRepository.findUserByUsername(username);
        System.out.println("Step1");
        System.out.println(user.toString());
        Patient patient = patientRepository.findPatientByUserPatient(user);
        System.out.println("Step2");
        System.out.println(user.getUser_id());
        System.out.println(patient.getPatient_id());
        userRepository.deleteByUsername(username);
        patientRepository.deleteById(patient.getPatient_id());

        System.out.println("Step3");

        return "Patient deleted!";
    }

    public String updatePatient(PatientInfoDTO patientInfoDTO){
        User userPatient = userRepository.findUserByUsername(patientInfoDTO.getUsername());
        Patient patient = patientRepository.findPatientByUserPatient(userPatient);

        if(patient != null){
            userPatient.setName(patientInfoDTO.getName());
            userPatient.setUsername(patientInfoDTO.getUsername());
            userPatient.setPassword(patientInfoDTO.getPassword());
            userPatient.setBirthdate(patientInfoDTO.getBirthdate());
            patient.setMedical_record(patientInfoDTO.getMedical_record());

            User caregiver = userRepository.findUserByUsername(patientInfoDTO.getCaregiver_username());

            patient.setUserCaregiver(caregiver);

            userRepository.save(userPatient);
            patientRepository.save(patient);

        }


        return "Patient updated!";
    }
}
