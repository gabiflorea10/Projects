package com.ds.Assignment1.sensorMonitoring.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ActivityNotificationController {

    private SimpMessagingTemplate template;

    @Autowired
    public ActivityNotificationController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/activitymonitor")
    @SendTo("/topic/activitymonitor")
    public String sendNotification(String activityAsJsonString){
        this.template.convertAndSend("/topic/activitymonitor", activityAsJsonString);
        return "Message sent!";
    }
}

