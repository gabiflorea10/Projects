package ro.utcluj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.config.SecurityContextUsernameGet;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.mapper.UserInfoMapper;
import ro.utcluj.model.User;
import ro.utcluj.repositories.UserRepository;


import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Service
@Transactional
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;
    private UserInfoMapper userInfoMapper;
    private SecurityContextUsernameGet securityContextUsernameGet;


    @Autowired
    public UserService(UserRepository userRepository, UserInfoMapper userInfoMapper, SecurityContextUsernameGet securityContextUsernameGet) {
        this.userRepository = userRepository;
        this.userInfoMapper = userInfoMapper;
        this.securityContextUsernameGet = securityContextUsernameGet;
    }

    public Integer updateUserData(String name, String phone, String email, String password){

            String contextUserName = securityContextUsernameGet.getUserNameFromContext();
            User userOld = userRepository.findByUsername(contextUserName);
            userOld.setName(name);
            userOld.setPhone(phone);
            userOld.setEmail(email);
            userOld.setUsername(contextUserName);
            userOld.setPassword(new BCryptPasswordEncoder().encode(password));
            userRepository.save(userOld);

            return 1;
    }

    public List<UserInfoDTO> findAllUserObservable(){
        List<User> lista = userRepository.findAll();
        return userInfoMapper.mapAll(lista);
    }

    public UserInfoDTO findByUser(){
        String contextUserName = securityContextUsernameGet.getUserNameFromContext();
        return userInfoMapper.map(userRepository.findByUsername(contextUserName));
    }

    public String addMoney(Integer money){
        String contextUserName = securityContextUsernameGet.getUserNameFromContext();
        User user = userRepository.findByUsername(contextUserName);
        Integer currentMoney = userRepository.getOne(user.getIduser()).getMoney();
        userRepository.getOne(user.getIduser()).setMoney(currentMoney+money);
        return "Added";
    }

    public Integer deleteUser(Integer idUser){
        userRepository.deleteById(idUser);
        return 1;
    }

    public String updateUser(Integer currentUserId, String name, String phone, String email, String username, String password, Integer money) {

        String mess = "";
        User user = userRepository.getOne(currentUserId);
            user.setName(name);
            user.setPhone(phone);
            user.setEmail(email);
            User userCheck = userRepository.findByUsername(username);
            if (userCheck == user)
                user.setUsername(username);
            else {
                mess = "Already existing username!";
                return mess;
            }
            if (!user.getPassword().equals(password)) {
                user.setPassword(new BCryptPasswordEncoder().encode(password));
            }
            user.setMoney(money);

        return mess;
    }

}
