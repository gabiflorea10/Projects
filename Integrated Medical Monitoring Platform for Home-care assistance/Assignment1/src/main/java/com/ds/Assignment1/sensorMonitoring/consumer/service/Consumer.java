package com.ds.Assignment1.sensorMonitoring.consumer.service;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Consumer implements MessageListener {

    private SensorService sensorService;

    @Autowired
    public Consumer(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    @RabbitListener(queues = "PatientSensorQueue")
    public void onMessage(Message message) {
        String body = new String(message.getBody());
        JSONObject sensorData = null;
        try {
            sensorData = (JSONObject) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(body);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        sensorService.manageMessages(sensorData);

    }
}
