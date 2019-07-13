
package ro.utcluj.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ro.utcluj.api.DrugServiceInterface;
import ro.utcluj.api.OrderServiceInterface;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.dto.DrugInfoDTO;
import ro.utcluj.dto.OrderInfoDTO;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.notification.NotificationService;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;

@Service
public class FXMLClientController{

    @Autowired
    private static UserInfoDTO currentUser;

    private static String prescriptionName = "";
    private static String prescriptionDrug = "";
    private static String prescriptionType = "";

    private UserServiceInterface userService;
    private DrugServiceInterface drugService;
    private OrderServiceInterface orderService;

    private ApplicationContext applicationContext;

    private NotificationService notificationService;



    public FXMLClientController(UserServiceInterface userService, DrugServiceInterface drugService, OrderServiceInterface orderService, ApplicationContext applicationContext, NotificationService notificationService, TextField addMoneyField, Label addMoneyLabel, TextField idDrugBuy, Label buyMessageLabel) {
        this.userService = userService;
        this.drugService = drugService;
        this.orderService = orderService;
        this.applicationContext = applicationContext;
        this.notificationService = notificationService;
        this.addMoneyField = addMoneyField;
        this.addMoneyLabel = addMoneyLabel;
        this.idDrugBuy = idDrugBuy;
        this.buyMessageLabel = buyMessageLabel;
    }

    @Autowired
    public FXMLClientController(ApplicationContext applicationContext, NotificationService notificationService) {
        this.applicationContext = applicationContext;
        this.userService = applicationContext.getBean(UserServiceInterface.class);
        this.drugService = applicationContext.getBean(DrugServiceInterface.class);
        this.orderService = applicationContext.getBean(OrderServiceInterface.class);
        this.notificationService = notificationService;
    }

    @FXML
    public TableView<DrugInfoDTO> drugViewTable;
    @FXML
    public TableColumn<DrugInfoDTO, Integer> drugIdCol;
    @FXML
    public TableColumn<DrugInfoDTO, String> drugNameCol;
    @FXML
    public TableColumn<DrugInfoDTO, String>  drugProducerCol;
    @FXML
    public TableColumn<DrugInfoDTO, String>  drugUsefulforCol;
    @FXML
    public TableColumn<DrugInfoDTO, Date>  drugTermofvalidityCol;
    @FXML
    public TableColumn<DrugInfoDTO, Integer>  drugPriceCol;

    @FXML
    public TableView<DrugInfoDTO> drugViewTable1;
    @FXML
    public TableColumn<DrugInfoDTO, Integer> drugIdCol1;
    @FXML
    public TableColumn<DrugInfoDTO, String> drugNameCol1;
    @FXML
    public TableColumn<DrugInfoDTO, String>  drugProducerCol1;
    @FXML
    public TableColumn<DrugInfoDTO, String>  drugUsefulforCol1;
    @FXML
    public TableColumn<DrugInfoDTO, Date>  drugTermofvalidityCol1;
    @FXML
    public TableColumn<DrugInfoDTO, Integer>  drugPriceCol1;

    @FXML
    public TextField addMoneyField;

    @FXML
    public Label addMoneyLabel;

    @FXML
    public Label moneyAmountLabel;

    @FXML
    public TextField idDrugBuy;
    @FXML
    public TextField searchByName;
    @FXML
    public TextField searchBySymptom;

    @FXML
    public Label buyMessageLabel;

    @FXML
    public TableView<OrderInfoDTO> orderViewTable;
    @FXML
    public TableColumn<OrderInfoDTO, Integer> orderIdCol;
    @FXML
    public TableColumn<OrderInfoDTO, String> orderDrugCol;
    @FXML
    public TableColumn<OrderInfoDTO, Date> orderDateCol;


    @FXML
    public TextField editNameField;
    @FXML
    public TextField editPhoneField;
    @FXML
    public TextField editEmailField;
    @FXML
    public TextField editUsernameField;
    @FXML
    public PasswordField editPasswordField;

    @FXML
    public Label editLabel;

    @FXML
    public TextArea messageForAdmin;

    @FXML
    public Label labelMessageForAdmin;

    public static UserInfoDTO getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(UserInfoDTO currentUser) {
        FXMLClientController.currentUser = currentUser;
    }

    @FXML
    public void editConfirmButton(Event event){
        String nameT = editNameField.getText();
        String phoneT = editPhoneField.getText();
        String emailT = editEmailField.getText();
        String usernameT = editUsernameField.getText();
        String passwordT = editPasswordField.getText();

        boolean validData = true;
        if(nameT.equals("") || phoneT.equals("")||emailT.equals("")||usernameT.equals("")||passwordT.equals(""))
            validData=false;

        if(validData) {
            userService.updateUserData(nameT, phoneT, emailT, passwordT);
            editLabel.setText("Edited successfully!");
        }
        else{
            editLabel.setText("Invalid data!");
        }

    }

