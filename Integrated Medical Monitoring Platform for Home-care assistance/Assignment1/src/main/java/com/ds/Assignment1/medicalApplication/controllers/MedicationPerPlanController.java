package com.ds.Assignment1.medicalApplication.controllers;

import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPerPlanInfoDTO;
import com.ds.Assignment1.medicalApplication.services.MedicationPerPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication_per_plan")
public class MedicationPerPlanController {
    private MedicationPerPlanService medicationPerPlanService;

    @Autowired
    public MedicationPerPlanController(MedicationPerPlanService medicationPerPlanService) {
        this.medicationPerPlanService = medicationPerPlanService;
    }

    @GetMapping(value = "/{id}")
    public MedicationPerPlanInfoDTO findMedicationPerPlanById(@PathVariable("id") UUID id){
        return medicationPerPlanService.findMedicationPerPlanById(id);
    }

    @GetMapping(value = "/allMedicationPerPlans")
    public List<MedicationPerPlanInfoDTO> findAllMedicationPlans(){
        return medicationPerPlanService.findAllMedicationPerPlans();
    }

    @PostMapping(value = "/insertMedicationPerPlan")
    public String insertMedicationPerPlan(@ModelAttribute MedicationPerPlanInfoDTO medicationPerPlanInfoDTO){
        return medicationPerPlanService.insertMedicationPerPlan(medicationPerPlanInfoDTO);
    }


    @DeleteMapping(value = "/deleteMedicationPerPlan")
    public String deleteMedicationPerPlan(@ModelAttribute MedicationPerPlanInfoDTO medicationPerPlanInfoDTO){
        return medicationPerPlanService.deleteMedicationPerPlan(medicationPerPlanInfoDTO);
    }
}
