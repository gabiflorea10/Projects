package com.ds.Assignment1.medicalApplication.controllers;

import assignment4net.assignment4net.service.CaregiverService;
import assignment4net.assignment4net.service.CaregiverServiceService;
import assignment4net.assignment4net.service.RecommendationDTO;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    @GetMapping(value = "/viewRecommendations")
    public List<RecommendationDTO> viewRecommendations(@RequestParam("caregiverUsername") String caregiverUsername) {

        CaregiverService caregiverService = new CaregiverServiceService().getCaregiverServicePort();

        return caregiverService.viewRecommendations(caregiverUsername);

    }

}
