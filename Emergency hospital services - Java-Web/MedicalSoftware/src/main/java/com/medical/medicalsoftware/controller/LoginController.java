package com.medical.medicalsoftware.controller;

import com.medical.medicalsoftware.model.Staff;
import com.medical.medicalsoftware.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LoginController {

    private StaffService staffService;

    public LoginController(StaffService staffService) {
        this.staffService = staffService;
    }

    @RequestMapping(value = {"","/", "/login", "login"}, method = RequestMethod.GET)
    public String loginGet(){
        return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String loginPost(@ModelAttribute(name = "loginStaff") Staff staff, Model model){
            String medUsername = staff.getMedUsername();
            String medPassword = staff.getMedPassword();
            Staff loggedStaff = staffService.findByMedUsername(medUsername);
        if(loggedStaff != null && loggedStaff.getMedPassword().equals(medPassword)){
            staffService.setLoggedStaff(loggedStaff);
            System.out.println(loggedStaff.toString());
            return "redirect:/startPage";
        }

        model.addAttribute("invalidLogin",true);
        return "login";

    }

    @RequestMapping(value = {"/register", "register"}, method = RequestMethod.GET)
    public String registerGet(){
        return "register";
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public String registerPost(@ModelAttribute(name = "registerStaff") Staff staff, Model model){
        String medUsername = staff.getMedUsername();
        String medPassword = staff.getMedPassword();
        String medName = staff.getMedName();
        String medType = staff.getMedType();
        String medSpecialization = staff.getMedSpecialization();
        String medYearOfPracticeBeginning = staff.getMedYearOfPracticeBeginning();

        Staff registeredStaff = staffService.findByMedUsername(medUsername);

        if(registeredStaff != null){
            model.addAttribute("invalidRegister", true);
            return "register";
        }

        staffService.addNewStaff(new Staff(medUsername,medPassword,medName,medType,medSpecialization,medYearOfPracticeBeginning));

        return "login";

    }



}
