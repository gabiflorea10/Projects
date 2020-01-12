package com.ds.Assignment1.medicalApplication.models.mappers;

import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPlanInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPlan;
import com.ds.Assignment1.medicalApplication.models.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MedicationPlanInfoMapper {

    public static MedicationPlanInfoDTO mapToDTO (MedicationPlan medicationPlan){
        MedicationPlanInfoDTO medicationPlanInfoDTO = new MedicationPlanInfoDTO();

        medicationPlanInfoDTO.setMedication_plan_id(medicationPlan.getMedication_plan_id());
        medicationPlanInfoDTO.setPatient_username(medicationPlan.getUserPatient().getUsername());
        medicationPlanInfoDTO.setDoctor_username(medicationPlan.getUserDoctor().getUsername());

        return medicationPlanInfoDTO;
    }

    public static List<MedicationPlanInfoDTO> mapAllToDTO(List<MedicationPlan> medicationPlans){
        List<MedicationPlanInfoDTO> medicationPlanInfoDTOS = new ArrayList<>();

        for(MedicationPlan medicationPlan: medicationPlans){
            medicationPlanInfoDTOS.add(mapToDTO(medicationPlan));
        }

        return medicationPlanInfoDTOS;
    }

    public static MedicationPlan mapFromDTO (MedicationPlanInfoDTO medicationPlanInfoDTO, User patient, User doctor){
        MedicationPlan medicationPlan = new MedicationPlan();

        medicationPlan.setMedication_plan_id(medicationPlanInfoDTO.getMedication_plan_id());
        medicationPlan.setUserPatient(patient);
        medicationPlan.setUserDoctor(doctor);

        return medicationPlan;
    }


}
