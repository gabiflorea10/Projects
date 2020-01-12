package com.ds.Assignment1.medicalApplication.controllers;


import com.ds.assignment4.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

    @GetMapping(value = "/getActivityForPatient")
    public List<ActivityMonitorInfoDTO> getActivityForPatient(@RequestParam("patientName") String patientName) {

        DoctorService doctorService = new DoctorServiceService().getDoctorServicePort();

        return doctorService.getActivityForPatient(patientName);

    }

    @GetMapping(value = "/getActivityCharts")
    public ActivitiesChartDTO getActivitiesChart(@RequestParam("patientName") String patientName, @RequestParam("date") String date) {

        DoctorService doctorService = new DoctorServiceService().getDoctorServicePort();

        return doctorService.getActivitiesChart(patientName, date);

    }


    @GetMapping(value = "/getMonitors")
    public List<MonitorPillsInfoDTO> getMonitorForPatientAndDate(@RequestParam("patientName") String patientName, @RequestParam("givenDate") String givenDate) {

        DoctorService doctorService = new DoctorServiceService().getDoctorServicePort();

        return doctorService.getMonitorForPatientAndDate(patientName, givenDate);
    }

    @PutMapping (value = "/annotateActivity")
    public String annotateActivity(@RequestParam("activityId") String activityId){

        DoctorService doctorService = new DoctorServiceService().getDoctorServicePort();

        return doctorService.annotateActivity(activityId);
    }

    @PostMapping(value = "/addRecommendation")
    public String addRecommendation(@RequestParam("patientName") String patientName, @RequestParam("description") String description){

        DoctorService doctorService = new DoctorServiceService().getDoctorServicePort();

        return doctorService.addRecommendation(patientName, description);
    }

}