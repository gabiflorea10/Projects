package ro.utcluj.api;

import ro.utcluj.dto.UserInfoDTO;

public interface LoginServiceInterface {
    UserInfoDTO loginUser();
    String saveNewUser(String name, String phone, String email, String username, String password, Integer money);
}
