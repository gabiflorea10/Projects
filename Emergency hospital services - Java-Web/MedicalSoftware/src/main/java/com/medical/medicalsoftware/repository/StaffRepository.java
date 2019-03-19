package com.medical.medicalsoftware.repository;

import com.medical.medicalsoftware.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface StaffRepository extends MongoRepository<Staff, String> {
    Staff findByMedUsername(String medUsername);
}
