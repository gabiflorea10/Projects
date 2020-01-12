package com.ds.Assignment1.medicalApplication.models.mappers;

import com.ds.Assignment1.medicalApplication.models.dtos.MedicationInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.Medication;

import java.util.ArrayList;
import java.util.List;

public class MedicationInfoMapper {

    public static MedicationInfoDTO mapToDTO (Medication medication){
        MedicationInfoDTO medicationInfoDTO = new MedicationInfoDTO();

        medicationInfoDTO.setMedication_id(medication.getMedication_id());
        medicationInfoDTO.setName(medication.getName());
        medicationInfoDTO.setSide_effects(medication.getSide_effects());
        medicationInfoDTO.setDosage(medication.getDosage());

        return medicationInfoDTO;
    }

    public static List<MedicationInfoDTO> mapAllToDTO(List<Medication> medications){
        List<MedicationInfoDTO> medicationInfoDTOS = new ArrayList<>();

        for(Medication medication: medications){
            medicationInfoDTOS.add(mapToDTO(medication));
        }

        return medicationInfoDTOS;
    }

    public static Medication mapFromDTO (MedicationInfoDTO medicationInfoDTO){
        Medication medication = new Medication();

        medication.setMedication_id(medicationInfoDTO.getMedication_id());
        medication.setName(medicationInfoDTO.getName());
        medication.setSide_effects(medicationInfoDTO.getSide_effects());
        medication.setDosage(medicationInfoDTO.getDosage());

        return medication;
    }

    public static List<Medication> mapAllFromDTO(List<MedicationInfoDTO> medicationInfoDTOS){
        List<Medication> medications = new ArrayList<>();

        for(MedicationInfoDTO medicationInfoDTO: medicationInfoDTOS){
            medications.add(mapFromDTO(medicationInfoDTO));
        }

        return medications;
    }
}
