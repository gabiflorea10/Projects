package com.medical.medicalsoftware.controller;

import com.medical.medicalsoftware.model.Patient;
import com.medical.medicalsoftware.model.Staff;
import com.medical.medicalsoftware.service.PatientService;
import com.medical.medicalsoftware.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class StartPageController {

    private  PatientService patientService;
    private StaffService staffService;
    private Patient editingPatient;

    public StartPageController(PatientService patientService, StaffService staffService) {
        this.patientService = patientService;
        this.staffService = staffService;
        this.editingPatient = null;
    }

    @RequestMapping(value = {"","/startPage"}, method = RequestMethod.GET)
    public String startPageGet(Model model){
        List<Patient> patients = patientService.getPatients();
        model.addAttribute("patients", patients);
        return "startPage";
    }

    @RequestMapping(value = {"","/staff"}, method = RequestMethod.GET)
    public String staffPageGet(Model model){
        List<Staff> staffs = staffService.getStaffs();
        model.addAttribute("staffs", staffs);
        return "staff";
    }

    @RequestMapping(value = {"","/about"}, method = RequestMethod.GET)
    public String aboutPageGet(Model model){
        return "about";
    }

    @RequestMapping(value = {"","/add"}, method = RequestMethod.GET)
    public String addPageGet(Model model){
        return "add";
    }

    @RequestMapping(value = {"","/edit"}, method = RequestMethod.GET)
    public String editPageGet(Model model){
        return "edit";
    }

    @RequestMapping(value = {"","/delete"}, method = RequestMethod.GET)
    public String deletePageGet(Model model){
        return "delete";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String registerPostAdd(@ModelAttribute(name = "addPatients") Patient patient, Model model){
        String patientName = patient.getPatientName();
        String processingState = patient.getProcessingState();
        String physiologicalCondition = patient.getPhysiologicalCondition();
        String emergencyType = patient.getEmergencyType();
        String location = patient.getLocation();
        String patientHistory = patient.getPatientHistory();
        String treatmentsPerformed = patient.getTreatmentsPerformed();

        Patient registeredPatients = patientService.findByPatientName(patientName);

        if(registeredPatients != null){
            model.addAttribute("invalidAdd", true);
            return "add";
        }

        patientService.addNewPatient(new Patient(patientName,processingState,physiologicalCondition,emergencyType,location,patientHistory,treatmentsPerformed));

        return "redirect:/startPage";

    }

    @RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
    public String registerPostDelete(@ModelAttribute(name = "deletePatients") Patient patient, Model model){
        String patientName = patient.getPatientName();

        Patient registeredPatients = patientService.findByPatientName(patientName);

        if(registeredPatients != null){
            patientService.deletePatient(patientName);
            return "redirect:/startPage";
        }

        model.addAttribute("invalidDelete", true);
        return "delete";

    }

    @RequestMapping(value = {"/edit"}, method = RequestMethod.POST)
    public String registerPostEdit(@ModelAttribute(name = "editPatients") Patient patient, Model model){
        String patientName = patient.getPatientName();

        editingPatient = patientService.findByPatientName(patientName);

        if(editingPatient != null){
            System.out.println(patientName);
            return "redirect:/editPatient";
        }

        model.addAttribute("invalidEdit", true);
        return "edit";

    }

    @RequestMapping(value = {"","/editPatient"}, method = RequestMethod.GET)
    public String editPatientGet(Model model){
        model.addAttribute("patient", editingPatient);
        return "editPatient";
    }

    @RequestMapping(value = {"/editPatient"}, method = RequestMethod.POST)
    public String registerPostEditPatient(Patient patient){
        String patientName = patient.getPatientName();
        String processingState = patient.getProcessingState();
        String physiologicalCondition = patient.getPhysiologicalCondition();
        String emergencyType = patient.getEmergencyType();
        String location = patient.getLocation();
        String patientHistory = patient.getPatientHistory();
        String treatmentsPerformed = patient.getTreatmentsPerformed();

        editingPatient.setProcessingState(processingState);
        editingPatient.setPhysiologicalCondition(physiologicalCondition);
        editingPatient.setEmergencyType(emergencyType);
        editingPatient.setLocation(location);
        editingPatient.setPatientHistory(patientHistory);
        editingPatient.setTreatmentsPerformed(treatmentsPerformed);

        patientService.deletePatient(patientName);
        patientService.addNewPatient(editingPatient);

        return "redirect:/startPage";

    }
}
