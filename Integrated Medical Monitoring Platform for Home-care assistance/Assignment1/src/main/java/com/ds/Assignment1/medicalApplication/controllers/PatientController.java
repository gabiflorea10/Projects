package com.ds.Assignment1.medicalApplication.controllers;

import com.ds.Assignment1.medicalApplication.models.dtos.PatientInfoDTO;
import com.ds.Assignment1.medicalApplication.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/{id}")
    public PatientInfoDTO findPatientById(@PathVariable("id") UUID id){
        return patientService.findPatientById(id);
    }

    @GetMapping(value = "/allPatients")
    public List<PatientInfoDTO> findAllPatients(){
        return patientService.findAllPatients();
    }

    @GetMapping(value = "/allPatientsByCaregiverUsername")
    public List<PatientInfoDTO> findAllPatientsByCaregiverUsername(@RequestParam("username") String username){
        return patientService.findAllPatientsByCaregiverUsername(username);
    }

    @GetMapping(value = "/getPatientByUsername")
    public PatientInfoDTO findPatientByUsername(@RequestParam("username") String username){
        return patientService.findPatientByUsername(username);
    }

    @PostMapping(value = "/insertPatient")
    public String insertPatient(@RequestBody PatientInfoDTO patientInfoDTO){
        System.out.println(patientInfoDTO.toString());
        return patientService.insertPatient(patientInfoDTO);
    }

    @PutMapping(value = "/updatePatient")
    public String updatePatient(@RequestBody PatientInfoDTO patientInfoDTO){

        return patientService.updatePatient(patientInfoDTO);
    }

    @DeleteMapping(value = "/deletePatient")
    public String deletePatient(@RequestParam("username") String username){
        System.out.println(username);
        return patientService.deletePatient(username);
    }
}
