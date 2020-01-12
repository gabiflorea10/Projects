package com.ds.Assignment1.pillDispenser.service;

import com.ds.Assignment1.pillDispenser.*;

import com.ds.Assignment1.pillDispenser.entity.MonitorPills;
import io.grpc.stub.StreamObserver;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


@Service
public class GrpcService extends MedPlanGrpc.MedPlanImplBase {

    //  for using service instead of rest call
//    @Autowired
//    private MedicationPlanService medicationPlanService;

    @Autowired
    private MonitorPillsService monitorPillsService;

    @Override
    public void fetchMedPlans(DateMessage dateMessage, StreamObserver<MedPlansList> medPlansListStreamObserver) {

        URL url = null;
        HttpURLConnection con = null;
        BufferedReader in = null;
        String response = "";

        // prepare received simulated date

        Date simTime = Date.valueOf(dateMessage.getCurrentDate());
        LocalDate simulatedTime = simTime.toLocalDate();

        System.out.println(simulatedTime);
        // rest api get

        try{
            url = new URL("http://localhost:8888/medication_plan/allMedicationPlanViews");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            response = in.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }


        // prepare the response

        response = response.substring(1, response.length() - 1);

        StringTokenizer stringTokenizer = new StringTokenizer(response, "}");

        JSONObject medicationPlan = null;

        List<MedPlanView> medPlanViews = new ArrayList<>();

        while (stringTokenizer.hasMoreTokens()) {
            String element = stringTokenizer.nextToken();
            if (element.charAt(0) == ',')
                element = element.substring(1);
            element = element.concat("}");

            try {
                medicationPlan = (JSONObject) new JSONParser(JSONParser.MODE_PERMISSIVE).parse(element);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            String patient_name = medicationPlan.getAsString("patient_name");
            String medication_name = medicationPlan.getAsString("medication_name");
            String side_effects = medicationPlan.getAsString("side_effects");
            Integer dosage = medicationPlan.getAsNumber("dosage").intValue();
            String intake_interval = medicationPlan.getAsString("intake_interval");
            String start_date = medicationPlan.getAsString("start_time");
            String end_date = medicationPlan.getAsString("end_time");
            String doctor_name = medicationPlan.getAsString("doctor_name");

            Date start = Date.valueOf(start_date);
            Date end = Date.valueOf(end_date);

            LocalDate startDate = start.toLocalDate();
            LocalDate endDate = end.toLocalDate();

            if(startDate.compareTo(simulatedTime)<=0 && endDate.compareTo(simulatedTime)>=0)
            {
                MedPlanView medPlanView = MedPlanView.newBuilder()
                        .setPatientName(patient_name)
                        .setMedicationName(medication_name)
                        .setSideEffects(side_effects)
                        .setDosage(dosage)
                        .setIntakeInterval(intake_interval)
                        .setStartTime(start_date)
                        .setEndTime(end_date)
                        .setDoctorName(doctor_name)
                        .build();

                medPlanViews.add(medPlanView);
            }
        }

        // send medicationplans

        MedPlansList medPlansList = MedPlansList.newBuilder().addAllMedPlanView(medPlanViews).build();
        medPlansListStreamObserver.onNext(medPlansList);
        medPlansListStreamObserver.onCompleted();
    }

    @Override
    public void processActions(MedicationPlansReport medicationPlansReport, StreamObserver<EmptyMessage> responseObserver) {

        List<DayReport> reportList = medicationPlansReport.getReportsList();


        // the place from where to store monitor activity to db

        MonitorPills monitorPills = new MonitorPills();


        EmptyMessage reply = EmptyMessage.newBuilder().build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();


        if(medicationPlansReport.getType().equals("Entity")){
            System.out.println("Instant notification:");
            for(DayReport dayReport: reportList){
                String dayReportContent = dayReport.getContent();
                System.out.println(dayReportContent);
                String patientName = dayReportContent.substring(dayReportContent.indexOf("name:")+6, dayReportContent.indexOf(" took"));
                String medicationName = dayReportContent.substring(dayReportContent.indexOf("medication:")+12, dayReportContent.indexOf(" at"));
                String currentDate = dayReportContent.substring(dayReportContent.indexOf("Today, ")+7, dayReportContent.indexOf(", patient with"));

                monitorPillsService.insertMonitorPill(currentDate, patientName, medicationName, "Taken");
            }

        }
        else if(medicationPlansReport.getType().equals("List")){
            System.out.println("Day report notification:");
            int index = 0;
            String currentDate = "";
            for(DayReport dayReport: reportList){
                String dayReportContent = dayReport.getContent();
                System.out.println(dayReportContent);
                if (index == 0){
                    currentDate = dayReportContent.substring(dayReportContent.indexOf("Today, ")+7, dayReportContent.indexOf(":"));
                }
                if (index > 0){
                    if(dayReportContent.contains("outside of")){
                        String patientName = dayReportContent.substring(dayReportContent.indexOf("name:")+6, dayReportContent.indexOf(" take"));
                        String medicationName = dayReportContent.substring(dayReportContent.indexOf("medication:")+12, dayReportContent.indexOf(" outside"));
                        monitorPillsService.insertMonitorPill(currentDate, patientName, medicationName, "Taken outside the prescribed time");
                    }
                    else {
                        String patientName = dayReportContent.substring(dayReportContent.indexOf("name:")+6, dayReportContent.indexOf(" didn't"));
                        String medicationName = dayReportContent.substring(dayReportContent.indexOf("medication:")+12, dayReportContent.indexOf(" at"));
                        monitorPillsService.insertMonitorPill(currentDate, patientName, medicationName, "Not taken");

                    }
                }
                index++;
            }

        }

    }

}

