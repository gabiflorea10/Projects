package com.ds.Assignment1.medicalApplication.services;

import com.ds.Assignment1.medicalApplication.errorHandlers.ResourceNotFoundException;
import com.ds.Assignment1.medicalApplication.models.dtos.MedicationInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import com.ds.Assignment1.medicalApplication.models.mappers.MedicationInfoMapper;
import com.ds.Assignment1.medicalApplication.repositories.MedicationPerPlanRepository;
import com.ds.Assignment1.medicalApplication.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MedicationService {

    private MedicationRepository medicationRepository;
    private MedicationPerPlanRepository medicationPerPlanRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository, MedicationPerPlanRepository medicationPerPlanRepository) {
        this.medicationRepository = medicationRepository;
        this.medicationPerPlanRepository = medicationPerPlanRepository;
    }

    public MedicationInfoDTO findMedicationById (UUID medication_id){
        Optional<Medication> medication = medicationRepository.findById(medication_id);

        if(!medication.isPresent()){
            throw new ResourceNotFoundException("Medication", "medication_id", medication_id);

        }

        return MedicationInfoMapper.mapToDTO(medication.get());
    }

    public Medication findById (UUID medication_id){
        Optional<Medication> medication = medicationRepository.findById(medication_id);

        return medication.get();
    }

    public Medication findByName (String name){
        Medication medication = medicationRepository.findByName(name);

        return medication;
    }

    public List<MedicationInfoDTO> findAllMedications(){
        List<Medication> medications = medicationRepository.findAll();
        return MedicationInfoMapper.mapAllToDTO(medications);
    }

    public String insertMedication(MedicationInfoDTO medicationInfoDTO){
        medicationRepository.save(MedicationInfoMapper.mapFromDTO(medicationInfoDTO));
        return "Medication inserted!";
    }

    public String deleteMedication(String name){
        Medication medication = medicationRepository.findByName(name);
        medicationPerPlanRepository.deleteByMedication(medication);
        medicationRepository.deleteByName(name);
        return "Medication deleted!";
    }

    public String updateMedication(MedicationInfoDTO medicationInfoDTO){
        Medication medication = medicationRepository.findByName(medicationInfoDTO.getName());

        if(medication != null) {
            medicationInfoDTO.setMedication_id(medication.getMedication_id());
            medicationRepository.save(MedicationInfoMapper.mapFromDTO(medicationInfoDTO));
        }
        return "Medication updated!";
    }
}
