package com.ds.Assignment1.medicalApplication.models.dtos;

import java.sql.Date;
import java.util.UUID;

public class UserInfoDTO {
    private UUID user_id;
    private String name;
    private String username;
    private String password;
    private Date birthdate;
    private String gender;
    private String role;

    public UserInfoDTO() {
    }

    public UserInfoDTO(UUID user_id, String name, String username, String password, Date birthdate, String gender, String role) {
        this.user_id = user_id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.gender = gender;
        this.role = role;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
