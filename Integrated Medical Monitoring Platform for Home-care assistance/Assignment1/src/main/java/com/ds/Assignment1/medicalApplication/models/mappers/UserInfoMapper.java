package com.ds.Assignment1.medicalApplication.models.mappers;

import com.ds.Assignment1.medicalApplication.models.dtos.UserInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserInfoMapper {

    public static UserInfoDTO mapToDTO (User user){
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setUser_id(user.getUser_id());
        userInfoDTO.setName(user.getName());
        userInfoDTO.setUsername(user.getUsername());
        userInfoDTO.setPassword(user.getPassword());
        userInfoDTO.setBirthdate(user.getBirthdate());
        userInfoDTO.setGender(user.getGender());
        userInfoDTO.setRole(user.getRole());

        return userInfoDTO;
    }

    public static List<UserInfoDTO> mapAllToDTO(List<User> users){
        List<UserInfoDTO> dtoUsers = new ArrayList<>();
        for(User user: users){
            dtoUsers.add(mapToDTO(user));
        }
        return dtoUsers;
    }

    public static User mapFromDTO (UserInfoDTO userInfoDTO){
        User user = new User();

        user.setUser_id(userInfoDTO.getUser_id());
        user.setName(userInfoDTO.getName());
        user.setUsername(userInfoDTO.getUsername());
        user.setPassword(userInfoDTO.getPassword());
        user.setBirthdate(userInfoDTO.getBirthdate());
        user.setGender(userInfoDTO.getGender());
        user.setRole(userInfoDTO.getRole());

        return user;
    }

    public static List<User> mapAllFromDTO(List<UserInfoDTO> userInfoDTOS){
        List<User> users = new ArrayList<>();
        for(UserInfoDTO userInfoDTO: userInfoDTOS){
            users.add(mapFromDTO(userInfoDTO));
        }
        return users;
    }

}
