package com.ds.Assignment4.repositories;

import com.ds.Assignment4.models.entities.MonitorPills;
import com.ds.Assignment4.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface MonitorPillsRepository extends JpaRepository<MonitorPills, UUID> {

    List<MonitorPills> findAllByUserPatientAndIntakeDate(User userPatient, Date intakeDate);
    List<MonitorPills> findAllByUserPatient(User userPatient);

}

