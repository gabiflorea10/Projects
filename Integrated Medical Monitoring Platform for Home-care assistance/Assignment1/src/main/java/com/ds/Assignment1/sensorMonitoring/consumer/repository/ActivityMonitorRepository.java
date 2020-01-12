package com.ds.Assignment1.sensorMonitoring.consumer.repository;

import com.ds.Assignment1.sensorMonitoring.consumer.model.entity.ActivityMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivityMonitorRepository extends JpaRepository<ActivityMonitor, UUID> {

}

