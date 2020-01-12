package com.ds.Assignment2.producer.controller;

import com.ds.Assignment2.producer.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueueController {

    private Producer producer;

    @Autowired
    public QueueController(Producer producer) {
        this.producer = producer;
    }

    @GetMapping("/startMonitoring")
    public String sendSensorsInformation(){
        producer.sendSensorInfo();
        return "Sensor monitoring platform is working!";
    }


}
