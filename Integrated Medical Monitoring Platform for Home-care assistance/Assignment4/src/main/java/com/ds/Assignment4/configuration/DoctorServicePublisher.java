package com.ds.Assignment4.configuration;

import com.ds.Assignment4.repositories.*;
import com.ds.Assignment4.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class DoctorServicePublisher {

    private ActivityMonitorRepository activityMonitorRepository;
    private MonitorPillsRepository monitorPillsRepository;
    private PatientRepository patientRepository;
    private UserRepository userRepository;
    private RecommendationRepository recommendationRepository;

    @Autowired
    public DoctorServicePublisher(ActivityMonitorRepository activityMonitorRepository, MonitorPillsRepository monitorPillsRepository, PatientRepository patientRepository, UserRepository userRepository, RecommendationRepository recommendationRepository) {
        this.activityMonitorRepository = activityMonitorRepository;
        this.monitorPillsRepository = monitorPillsRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Bean
    public void publishDoctorServices(){
        new Thread(() -> {
            Endpoint.publish("http://0.0.0.0:9999/webservices/doctorservices", new DoctorService(activityMonitorRepository, monitorPillsRepository, patientRepository, userRepository, recommendationRepository));
        }
         ).start();

    }
}
