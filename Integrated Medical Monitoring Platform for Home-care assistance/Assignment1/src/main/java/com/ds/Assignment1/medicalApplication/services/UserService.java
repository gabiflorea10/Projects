package com.ds.Assignment1.medicalApplication.services;

import com.ds.Assignment1.medicalApplication.errorHandlers.ResourceNotFoundException;
import com.ds.Assignment1.medicalApplication.models.dtos.UserInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.Patient;
import com.ds.Assignment1.medicalApplication.models.entities.User;
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
public class UserService {
    private UserRepository userRepository;
    private PatientRepository patientRepository;

    @Autowired
    public UserService(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    public UserInfoDTO findUserById (UUID user_id){
        Optional<User> user = userRepository.findById(user_id);

        if(!user.isPresent()){
            throw new ResourceNotFoundException("User", "user_id", user_id);
        }

        return UserInfoMapper.mapToDTO(user.get());

    }

    public User findUserByUsername (String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.get();

    }

    public List<UserInfoDTO> findAllUsers(){
        List<User> users = userRepository.findAll();
        return UserInfoMapper.mapAllToDTO(users);
    }

    public List<UserInfoDTO> findAllCaregivers(){
        List<User> users = userRepository.findAllByRole("caregiver");
        return UserInfoMapper.mapAllToDTO(users);
    }

    public UserInfoDTO loginUser (String username, String password){
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

        if(!user.isPresent()){
            throw new ResourceNotFoundException("User", "user_name", username);
        }

        return UserInfoMapper.mapToDTO(user.get());

    }

    public String insertUser(UserInfoDTO userInfoDTO){
        userRepository.save(UserInfoMapper.mapFromDTO(userInfoDTO));
        return "User inserted!";
    }

    public String insertCaregiver(UserInfoDTO userInfoDTO){
        userInfoDTO.setRole("caregiver");
        userRepository.save(UserInfoMapper.mapFromDTO(userInfoDTO));
        return "Caregiver inserted!";
    }

    public String deleteUser(String username){
        if(username.equals("spital")) return "Default caregiver cannot be deleted.";
        User userCaregiver = userRepository.findByUsername(username).get();
        User defaultCaregiver = userRepository.findByUsername("spital").get();
        List<Patient> patients = patientRepository.findAllByUserCaregiver(userCaregiver);
        for(Patient patient: patients){
            patient.setUserCaregiver(defaultCaregiver);
            patientRepository.save(patient);
        }
        Integer respond = userRepository.deleteByUsername(username);
        return respond + " User deleted!";
    }

    @Transactional
    public String updateUser(UserInfoDTO userInfoDTO){

        User user = userRepository.findUserByUsername(userInfoDTO.getUsername());

        if(user != null){
            userInfoDTO.setUser_id(user.getUser_id());
            userInfoDTO.setGender(user.getGender());
            userInfoDTO.setRole(user.getRole());
            userRepository.save(UserInfoMapper.mapFromDTO(userInfoDTO));
        }

        return "User updated!";
    }
}
