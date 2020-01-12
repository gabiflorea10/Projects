package com.ds.Assignment1.medicalApplication.services;

import com.ds.Assignment1.medicalApplication.errorHandlers.ResourceNotFoundException;
import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPlanInfoDTO;
import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPlanView;
import com.ds.Assignment1.medicalApplication.models.dtos.PatientInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPerPlan;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPlan;
import com.ds.Assignment1.medicalApplication.models.entities.User;
import com.ds.Assignment1.medicalApplication.models.mappers.MedicationPlanInfoMapper;
import com.ds.Assignment1.medicalApplication.models.mappers.MedicationPlanViewMapper;
import com.ds.Assignment1.medicalApplication.repositories.MedicationPerPlanRepository;
import com.ds.Assignment1.medicalApplication.repositories.MedicationPlanRepository;
import com.ds.Assignment1.medicalApplication.repositories.MedicationRepository;
import com.ds.Assignment1.medicalApplication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MedicationPlanService {
    private MedicationPlanRepository medicationPlanRepository;
    private UserService userService;
    private UserRepository userRepository;
    private MedicationRepository medicationRepository;
    private MedicationPerPlanRepository medicationPerPlanRepository;
    private PatientService patientService;
    private MedicationPerPlanService medicationPerPlanService;
    private MedicationService medicationService;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository, UserService userService, PatientService patientService, MedicationPerPlanService medicationPerPlanService, MedicationService medicationService, UserRepository userRepository, MedicationRepository medicationRepository, MedicationPerPlanRepository medicationPerPlanRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.userService = userService;
        this.medicationPerPlanService = medicationPerPlanService;
        this.medicationService = medicationService;
        this.patientService = patientService;
        this.userRepository = userRepository;
        this.medicationRepository = medicationRepository;
        this.medicationPerPlanRepository = medicationPerPlanRepository;
    }

    public MedicationPlanInfoDTO findMedicationPlanById (UUID medication_plan_id){
        Optional<MedicationPlan> medicationPlan = medicationPlanRepository.findById(medication_plan_id);

        if(!medicationPlan.isPresent()){
            throw new ResourceNotFoundException("MedicationPlan", "medication_plan_id", medication_plan_id);
        }

        return MedicationPlanInfoMapper.mapToDTO(medicationPlan.get());
    }

    public List<MedicationPlanView> findAllMedicationPlansByUsername(String username){
        List<MedicationPlanView> medicationPlanViews = new ArrayList<>();
        User user = userService.findUserByUsername(username);
        List<MedicationPlan> medicationPlans = medicationPlanRepository.findAllByUserPatient(user);

        for(MedicationPlan medicationPlan: medicationPlans){
            List<MedicationPerPlan> medicationPerPlans = medicationPerPlanService.findAllByMedicatiionPlan(medicationPlan);

            for(MedicationPerPlan medicationPerPlan: medicationPerPlans){
                Medication medication = medicationService.findById(medicationPerPlan.getMedication().getMedication_id());
                MedicationPlanView medicationPlanView = MedicationPlanViewMapper.mapToDTO(medication, medicationPlan, medicationPerPlan);
                medicationPlanViews.add(medicationPlanView);
            }

        }

        return medicationPlanViews;
    }

    public List<MedicationPlanView> findAllMedicationPlanViews(){
        List<MedicationPlanView> medicationPlanViews = new ArrayList<>();
        List<MedicationPlan> medicationPlans = medicationPlanRepository.findAll();

        for(MedicationPlan medicationPlan: medicationPlans){
            List<MedicationPerPlan> medicationPerPlans = medicationPerPlanService.findAllByMedicatiionPlan(medicationPlan);

            for(MedicationPerPlan medicationPerPlan: medicationPerPlans){
                Medication medication = medicationService.findById(medicationPerPlan.getMedication().getMedication_id());
                MedicationPlanView medicationPlanView = MedicationPlanViewMapper.mapToDTO(medication, medicationPlan, medicationPerPlan);
                medicationPlanViews.add(medicationPlanView);
            }

        }

        return medicationPlanViews;
    }

    public List<MedicationPlanView> findAllMedicationPlansByCaregiverUsername(String username){
        List<MedicationPlanView> medicationPlanViews = new ArrayList<>();

        List<PatientInfoDTO> patientInfoDTOS = patientService.findAllPatientsByCaregiverUsername(username);

        for(PatientInfoDTO patientInfoDTO: patientInfoDTOS){
            List<MedicationPlanView> medicationPlanViewPatientsPartial = this.findAllMedicationPlansByUsername(patientInfoDTO.getUsername());
            medicationPlanViews.addAll(medicationPlanViewPatientsPartial);
        }

        return medicationPlanViews;
    }

    public List<MedicationPlanInfoDTO> findAllMedicationPlans(){
        List<MedicationPlan> medicationPlans = medicationPlanRepository.findAll();
        return MedicationPlanInfoMapper.mapAllToDTO(medicationPlans);
    }

    public String insertMedicationPlan(MedicationPlanInfoDTO medicationPlanInfoDTO){
        User patient = userService.findUserByUsername(medicationPlanInfoDTO.getPatient_username());
        User doctor = userService.findUserByUsername(medicationPlanInfoDTO.getDoctor_username());
        medicationPlanRepository.save(MedicationPlanInfoMapper.mapFromDTO(medicationPlanInfoDTO, patient, doctor));
        return "Medication plan inserted!";
    }

    public String insertMedicationPlanView(MedicationPlanView medicationPlanView){
        User patient = userRepository.findUserByName(medicationPlanView.getPatient_name());
        User doctor = userRepository.findUserByName(medicationPlanView.getDoctor_name());
        Medication medication = medicationRepository.findByName(medicationPlanView.getMedication_name());
        MedicationPlan medicationPlan = new MedicationPlan(patient, doctor);
        medicationPlanRepository.save(medicationPlan);
        medicationPlan = medicationPlanRepository.findByUserPatientAndUserDoctor(patient, doctor);
        MedicationPerPlan medicationPerPlan = new MedicationPerPlan(medication, medicationPlan, medicationPlanView.getIntake_interval(), medicationPlanView.getStart_time(), medicationPlanView.getEnd_time());
        medicationPerPlanRepository.save(medicationPerPlan);
        return "Medication plan inserted!";
    }

    public String deleteMedicationPlan(MedicationPlanInfoDTO medicationPlanInfoDTO){
        medicationPlanRepository.deleteById(medicationPlanInfoDTO.getMedication_plan_id());
        return "Medication plan deleted!";
    }

}
