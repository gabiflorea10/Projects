package com.ds.Assignment1.pillDispenser.repository;

import com.ds.Assignment1.pillDispenser.entity.MonitorPills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MonitorPillsRepository extends JpaRepository<MonitorPills, UUID> {
}
