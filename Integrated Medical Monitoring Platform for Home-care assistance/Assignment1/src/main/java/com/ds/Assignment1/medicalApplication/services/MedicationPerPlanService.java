package com.ds.Assignment1.medicalApplication.services;

import com.ds.Assignment1.medicalApplication.errorHandlers.ResourceNotFoundException;
import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPerPlanInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPerPlan;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPlan;
import com.ds.Assignment1.medicalApplication.models.mappers.MedicationPerPlanInfoMapper;
import com.ds.Assignment1.medicalApplication.repositories.MedicationPerPlanRepository;
import com.ds.Assignment1.medicalApplication.repositories.MedicationPlanRepository;
import com.ds.Assignment1.medicalApplication.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MedicationPerPlanService {
    private MedicationPerPlanRepository medicationPerPlanRepository;
    private MedicationRepository medicationRepository;
    private MedicationPlanRepository medicationPlanRepository;

    @Autowired
    public MedicationPerPlanService(MedicationPerPlanRepository medicationPerPlanRepository, MedicationRepository medicationRepository, MedicationPlanRepository medicationPlanRepository) {
        this.medicationPerPlanRepository = medicationPerPlanRepository;
        this.medicationRepository = medicationRepository;
        this.medicationPlanRepository = medicationPlanRepository;
    }

    public MedicationPerPlanInfoDTO findMedicationPerPlanById (UUID medication_per_plan_id){
        Optional<MedicationPerPlan> medicationPerPlan = medicationPerPlanRepository.findById(medication_per_plan_id);

        if(!medicationPerPlan.isPresent()){
            throw new ResourceNotFoundException("MedicationPerPlan", "medication_per_plan_id", medication_per_plan_id);
        }

        return MedicationPerPlanInfoMapper.mapToDTO(medicationPerPlan.get());
    }

    public List<MedicationPerPlanInfoDTO> findAllMedicationPerPlans(){
        List<MedicationPerPlan> medicationPerPlans = medicationPerPlanRepository.findAll();
        return MedicationPerPlanInfoMapper.mapAllToDTO(medicationPerPlans);
    }

    public List<MedicationPerPlan> findAllByMedicatiionPlan(MedicationPlan medicationPlan){
        List<MedicationPerPlan> medicationPerPlans = medicationPerPlanRepository.findAllByMedicationPlan(medicationPlan);
        return medicationPerPlans;
    }



    public String insertMedicationPerPlan(MedicationPerPlanInfoDTO medicationPerPlanInfoDTO){
        Medication medication = medicationRepository.findByName(medicationPerPlanInfoDTO.getMedication_name());
        MedicationPlan medicationPlan = medicationPlanRepository.findById(medicationPerPlanInfoDTO.getMedication_plan_id()).get();
        medicationPerPlanRepository.save(MedicationPerPlanInfoMapper.mapFromDTO(medicationPerPlanInfoDTO, medication, medicationPlan));
        return "Medication per plan inserted!";
    }

    public String deleteMedicationPerPlan(MedicationPerPlanInfoDTO medicationPerPlanInfoDTO){
        medicationPerPlanRepository.deleteById(medicationPerPlanInfoDTO.getMedication_per_plan_id());
        return "Medication per plan deleted!";
    }

}
