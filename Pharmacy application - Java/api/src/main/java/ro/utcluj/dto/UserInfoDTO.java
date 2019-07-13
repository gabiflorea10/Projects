package ro.utcluj.dto;

import java.io.Serializable;

public class UserInfoDTO implements Serializable {

    private Integer iduser;
    private String name;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String typeofuser;
    private Integer money;

    public UserInfoDTO() {
    }

    public UserInfoDTO(Integer iduser, String name, String phone, String email, String username, String password, String typeofuser, Integer money) {
        this.iduser = iduser;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.password = password;
        this.typeofuser = typeofuser;
        this.money = money;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeofuser() {
        return typeofuser;
    }

    public void setTypeofuser(String typeofuser) {
        this.typeofuser = typeofuser;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "iduser=" + iduser +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", typeofuser='" + typeofuser + '\'' +
                ", money=" + money +
                '}';
    }
}
