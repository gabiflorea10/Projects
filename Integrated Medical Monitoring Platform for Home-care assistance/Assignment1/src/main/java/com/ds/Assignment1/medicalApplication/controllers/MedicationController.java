package com.ds.Assignment1.medicalApplication.controllers;

import com.ds.Assignment1.medicalApplication.models.dtos.MedicationInfoDTO;
import com.ds.Assignment1.medicalApplication.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {
    private MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping(value = "/{id}")
    public MedicationInfoDTO findMedicationById(@PathVariable("id") UUID id){
        return medicationService.findMedicationById(id);
    }

    @GetMapping(value = "/allMedication")
    public List<MedicationInfoDTO> findAllMedications(){
        return medicationService.findAllMedications();
    }

    @PostMapping(value = "/insertMedication")
    public String insertMedication(@RequestBody MedicationInfoDTO medicationInfoDTO){
        return medicationService.insertMedication(medicationInfoDTO);
    }

    @PutMapping(value = "/updateMedication")
    public String updateMedication(@RequestBody MedicationInfoDTO medicationInfoDTO){
        return medicationService.updateMedication(medicationInfoDTO);
    }

    @DeleteMapping(value = "/deleteMedication")
    public String deleteMedication(@RequestParam("name") String name){
        return medicationService.deleteMedication(name);
    }
}
