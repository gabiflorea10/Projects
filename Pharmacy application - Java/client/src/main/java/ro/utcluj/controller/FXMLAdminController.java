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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ro.utcluj.api.*;
import ro.utcluj.dto.DrugInfoDTO;
import ro.utcluj.dto.MessageInfoDTO;
import ro.utcluj.dto.OrderInfoDTO;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.notification.NotificationService;
import ro.utcluj.reportManager.ReportFactory;
import ro.utcluj.reportManager.ReportService;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@Service
public class FXMLAdminController {

    @Autowired
    private static UserInfoDTO currentUser;

    private UserServiceInterface userService;
    private DrugServiceInterface drugService;
    private OrderServiceInterface orderService;
    private LoginServiceInterface loginService;
    private MessageServiceInterface messageService;
    private ReportService reportService;

    private ApplicationContext applicationContext;

    private NotificationService notificationService;

    public FXMLAdminController(UserServiceInterface userService, DrugServiceInterface drugService, OrderServiceInterface orderService, ApplicationContext applicationContext, NotificationService notificationService, Label drugMessageLabel) {
        this.userService = userService;
        this.drugService = drugService;
        this.orderService = orderService;
        this.applicationContext = applicationContext;
        this.notificationService = notificationService;
        this.drugMessageLabel = drugMessageLabel;
    }

    @Autowired
    public FXMLAdminController(ApplicationContext applicationContext,NotificationService notificationService, LoginServiceInterface loginService) {
        this.applicationContext = applicationContext;
        this.userService = applicationContext.getBean(UserServiceInterface.class);
        this.drugService = applicationContext.getBean(DrugServiceInterface.class);
        this.orderService = applicationContext.getBean(OrderServiceInterface.class);
        this.loginService = applicationContext.getBean(LoginServiceInterface.class);
        this.messageService = applicationContext.getBean(MessageServiceInterface.class);
        this.reportService = new ReportService(applicationContext);
        this.notificationService = notificationService;
    }

    @FXML
    public TableView<UserInfoDTO> userViewTable;
    @FXML
    public TableColumn<UserInfoDTO, Integer> userIdCol;
    @FXML
    public TableColumn<UserInfoDTO, String> userNameCol;
    @FXML
    public TableColumn<UserInfoDTO, String>  userPhoneCol;
    @FXML
    public TableColumn<UserInfoDTO, String>  userEmailCol;
    @FXML
    public TableColumn<UserInfoDTO, String>  userUsernameCol;
    @FXML
    public TableColumn<UserInfoDTO, String>  userPasswordCol;
    @FXML
    public TableColumn<UserInfoDTO, Integer>  userMoneyCol;

    @FXML
    public TableView<MessageInfoDTO> messageViewTable;
    @FXML
    public TableColumn<MessageInfoDTO, Integer> messIdCol;
    @FXML
    public TableColumn<MessageInfoDTO, String> messNameCol;
    @FXML
    public TableColumn<MessageInfoDTO, String>  messMessCol;


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
    public TableView<OrderInfoDTO> orderViewTable;
    @FXML
    public TableColumn<OrderInfoDTO, Integer> orderIdCol;
    @FXML
    public TableColumn<OrderInfoDTO, String> orderUserCol;
    @FXML
    public TableColumn<OrderInfoDTO, String> orderDrugCol;
    @FXML
    public TableColumn<OrderInfoDTO, Date> orderDateCol;

    @FXML
    public TextField startDateField;
    @FXML
    public TextField endDateField;
    @FXML
    public Label reportLabel;

    @FXML
    public TextField userNameField;
    @FXML
    public TextField userPhoneField;
    @FXML
    public TextField userEmailField;
    @FXML
    public TextField userUsernameField;
    @FXML
    public PasswordField userPasswordField;
    @FXML
    public TextField userMoneyField;

    @FXML
    public Label userModifyLabel;


    @FXML
    public TextField drugNameField;
    @FXML
    public TextField drugProducerField;
    @FXML
    public TextField drugUsefulnessField;
    @FXML
    public TextField drugValidityField;
    @FXML
    public TextField drugPriceField;

    @FXML
    public Label drugMessageLabel;

