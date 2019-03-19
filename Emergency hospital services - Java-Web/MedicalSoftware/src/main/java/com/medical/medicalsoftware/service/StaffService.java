package com.medical.medicalsoftware.service;

import com.medical.medicalsoftware.model.Staff;

import java.util.List;

public interface StaffService {
    void setLoggedStaff(Staff staff);
    Staff getLoggedStaff();
    Staff findByMedUsername(String medUsername);
    List<Staff> getStaffs();
    void addNewStaff(Staff staff);
}

