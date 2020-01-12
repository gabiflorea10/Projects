package com.ds.Assignment2.producer.service;

import com.ds.Assignment2.producer.entity.SensorMonitor;
import net.minidev.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class Producer {

    private AmqpTemplate amqpTemplate;

    @Autowired
    public Producer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    private static final String queueName = "PatientSensorQueue";
    private static final String exchange = "Patient.Data";
    private static final String routingKey = "Patient.SensorMonitor";

    public void sendSensorInfo(){

        Random random = new Random();

        List<UUID> patientsId = new ArrayList<>();
        patientsId.add(UUID.fromString("d95822ba-097f-49a5-9ebe-0abf145572bf"));
        patientsId.add(UUID.fromString("205f0e95-2dde-41a7-939a-6f46a7352966"));
        patientsId.add(UUID.fromString("046c8241-9953-4aa3-8d60-079a7b20e6db"));
        patientsId.add(UUID.fromString("cac0725f-ae01-433f-824e-c9b120b25174"));
        patientsId.add(UUID.fromString("f0327552-f985-4f29-9e84-9fb56da6363c"));

        int no_of_patients = patientsId.size();

        int random_patient;

        List<SensorMonitor> sensorMonitors = FileParser.readFile();

        for(SensorMonitor sensorMonitor : sensorMonitors){
            random_patient = random.nextInt(no_of_patients);
            sensorMonitor.setPatient_id(patientsId.get(random_patient));

            //Explicitly map to JSON
            JSONObject sensorJSON = new JSONObject();
            sensorJSON.put("patient_id", sensorMonitor.getPatient_id().toString());
            sensorJSON.put("activity", sensorMonitor.getActivity_label());
            sensorJSON.put("start", sensorMonitor.getStart_time().getTime());
            sensorJSON.put("end", sensorMonitor.getEnd_time().getTime());

            System.out.println(sensorJSON);
            amqpTemplate.convertAndSend(exchange, routingKey, sensorJSON);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }



}
