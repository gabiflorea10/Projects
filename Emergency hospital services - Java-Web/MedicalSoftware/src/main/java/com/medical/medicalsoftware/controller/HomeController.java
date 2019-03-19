package com.medical.medicalsoftware.controller;

import com.medical.medicalsoftware.model.Patient;
import com.medical.medicalsoftware.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    private  PatientService patientService;

    public HomeController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = {"","/", "/home"}, method = RequestMethod.GET)
    public String firstPrint(Model model){
        List<Patient> patients = patientService.getPatients();
        model.addAttribute("patients", patients);
        return "home";
    }
}
