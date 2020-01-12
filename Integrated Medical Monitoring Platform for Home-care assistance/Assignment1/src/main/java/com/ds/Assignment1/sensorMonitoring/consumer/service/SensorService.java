package com.ds.Assignment1.sensorMonitoring.consumer.service;

import com.ds.Assignment1.medicalApplication.models.entities.Patient;
import com.ds.Assignment1.medicalApplication.repositories.PatientRepository;
import com.ds.Assignment1.sensorMonitoring.consumer.controller.ActivityNotificationController;
import com.ds.Assignment1.sensorMonitoring.consumer.model.dto.ActivityMonitorInfoDTO;
import com.ds.Assignment1.sensorMonitoring.consumer.model.entity.ActivityMonitor;
import com.ds.Assignment1.sensorMonitoring.consumer.repository.ActivityMonitorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class SensorService {

    private PatientRepository patientRepository;
    private ActivityMonitorRepository activityMonitorRepository;
    private ActivityNotificationController activityNotificationController;

    @Autowired
    public SensorService(PatientRepository patientRepository, ActivityMonitorRepository activityMonitorRepository, ActivityNotificationController activityNotificationController) {
        this.patientRepository = patientRepository;
        this.activityMonitorRepository = activityMonitorRepository;
        this.activityNotificationController = activityNotificationController;
    }

    public String manageMessages(JSONObject sensorData) {
        UUID patient_id = UUID.fromString(sensorData.getAsString("patient_id"));
        Long start_time = sensorData.getAsNumber("start").longValue();
        Long end_time = sensorData.getAsNumber("end").longValue();
        String activity_label = sensorData.getAsString("activity");


        //Display messages extracted from the queue

        System.out.println();
        System.out.println("Activity:");
        System.out.println(patient_id);
        System.out.println(new Date(start_time));
        System.out.println(new Date(end_time));
        System.out.println(activity_label);
        System.out.println();


        //Verify rules

        this.verifyRules(patient_id, start_time, end_time, activity_label);

        return "Done!";
    }

    private String verifyRules(UUID patient_id, Long start_time, Long end_time, String activity_label){

        Boolean anomalous = false;
        Long period = 0L;
        Double hours = 0.0;

        if(activity_label.equals("Sleeping")){
            period = end_time - start_time;
            hours = period / 3600000.0;

            if(hours > 8.5){
                anomalous = true;
            }

        }
        else if(activity_label.equals("Toileting")){
            period = end_time - start_time;
            hours = period / 3600000.0;
            if(hours > 0.03){
                anomalous = true;
            }
        }
        else if(activity_label.equals("Showering")){
            period = end_time - start_time;
            hours = period / 3600000.0;
            if(hours > 0.08){
                anomalous = true;
            }
        }
        else if (activity_label.equals("Leaving")){
            period = end_time - start_time;
            hours = period / 3600000.0;
            if(hours > 3.5){
                anomalous = true;
            }

        }

        //Save in database

        this.saveInDatabase(patient_id, start_time, end_time, activity_label, anomalous, hours);

        return "Done!";
    }

    private String saveInDatabase(UUID patient_id, Long start_time, Long end_time, String activity_label, Boolean anomalous, Double hours) {
        ActivityMonitor activityMonitor = new ActivityMonitor();
        Patient patient = patientRepository.findById(patient_id).get();
        activityMonitor.setPatient(patient);
        activityMonitor.setStart_time(new Timestamp(start_time));
        activityMonitor.setEnd_time(new Timestamp(end_time));
        activityMonitor.setActivity_label(activity_label);
        if(anomalous){
            activityMonitor.setIs_anomalous("true");
        }
        else{
            activityMonitor.setIs_anomalous("false");
        }


        activityMonitorRepository.save(activityMonitor);

        //Send notification
        this.sendNotification(patient_id, start_time, end_time, activity_label, anomalous, hours, patient);

    return "Done!";
    }

    private String sendNotification(UUID patient_id, Long start_time, Long end_time, String activity_label, Boolean anomalous, Double hours, Patient patient) {
        if(anomalous){
            ActivityMonitorInfoDTO activityMonitorInfoDTO = new ActivityMonitorInfoDTO();
            activityMonitorInfoDTO.setPatient_name(patient.getUserPatient().getName());
            activityMonitorInfoDTO.setHours(hours);
            activityMonitorInfoDTO.setActivity_label(activity_label);
            activityMonitorInfoDTO.setCaregiver_username(patient.getUserCaregiver().getUsername());

            ObjectMapper objectMapper = new ObjectMapper();

            String activityAsJsonString="";

            try {
                activityAsJsonString = objectMapper.writeValueAsString(activityMonitorInfoDTO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            String sentStatus = activityNotificationController.sendNotification(activityAsJsonString);
            System.out.println(sentStatus);

        }

        return "Done!";
    }
}
