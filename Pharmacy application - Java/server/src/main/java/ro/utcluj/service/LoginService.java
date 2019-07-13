package ro.utcluj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcluj.api.LoginServiceInterface;
import ro.utcluj.config.SecurityContextUsernameGet;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.mapper.UserInfoMapper;
import ro.utcluj.model.User;
import ro.utcluj.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class LoginService implements LoginServiceInterface {

    private UserRepository userRepository;
    private UserInfoMapper userInfoMapper;
    private SecurityContextUsernameGet securityContextUsernameGet;

    @Autowired
    public LoginService(UserRepository userRepository, UserInfoMapper userInfoMapper, SecurityContextUsernameGet securityContextUsernameGet) {
        this.userRepository = userRepository;
        this.userInfoMapper = userInfoMapper;
        this.securityContextUsernameGet = securityContextUsernameGet;
    }

    public UserInfoDTO loginUser(){

        String contextUserName = securityContextUsernameGet.getUserNameFromContext();

        User user = userRepository.findByUsername(contextUserName);

        UserInfoDTO userInfoDTO = userInfoMapper.map(user);

        if (user != null) {
            return userInfoDTO;
        }
        return null;

    }

    public String saveNewUser(String name, String phone, String email, String username, String password, Integer money){

        String messsage = "";
        User user = userRepository.findByUsername(username);

        if(user == null){
            user = new User(name, phone, email, username, new BCryptPasswordEncoder().encode(password), "client", money);
            userRepository.save(user);
        }
        else{
            messsage = "Already existing user!";
        }

        return messsage;
    }

}
