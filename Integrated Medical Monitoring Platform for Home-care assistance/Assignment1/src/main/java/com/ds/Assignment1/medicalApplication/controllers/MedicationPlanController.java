package com.ds.Assignment1.medicalApplication.controllers;

import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPlanInfoDTO;
import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPlanView;
import com.ds.Assignment1.medicalApplication.services.MedicationPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication_plan")
public class MedicationPlanController {
    private MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService) {
        this.medicationPlanService = medicationPlanService;
    }

    @GetMapping(value = "/{id}")
    public MedicationPlanInfoDTO findMedicationPlanById(@PathVariable("id") UUID id){
        return medicationPlanService.findMedicationPlanById(id);
    }

    @GetMapping(value = "/allMedicationPlans")
    public List<MedicationPlanInfoDTO> findAllMedicationPlans(){
        return medicationPlanService.findAllMedicationPlans();
    }

    @GetMapping(value = "/allMedicationPlanViews")
    public List<MedicationPlanView> findAllMedicationPlansViews(){
        return medicationPlanService.findAllMedicationPlanViews();
    }

    @GetMapping(value = "/allMedicationPlansByUsername")
    public List<MedicationPlanView> findAllMedicationPlansByUsername(@RequestParam("username") String username){
        return medicationPlanService.findAllMedicationPlansByUsername(username);
    }

    @GetMapping(value = "/allMedicationPlansByCaregiverUsername")
    public List<MedicationPlanView> findAllMedicationPlansByCaregiverUsername(@RequestParam("username") String username){
        return medicationPlanService.findAllMedicationPlansByCaregiverUsername(username);
    }

    @PostMapping(value = "/insertMedicationPlan")
    public String insertMedicationPlan(@ModelAttribute MedicationPlanInfoDTO medicationPlanInfoDTO){

        return medicationPlanService.insertMedicationPlan(medicationPlanInfoDTO);
    }

    @PostMapping(value = "/insertMedicationPlanView")
    public String insertMedicationPlanView(@RequestBody MedicationPlanView medicationPlanView){
        return medicationPlanService.insertMedicationPlanView(medicationPlanView);
    }

    @DeleteMapping(value = "/deleteMedicationPlan")
    public String deleteMedicationPlan(@ModelAttribute MedicationPlanInfoDTO medicationPlanInfoDTO){
        return medicationPlanService.deleteMedicationPlan(medicationPlanInfoDTO);
    }
}
