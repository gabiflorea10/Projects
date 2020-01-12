package com.ds.Assignment4.services;

import com.ds.Assignment4.models.dtos.ActivitiesChartDTO;
import com.ds.Assignment4.models.dtos.ActivityMonitorInfoDTO;
import com.ds.Assignment4.models.dtos.MonitorPillsInfoDTO;
import com.ds.Assignment4.models.entities.*;
import com.ds.Assignment4.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@WebService
@Service
public class DoctorService {

    private ActivityMonitorRepository activityMonitorRepository;
    private MonitorPillsRepository monitorPillsRepository;
    private PatientRepository patientRepository;
    private UserRepository userRepository;
    private RecommendationRepository recommendationRepository;

    public DoctorService() {
    }

    @Autowired
    public DoctorService(ActivityMonitorRepository activityMonitorRepository, MonitorPillsRepository monitorPillsRepository, PatientRepository patientRepository, UserRepository userRepository, RecommendationRepository recommendationRepository) {
        this.activityMonitorRepository = activityMonitorRepository;
        this.monitorPillsRepository = monitorPillsRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @WebMethod
    public List<ActivityMonitorInfoDTO> getActivityForPatient(String patientName){
        User user = userRepository.findUserByName(patientName);
        Patient patient = patientRepository.findPatientByUserPatient(user);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DecimalFormat df = new DecimalFormat("0.000");

        List<ActivityMonitor> activityMonitors = activityMonitorRepository.findActivityMonitorByPatient(patient);

        List<ActivityMonitorInfoDTO> activityMonitorInfoDTOS = new ArrayList<>();
        for(ActivityMonitor activityMonitor: activityMonitors){
            ActivityMonitorInfoDTO activityMonitorInfoDTO = new ActivityMonitorInfoDTO();
            activityMonitorInfoDTO.setActivity_id(activityMonitor.getActivity_monitor_id().toString());
            activityMonitorInfoDTO.setPatient_name(activityMonitor.getPatient().getUserPatient().getName());
            activityMonitorInfoDTO.setHours(Double.valueOf(df.format((activityMonitor.getEnd_time().getTime() - activityMonitor.getStart_time().getTime())/3600000.0)));
            activityMonitorInfoDTO.setCaregiver_username(activityMonitor.getPatient().getUserCaregiver().getUsername());
            activityMonitorInfoDTO.setActivity_label(activityMonitor.getActivity_label());
            activityMonitorInfoDTO.setIs_anomalous(activityMonitor.getIs_anomalous());
            activityMonitorInfoDTO.setActivity_date(activityMonitor.getStart_time().toLocalDateTime().format(formatter));

            activityMonitorInfoDTOS.add(activityMonitorInfoDTO);
        }

        return activityMonitorInfoDTOS;
    }

    @WebMethod
    public ActivitiesChartDTO getActivitiesChart(String patientName, String date){
        ActivitiesChartDTO activitiesChartDTO = new ActivitiesChartDTO(new HashMap<>());

        User user = userRepository.findUserByName(patientName);
        Patient patient = patientRepository.findPatientByUserPatient(user);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<ActivityMonitor> activityMonitors = activityMonitorRepository.findActivityMonitorByPatient(patient);

        for(ActivityMonitor activityMonitor: activityMonitors){
            if(activityMonitor.getStart_time().toLocalDateTime().format(formatter).equals(date)){
                activitiesChartDTO.activitiesMap.put(activityMonitor.getActivity_label(), activitiesChartDTO.activitiesMap.getOrDefault(activityMonitor.getActivity_label(), 0.0) +
                        ((activityMonitor.getEnd_time().getTime() - activityMonitor.getStart_time().getTime())/3600000.0));
            }

        }

        return activitiesChartDTO;
    }

    @WebMethod
    public String annotateActivity(String activityIdString){
        UUID activity_id = UUID.fromString(activityIdString);

        System.out.println(activity_id);

        ActivityMonitor activityMonitor = activityMonitorRepository.findById(activity_id).get();

        if(activityMonitor.getIs_anomalous().equals("true")){
            activityMonitor.setIs_anomalous("false");
        }
        else {
            activityMonitor.setIs_anomalous("true");
        }

        activityMonitorRepository.save(activityMonitor);

        return "Annotated successfully!";
    }

    @WebMethod
    public List<MonitorPillsInfoDTO> getMonitorForPatientAndDate(String patientName, String givenDate){
        List<MonitorPillsInfoDTO> monitorPillsInfoDTOS = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        User userPatient = userRepository.findUserByName(patientName);

        List<MonitorPills> monitorPills = monitorPillsRepository.findAllByUserPatient(userPatient);

        for(MonitorPills mp: monitorPills){

            if(mp.getIntakeDate().toLocalDate().format(formatter).equals(givenDate)){
                MonitorPillsInfoDTO monitorPillsInfoDTO = new MonitorPillsInfoDTO();
                monitorPillsInfoDTO.setPatientName(mp.getUserPatient().getName());
                monitorPillsInfoDTO.setMedicationName(mp.getMedication().getName());
                monitorPillsInfoDTO.setIntakeDate(mp.getIntakeDate().toLocalDate().format(formatter));
                monitorPillsInfoDTO.setDescription(mp.getDescription());

                monitorPillsInfoDTOS.add(monitorPillsInfoDTO);
            }

        }

        return monitorPillsInfoDTOS;

    }


    @WebMethod
    public String addRecommendation(String patientName, String description){

        User userPatient = userRepository.findUserByName(patientName);
        Patient patient = patientRepository.findPatientByUserPatient(userPatient);

        Recommendation recommendation = new Recommendation();
        recommendation.setUserPatient(userPatient);
        recommendation.setPatientName(patientName);
        recommendation.setDescription(description);
        recommendation.setCaregiverUsername(patient.getUserCaregiver().getUsername());

        recommendationRepository.save(recommendation);

        return "Inserted successfully!";
    }

}
