package ro.utcluj.api;

import ro.utcluj.dto.UserInfoDTO;

import java.util.List;

public interface UserServiceInterface {

    Integer updateUserData(String name, String phone, String email, String password);

    List<UserInfoDTO> findAllUserObservable();

    String addMoney(Integer money);

    Integer deleteUser(Integer idUser);

    String updateUser(Integer currentUserId, String name, String phone, String email, String username, String password, Integer money);

    UserInfoDTO findByUser();
}