    @FXML
    public void buyButtonPressed(){
        Integer idOfDrug=0;
        boolean validData = true;

        try {
            idOfDrug = Integer.parseInt(idDrugBuy.getText());
        } catch (NumberFormatException e1) {
            validData = false;
        }

        if(validData) {
            String message;
            if(currentUser.getName().equals(prescriptionName))
                message = orderService.buyDrug(idOfDrug, prescriptionName, prescriptionDrug, prescriptionType);
            else
                message = orderService.buyDrug(idOfDrug, "", "", "");
            ObservableList<OrderInfoDTO> orderInfoDTOObservableList = FXCollections.observableArrayList();
            orderInfoDTOObservableList.addAll(orderService.findAllByUser());
            orderViewTable.setItems(orderInfoDTOObservableList);
            buyMessageLabel.setText(message);
        }
        else{
            buyMessageLabel.setText("Invalid data!");
        }

        currentUser = userService.findByUser();
        moneyAmountLabel.setText(currentUser.getMoney().toString());

        ObservableList<OrderInfoDTO> orderObservableList = FXCollections.observableArrayList();
        orderObservableList.addAll(orderService.findAllByUser());
        orderViewTable.setItems(orderObservableList);
    }

    @FXML
    public void addMoneyButton(){
        Integer money = 0;

        boolean validData = true;

        try {
            money = Integer.parseInt(addMoneyField.getText());
        } catch (NumberFormatException e1) {
            validData = false;
        }
    if(validData) {
        userService.addMoney(money);
        addMoneyLabel.setText("Added successfully!");
    }
    else{
        addMoneyLabel.setText("Invalid data!");
    }

        currentUser = userService.findByUser();
        moneyAmountLabel.setText(currentUser.getMoney().toString());

    }

    @FXML
    public void searchByNamePressed(){
        String nameOfDrug = searchByName.getText();
        boolean validData = true;
        if(nameOfDrug.equals(""))
            validData = false;
        if(validData) {
            drugIdCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Integer>("iddrug"));
            drugNameCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("name"));
            drugProducerCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("producer"));
            drugUsefulforCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("usefulfor"));
            drugTermofvalidityCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Date>("termofvalidity"));
            drugPriceCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Integer>("price"));

            ObservableList<DrugInfoDTO> drugObservableList = FXCollections.observableArrayList();
            drugObservableList.addAll(drugService.findAllByName(nameOfDrug));

            drugViewTable1.setItems(drugObservableList);
        }

    }

    @FXML
    public void searchBySymptomPressed(){
        String symptom = searchBySymptom.getText();
        boolean validData = true;
        if(symptom.equals(""))
            validData = false;
        if(validData) {
            drugIdCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Integer>("iddrug"));
            drugNameCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("name"));
            drugProducerCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("producer"));
            drugUsefulforCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("usefulfor"));
            drugTermofvalidityCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Date>("termofvalidity"));
            drugPriceCol1.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Integer>("price"));

            ObservableList<DrugInfoDTO> drugObservableList = FXCollections.observableArrayList();
            drugObservableList.addAll(drugService.findAllBySymptom(symptom));

            drugViewTable1.setItems(drugObservableList);
        }
    }

    private void changePage(javafx.event.Event event, String page, String title){
        Parent root = null;
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            fxmlLoader.setLocation(getClass().getResource(page));
            root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            (((Node)event.getSource())).getScene().getWindow().hide();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logoutPressed(Event event) throws IOException {
        notificationService.sendMessageToServer("Logout " + currentUser.getIduser());
        FXMLClientController.currentUser = null;
        changePage(event, "/view/loginpage.fxml", "Pharmacy - Login");
    }

    void initializePage(UserInfoDTO user) {
        currentUser = user;

        drugIdCol.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Integer>("iddrug"));
        drugNameCol.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("name"));
        drugProducerCol.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("producer"));
        drugUsefulforCol.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, String>("usefulfor"));
        drugTermofvalidityCol.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Date>("termofvalidity"));
        drugPriceCol.setCellValueFactory(new PropertyValueFactory<DrugInfoDTO, Integer>("price"));

        ObservableList<DrugInfoDTO> drugObservableList = FXCollections.observableArrayList();
        drugObservableList.addAll(drugService.findAllObservable());

        drugViewTable.setItems(drugObservableList);


        orderIdCol.setCellValueFactory(new PropertyValueFactory<OrderInfoDTO, Integer>("id"));
        orderDrugCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDrugInfoDTO().getName()));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<OrderInfoDTO, Date>("orderdate"));

        ObservableList<OrderInfoDTO> orderObservableList = FXCollections.observableArrayList();
        orderObservableList.addAll(orderService.findAllByUser());

        orderViewTable.setItems(orderObservableList);

        editNameField.setText(currentUser.getName());
        editPhoneField.setText(currentUser.getPhone());
        editEmailField.setText(currentUser.getEmail());
        editUsernameField.setText(currentUser.getUsername());
        editPasswordField.setText(currentUser.getPassword());

        moneyAmountLabel.setText(currentUser.getMoney().toString());

    }

    @FXML
    public void uploadPrescriptionPressed() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            File file = new File(selectedFile.getAbsolutePath());
            Scanner sc = new Scanner(file);

            prescriptionName = sc.nextLine();
            prescriptionDrug = sc.nextLine();
            prescriptionType = sc.nextLine();

        }

    }

    @FXML
    public void refreshDrugsButton(){
        ObservableList<DrugInfoDTO> drugObservableList = FXCollections.observableArrayList();
        drugObservableList.addAll(drugService.findAllObservable());
        drugViewTable.setItems(drugObservableList);
    }

    @FXML
    public void sendMessageToAdmin() throws IOException {
        String message = messageForAdmin.getText();
        notificationService.sendMessageToServer("MSGADM~"+ currentUser.getName() +"~" + message);
        System.out.println();
        labelMessageForAdmin.setText("Message successfully sent!");
    }

}
