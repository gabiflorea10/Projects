package com.ds.Assignment1.medicalApplication.models.mappers;

import com.ds.Assignment1.medicalApplication.models.dtos.MedicationPerPlanInfoDTO;
import com.ds.Assignment1.medicalApplication.models.entities.Medication;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPerPlan;
import com.ds.Assignment1.medicalApplication.models.entities.MedicationPlan;

import java.util.ArrayList;
import java.util.List;

public class MedicationPerPlanInfoMapper {

    public static MedicationPerPlanInfoDTO mapToDTO (MedicationPerPlan medicationPerPlan){
        MedicationPerPlanInfoDTO medicationPerPlanInfoDTO =  new MedicationPerPlanInfoDTO();

        medicationPerPlanInfoDTO.setMedication_per_plan_id(medicationPerPlan.getMedication_per_plan_id());
        medicationPerPlanInfoDTO.setMedication_name(medicationPerPlan.getMedication().getName());
        medicationPerPlanInfoDTO.setMedication_plan_id(medicationPerPlan.getMedicationPlan().getMedication_plan_id());
        medicationPerPlanInfoDTO.setIntake_interval(medicationPerPlan.getIntake_interval());
        medicationPerPlanInfoDTO.setStart_time(medicationPerPlan.getStart_time());
        medicationPerPlanInfoDTO.setEnd_time(medicationPerPlan.getEnd_time());

        return medicationPerPlanInfoDTO;
    }

    public static List<MedicationPerPlanInfoDTO> mapAllToDTO(List<MedicationPerPlan> medicationPerPlans){
        List<MedicationPerPlanInfoDTO> medicationPerPlanInfoDTOS = new ArrayList<>();

        for(MedicationPerPlan medicationPerPlan: medicationPerPlans){
            medicationPerPlanInfoDTOS.add(mapToDTO(medicationPerPlan));
        }

        return medicationPerPlanInfoDTOS;
    }

    public static MedicationPerPlan mapFromDTO (MedicationPerPlanInfoDTO medicationPerPlanInfoDTO, Medication medication, MedicationPlan medicationPlan){
        MedicationPerPlan medicationPerPlan =  new MedicationPerPlan();

        medicationPerPlan.setMedication_per_plan_id(medicationPerPlanInfoDTO.getMedication_per_plan_id());
        medicationPerPlan.setMedication(medication);
        medicationPerPlan.setMedicationPlan(medicationPlan);
        medicationPerPlan.setIntake_interval(medicationPerPlanInfoDTO.getIntake_interval());
        medicationPerPlan.setStart_time(medicationPerPlanInfoDTO.getStart_time());
        medicationPerPlan.setEnd_time(medicationPerPlanInfoDTO.getEnd_time());

        return medicationPerPlan;
    }

}
