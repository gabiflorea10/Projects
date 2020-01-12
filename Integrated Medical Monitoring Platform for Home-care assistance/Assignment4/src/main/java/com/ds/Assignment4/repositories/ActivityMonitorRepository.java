package com.ds.Assignment4.repositories;

import com.ds.Assignment4.models.entities.ActivityMonitor;
import com.ds.Assignment4.models.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivityMonitorRepository extends JpaRepository<ActivityMonitor, UUID> {

    List<ActivityMonitor> findActivityMonitorByPatient(Patient patient);
    Optional<ActivityMonitor> findById(UUID id);
}

