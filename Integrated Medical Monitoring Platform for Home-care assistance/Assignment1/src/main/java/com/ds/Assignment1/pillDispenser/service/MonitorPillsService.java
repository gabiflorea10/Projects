package com.ds.Assignment1.pillDispenser.service;

import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import com.ds.Assignment1.medicalApplication.models.entities.User;
import com.ds.Assignment1.medicalApplication.repositories.MedicationRepository;
import com.ds.Assignment1.medicalApplication.repositories.UserRepository;
import com.ds.Assignment1.pillDispenser.entity.MonitorPills;
import com.ds.Assignment1.pillDispenser.repository.MonitorPillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;

@Service
@Transactional
public class MonitorPillsService {

    private MonitorPillsRepository monitorPillsRepository;
    private UserRepository userRepository;
    private MedicationRepository medicationRepository;

    @Autowired
    public MonitorPillsService(MonitorPillsRepository monitorPillsRepository, UserRepository userRepository, MedicationRepository medicationRepository) {
        this.monitorPillsRepository = monitorPillsRepository;
        this.userRepository = userRepository;
        this.medicationRepository = medicationRepository;

    }

    public String insertMonitorPill(String currentDate, String patientName, String medicationName, String status){

        Date dayDate = Date.valueOf(currentDate);

        User user = userRepository.findUserByName(patientName);
        Medication medication = medicationRepository.findByName(medicationName);

        monitorPillsRepository.save(new MonitorPills(user, medication, dayDate, status));

        return "Inserted successfully!";
    }
}
