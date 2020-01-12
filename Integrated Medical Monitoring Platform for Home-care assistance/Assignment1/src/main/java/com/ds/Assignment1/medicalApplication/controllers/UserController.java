package com.ds.Assignment1.medicalApplication.controllers;

import com.ds.Assignment1.medicalApplication.models.dtos.UserInfoDTO;
import com.ds.Assignment1.medicalApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{id}")
    public UserInfoDTO findUserById(@PathVariable("id") UUID id){
        return userService.findUserById(id);
    }

    @GetMapping(value = "/allUsers")
    public List<UserInfoDTO> findAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping(value = "/allCaregivers")
    public List<UserInfoDTO> findAllCaregivers(){
        return userService.findAllCaregivers();
    }

    @GetMapping(value = "/login")
    public UserInfoDTO loginUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.loginUser(username, password);
    }

    @PostMapping(value = "/insertUser")
    public String insertUser(@ModelAttribute UserInfoDTO userInfoDTO){
        return userService.insertUser(userInfoDTO);
    }

    @PostMapping(value = "/insertCaregiver")
    public String insertCaregiver(@RequestBody UserInfoDTO userInfoDTO){
        return userService.insertCaregiver(userInfoDTO);
    }

    @PutMapping(value = "/updateCaregiver")
    public String updateCaregiver(@RequestBody UserInfoDTO userInfoDTO){
        return userService.updateUser(userInfoDTO);
    }

    @DeleteMapping(value = "/deleteCaregiver")
    public String deleteCaregiver(@RequestParam("username") String username){
        return userService.deleteUser(username);
    }

    @PutMapping()
    public String updateUser(@ModelAttribute UserInfoDTO userInfoDTO){
        return userService.updateUser(userInfoDTO);
    }


}
