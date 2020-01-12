package com.ds.Assignment3.pillDispenser.controller;

import com.ds.Assignment3.pillDispenser.entity.MedicationPlanView;
import com.ds.Assignment3.pillDispenser.service.MedPlanView;
import com.ds.Assignment3.pillDispenser.service.MedPlansList;
import com.ds.Assignment3.pillDispenser.service.PillDispenserService;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class PillDispenserController {

    private static final int port = 8889;
    private String patientNameResponse="";
    private String medicationResponse="";
    private String takenCheck="";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime fetchingDataTime = LocalDateTime.parse("2019-11-18 00:00:00", formatter);
    private LocalDateTime simulatedTime = LocalDateTime.parse("2011-11-27 23:00:00", formatter);
    private List<MedicationPlanView> meds = new ArrayList<>();
    private ObservableList<MedicationPlanView> medObservableList = FXCollections.observableArrayList();
    private List<String> badPatientsList = new ArrayList<>();

    @FXML
    private TableView<MedicationPlanView> tableView;

    @FXML
    private TableColumn<MedicationPlanView, String> patientColumn;

    @FXML
    private TableColumn<MedicationPlanView, String> medicationColumn;

    @FXML
    private TableColumn<MedicationPlanView, Integer> dosageColumn;

    @FXML
    private TableColumn<MedicationPlanView, Date> startColumn;

    @FXML
    private TableColumn<MedicationPlanView, Date> endColumn;

    @FXML
    private TableColumn<MedicationPlanView, String> intakeCol;

    @FXML
    private TableColumn<MedicationPlanView, String> doctorColumn;

    @FXML
    private TableColumn<MedicationPlanView, String> takenCol;

    @FXML
    private Label timeLabel;

    @FXML
    private Label timeSimLabel;


    @FXML
    public void handleClick() {
        patientNameResponse = tableView.getSelectionModel().getSelectedItem().getPatient_name();
        medicationResponse = tableView.getSelectionModel().getSelectedItem().getMedication_name();
        takenCheck = tableView.getSelectionModel().getSelectedItem().getTaken();
    }

    @FXML
    public void takeMed(){
        if(takenCheck.equals("Not taken")){

            // Identify medication in table

            Boolean takenOnTime = Boolean.TRUE;

            for (MedicationPlanView med: medObservableList){
                if(med.getPatient_name().equals(patientNameResponse) && med.getMedication_name().equals(medicationResponse)){
                    int start_hour = 0;
                    int end_hour = 0;
                    List<String> tokens = new ArrayList<>();
                    StringTokenizer tokenizer = new StringTokenizer(med.getIntake_interval(), "-");
                    while(tokenizer.hasMoreTokens()){
                        tokens.add(tokenizer.nextToken());
                    }

                    start_hour = Integer.parseInt(tokens.get(0));
                    end_hour = Integer.parseInt(tokens.get(1));

                    if(start_hour<=simulatedTime.getHour() && end_hour>=simulatedTime.getHour()){
                        takenOnTime = Boolean.TRUE;
                    }
                    else{
                        takenOnTime = Boolean.FALSE;
                    }

                    medObservableList.remove(med);
                    break;
                }
            }


            // Update table

            tableView.setItems(medObservableList);


            // Send report about medication plan

            if(takenOnTime == Boolean.TRUE) {
                PillDispenserService client = new PillDispenserService("localhost", port);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String response = "Today, " + simulatedTime.format(formatter) + ", patient with name: " + patientNameResponse + " took medication: " + medicationResponse + " at prescribed time.";
                List<String> plansReport = new ArrayList<>();
                plansReport.add(response);
                client.procActions("Entity", plansReport);
                try {
                    client.shutdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                badPatientsList.add(("Patient with name: "+ patientNameResponse + " take the medication: " + medicationResponse + " outside of prescribed time."));
            }
        }


        //Clear table selection

        tableView.getSelectionModel().clearSelection();
    }


    @FXML
    public void initialize() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            // Increase simulated time

            simulatedTime = simulatedTime.plus(1, ChronoUnit.HOURS);


            // Show real-time and simulated-time clocks

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            timeLabel.setText(LocalDateTime.now().format(formatter));
            timeSimLabel.setText(simulatedTime.format(formatter));


        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();


        Timeline clock1 = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            // Fetch data at proper time (00:00:00)

            if(simulatedTime.getHour() == fetchingDataTime.getHour() && simulatedTime.getMinute() == fetchingDataTime.getMinute() && simulatedTime.getSecond() == fetchingDataTime.getSecond()) {

                // Add all patients that didn't take the medication to badPatientsList

                for(MedicationPlanView med: medObservableList){
                    badPatientsList.add(("Patient with name: "+ med.getPatient_name() + " didn't take the medication: " + med.getMedication_name() + " at all."));
                }


                // Create a client

                PillDispenserService client = new PillDispenserService("localhost", port);


                // Send report at the end of the day

                client.procActions("List", badPatientsList);


                // Fetch medication plans for the next day

                MedPlansList response = client.fetchMedicationPlans(simulatedTime);

                try {
                    client.shutdown();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                patientColumn.setCellValueFactory(new PropertyValueFactory<MedicationPlanView, String>("patient_name"));
                medicationColumn.setCellValueFactory(new PropertyValueFactory<MedicationPlanView, String>("medication_name"));
                dosageColumn.setCellValueFactory(new PropertyValueFactory<MedicationPlanView, Integer>("dosage"));
                startColumn.setCellValueFactory(new PropertyValueFactory<MedicationPlanView, Date>("start_time"));
                endColumn.setCellValueFactory(new PropertyValueFactory<MedicationPlanView, Date>("end_time"));
                intakeCol.setCellValueFactory(new PropertyValueFactory<MedicationPlanView, String>("intake_interval"));
                doctorColumn.setCellValueFactory(new PropertyValueFactory<MedicationPlanView, String>("doctor_name"));
                takenCol.setCellValueFactory(new PropertyValueFactory<MedicationPlanView, String>("taken"));

                medObservableList.clear();
                badPatientsList.clear();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                badPatientsList.add( "Today, "+ simulatedTime.format(formatter) + ":");
                medObservableList.addAll(processResponse(response));
                tableView.setItems(medObservableList);
            }

        }), new KeyFrame(Duration.seconds(1)));
        clock1.setCycleCount(Animation.INDEFINITE);
        clock1.play();


    }

    private List<MedicationPlanView> processResponse(MedPlansList medPlansList){

        // Process the server response

        meds.clear();

        for(MedPlanView medPlanView: medPlansList.getMedPlanViewList()){
            MedicationPlanView medicationPlanView = new MedicationPlanView();
            medicationPlanView.setPatient_name(medPlanView.getPatientName());
            medicationPlanView.setMedication_name(medPlanView.getMedicationName());
            medicationPlanView.setSide_effects(medPlanView.getSideEffects());
            medicationPlanView.setDosage(medPlanView.getDosage());
            medicationPlanView.setIntake_interval(medPlanView.getIntakeInterval());
            medicationPlanView.setStart_time(Date.valueOf(medPlanView.getStartTime()));
            medicationPlanView.setEnd_time(Date.valueOf(medPlanView.getEndTime()));
            medicationPlanView.setDoctor_name(medPlanView.getDoctorName());
            medicationPlanView.setTaken("Not taken");

            meds.add(medicationPlanView);
        }


        // Sort medication plans list by patient

        Collections.sort(meds);

        return meds;
    }
}
