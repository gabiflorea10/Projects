package com.medical.medicalsoftware.service;

import com.medical.medicalsoftware.model.Staff;
import com.medical.medicalsoftware.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImplementation implements StaffService{

    private StaffRepository staffRepository;
    private Staff loggedStaff;

    public StaffServiceImplementation(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff findByMedUsername (String medUsername){
        return staffRepository.findByMedUsername(medUsername);
    }

    @Override
    public List<Staff> getStaffs(){
        List<Staff> staffs = new ArrayList<>();
        staffRepository.findAll().iterator().forEachRemaining(staffs::add);
        return staffs;
    }

    @Override
    public void setLoggedStaff(Staff staff) {
        this.loggedStaff = staff;
    }

    @Override
    public Staff getLoggedStaff() {
        return this.loggedStaff;
    }

    @Override
    public void addNewStaff(Staff staff) {
        staffRepository.save(staff);
    }
}
