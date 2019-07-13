package ro.utcluj.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserInfoMapper {

    public UserInfoDTO map(User user){
        UserInfoDTO userInfoDTO = new UserInfoDTO();

        userInfoDTO.setIduser(user.getIduser());
        userInfoDTO.setName(user.getName());
        userInfoDTO.setPhone(user.getPhone());
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setUsername(user.getUsername());
        userInfoDTO.setPassword(user.getPassword());
        userInfoDTO.setTypeofuser(user.getTypeofuser());
        userInfoDTO.setMoney(user.getMoney());

        return userInfoDTO;

    }

    public List<UserInfoDTO> mapAll(List<User> users){
        List<UserInfoDTO> dtoUsers = new ArrayList<>();
        for(User user: users){
            dtoUsers.add(map(user));
        }
        return dtoUsers;
    }


}