    @FXML
    private ChoiceBox<String> reportTypeChoice;
    private ObservableList<String> reportChoices = FXCollections.observableArrayList("reportTxt", "reportPDF");

    private static Integer currentUserId;
    private static Integer currentDrugId;
    private static Integer currentMessId;


    private void changePage(javafx.event.Event event, String page, String title){
        Parent root;
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
        FXMLAdminController.currentUser = null;
        changePage(event, "/view/loginpage.fxml", "Pharmacy - Login");
    }

    void initializePage(UserInfoDTO user){
        currentUser = user;

        userIdCol.setCellValueFactory(new PropertyValueFactory<UserInfoDTO, Integer>("iduser"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<UserInfoDTO, String>("name"));
        userPhoneCol.setCellValueFactory(new PropertyValueFactory<UserInfoDTO, String>("phone"));
        userEmailCol.setCellValueFactory(new PropertyValueFactory<UserInfoDTO, String>("email"));
        userUsernameCol.setCellValueFactory(new PropertyValueFactory<UserInfoDTO, String>("username"));
        userPasswordCol.setCellValueFactory(new PropertyValueFactory<UserInfoDTO, String>("password"));
        userMoneyCol.setCellValueFactory(new PropertyValueFactory<UserInfoDTO, Integer>("money"));

        ObservableList<UserInfoDTO> userObservableList = FXCollections.observableArrayList();
        userObservableList.addAll(userService.findAllUserObservable());
        userViewTable.setItems(userObservableList);


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
        orderUserCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getUserInfoDTO().getName()));
        orderDrugCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getDrugInfoDTO().getName()));
        orderDateCol.setCellValueFactory(new PropertyValueFactory<OrderInfoDTO, Date>("orderdate"));

        ObservableList<OrderInfoDTO> orderObservableList = FXCollections.observableArrayList();
        orderObservableList.addAll(orderService.findAllOrders());

        orderViewTable.setItems(orderObservableList);

        messIdCol.setCellValueFactory(new PropertyValueFactory<MessageInfoDTO, Integer>("idmessage"));
        messNameCol.setCellValueFactory(new PropertyValueFactory<MessageInfoDTO, String>("clientname"));
        messMessCol.setCellValueFactory(new PropertyValueFactory<MessageInfoDTO, String>("messagetext"));

        ObservableList<MessageInfoDTO> messageInfoDTOObservableList = FXCollections.observableArrayList();
        messageInfoDTOObservableList.addAll(messageService.findAll());

        messageViewTable.setItems(messageInfoDTOObservableList);

        reportTypeChoice.setItems(reportChoices);

    }

    @FXML
    public void generateReportTxtButton(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        String pathToSave = selectedDirectory.getAbsolutePath();

        boolean validData = true;

        if(reportTypeChoice.getSelectionModel().isEmpty())
            validData = false;

        if(startDateField.getText().equals("")||endDateField.getText().equals(""))
            validData = false;

        try {
            Date.valueOf(startDateField.getText());
        } catch (Exception e1) {
            validData = false;
        }

        try {
            Date.valueOf(endDateField.getText());
        } catch (Exception e1) {
            validData = false;
        }

        if(validData) {
            String reportType = reportTypeChoice.getSelectionModel().getSelectedItem();
            Date startDate = Date.valueOf(startDateField.getText());
            Date endDate = Date.valueOf(endDateField.getText());

            reportService.createReport(startDate, endDate, reportType, pathToSave);

            reportLabel.setText("Generate successfully!");
        }
        else{
            reportLabel.setText("Invalid data!");
        }
    }

    @FXML
    public void userAddButton(){
        boolean validData = true;

        if(userNameField.getText().equals("") || userPhoneField.getText().equals("")||userEmailField.getText().equals("")||userUsernameField.getText().equals("")||userPasswordField.getText().equals("")||userMoneyField.getText().equals(""))
            validData=false;
        try {
            Integer.parseInt(userMoneyField.getText());
        } catch (NumberFormatException e1) {
            validData = false;
        }

        if(validData) {
            String mess = loginService.saveNewUser(userNameField.getText(), userPhoneField.getText(), userEmailField.getText(), userUsernameField.getText(), userPasswordField.getText(), Integer.parseInt(userMoneyField.getText()));
            if (mess.equals("")) {
                userModifyLabel.setText("Added successfully!");
                ObservableList<UserInfoDTO> userObservableList = FXCollections.observableArrayList();
                userObservableList.addAll(userService.findAllUserObservable());
                userViewTable.setItems(userObservableList);
            } else {
                userModifyLabel.setText(mess);
            }
        }
        else{
            userModifyLabel.setText("Invalid data!");
        }
    }

    @FXML
    public void userEditButton(Event event){
        boolean validData = true;

        if(userNameField.getText().equals("") || userPhoneField.getText().equals("")||userEmailField.getText().equals("")||userUsernameField.getText().equals("")||userPasswordField.getText().equals("")||userMoneyField.getText().equals(""))
            validData=false;

        try {
            Integer.parseInt(userMoneyField.getText());
        } catch (NumberFormatException e1) {
            validData = false;
        }

        if(validData) {
            String mess = userService.updateUser(currentUserId, userNameField.getText(), userPhoneField.getText(), userEmailField.getText(), userUsernameField.getText(), userPasswordField.getText(), Integer.parseInt(userMoneyField.getText()));

            if (mess.equals("")) {
                userModifyLabel.setText("Updated successfully!");

                ObservableList<UserInfoDTO> userObservableList = FXCollections.observableArrayList();
                userObservableList.addAll(userService.findAllUserObservable());
                userViewTable.setItems(userObservableList);

            } else {
                userModifyLabel.setText(mess);
            }
        }
        else {
            userModifyLabel.setText("Invalid data!");
        }
    }

    @FXML
    public void userDeleteButton(Event event){
        userService.deleteUser(currentUserId);
        userModifyLabel.setText("Deleted successfully!");
        userNameField.setText("");
        userPhoneField.setText("");
        userEmailField.setText("");
        userUsernameField.setText("");
        userPasswordField.setText("");
        userMoneyField.setText("");
        ObservableList<UserInfoDTO> userObservableList = FXCollections.observableArrayList();
        userObservableList.addAll(userService.findAllUserObservable());
        userViewTable.setItems(userObservableList);
    }

    @FXML
    public void userClearButton(){
        userNameField.setText("");
        userPhoneField.setText("");
        userEmailField.setText("");
        userUsernameField.setText("");
        userPasswordField.setText("");
        userMoneyField.setText("");
    }

    @FXML
    public void handleClickTable(){
         UserInfoDTO user  = userViewTable.getSelectionModel().getSelectedItem();

            userNameField.setText(user.getName());
            userPhoneField.setText(user.getPhone());
            userEmailField.setText(user.getEmail());
            userUsernameField.setText(user.getUsername());
            userPasswordField.setText(user.getPassword());
            userMoneyField.setText(Integer.toString(user.getMoney()));
            currentUserId = user.getIduser();

    }

    @FXML
    public void handleClickMessageTable(){
        MessageInfoDTO messageInfoDTO = messageViewTable.getSelectionModel().getSelectedItem();
        currentMessId = messageInfoDTO.getIdmessage();
    }

    @FXML
    public void handleClickTable2(){
        DrugInfoDTO drug = drugViewTable.getSelectionModel().getSelectedItem();

        drugNameField.setText(drug.getName());
        drugProducerField.setText(drug.getProducer());
        drugUsefulnessField.setText(drug.getUsefulfor());
        drugValidityField.setText(String.valueOf(drug.getTermofvalidity()));
        drugPriceField.setText(String.valueOf(drug.getPrice()));
        currentDrugId = drug.getIddrug();
    }

    @FXML
    public void drugAddButton(){
        boolean validData = true;
        if(drugNameField.getText().equals("") || drugProducerField.getText().equals("") || drugUsefulnessField.getText().equals("") || drugValidityField.getText().equals("") || drugPriceField.getText().equals(""))
            validData = false;
        try {
            Integer.parseInt(drugPriceField.getText());
        } catch (NumberFormatException e1) {
            validData = false;
        }
        try {
            Date.valueOf(drugValidityField.getText());
        } catch (Exception e1) {
            validData = false;
        }

        if(validData) {
            drugService.addDrug(drugNameField.getText(), drugProducerField.getText(), drugUsefulnessField.getText(), Date.valueOf(drugValidityField.getText()), Integer.parseInt(drugPriceField.getText()));
            drugMessageLabel.setText("Added successfully!");
            ObservableList<DrugInfoDTO> drugObservableList = FXCollections.observableArrayList();
            drugObservableList.addAll(drugService.findAllObservable());
            drugViewTable.setItems(drugObservableList);
        }
        else{
            drugMessageLabel.setText("Invalid data!");
        }

    }

    @FXML
    public void drugEditButton(){
        boolean validData = true;
        if(drugNameField.getText().equals("") || drugProducerField.getText().equals("") || drugUsefulnessField.getText().equals("") || drugValidityField.getText().equals("") || drugPriceField.getText().equals(""))
            validData = false;
        try {
            Integer.parseInt(drugPriceField.getText());
        } catch (NumberFormatException e1) {
            validData = false;
        }
        try {
            Date.valueOf(drugValidityField.getText());
        } catch (Exception e1) {
            validData = false;
        }
        if(validData) {
            drugService.updateDrug(currentDrugId, drugNameField.getText(), drugProducerField.getText(), drugUsefulnessField.getText(), Date.valueOf(drugValidityField.getText()), Integer.parseInt(drugPriceField.getText()));
            drugMessageLabel.setText("Updated successfully!");
            ObservableList<DrugInfoDTO> drugObservableList = FXCollections.observableArrayList();
            drugObservableList.addAll(drugService.findAllObservable());
            drugViewTable.setItems(drugObservableList);
        }
        else{
            drugMessageLabel.setText("Invalid data!");
        }

    }

    public void drugDeleteButtonTest(){
        drugService.deleteDrug(currentDrugId);
        drugMessageLabel.setText("Deleted successfully!");
    }

    @FXML
    public void drugDeleteButton(){
        drugService.deleteDrug(currentDrugId);
        drugMessageLabel.setText("Deleted successfully!");
        drugNameField.setText("");
        drugProducerField.setText("");
        drugUsefulnessField.setText("");
        drugValidityField.setText("");
        drugPriceField.setText("");
        ObservableList<DrugInfoDTO> drugObservableList = FXCollections.observableArrayList();
        drugObservableList.addAll(drugService.findAllObservable());
        drugViewTable.setItems(drugObservableList);
    }

    @FXML
    public void drugClearButton(){
        drugNameField.setText("");
        drugProducerField.setText("");
        drugUsefulnessField.setText("");
        drugValidityField.setText("");
        drugPriceField.setText("");
    }

    @FXML
    public void refreshUsers(){
        ObservableList<UserInfoDTO> userObservableList = FXCollections.observableArrayList();
        userObservableList.addAll(userService.findAllUserObservable());
        userViewTable.setItems(userObservableList);
    }

    @FXML
    public void refreshDrugs(){
        ObservableList<DrugInfoDTO> drugObservableList = FXCollections.observableArrayList();
        drugObservableList.addAll(drugService.findAllObservable());
        drugViewTable.setItems(drugObservableList);
    }

    @FXML
    public void refreshOrders(){
        ObservableList<OrderInfoDTO> orderObservableList = FXCollections.observableArrayList();
        orderObservableList.addAll(orderService.findAllOrders());
        orderViewTable.setItems(orderObservableList);
    }

    @FXML
    public void deleteMessageButton(){
        messageService.deleteMessage(currentMessId);
        ObservableList<MessageInfoDTO> messageInfoDTOObservableList = FXCollections.observableArrayList();
        messageInfoDTOObservableList.addAll(messageService.findAll());
        messageViewTable.setItems(messageInfoDTOObservableList);

    }

    @FXML
    public void refreshMessages(){
        ObservableList<MessageInfoDTO> messageInfoDTOObservableList = FXCollections.observableArrayList();
        messageInfoDTOObservableList.addAll(messageService.findAll());
        messageViewTable.setItems(messageInfoDTOObservableList);
    }
}
