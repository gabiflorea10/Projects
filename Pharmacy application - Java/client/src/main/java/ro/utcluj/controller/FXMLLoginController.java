package ro.utcluj.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.utcluj.api.LoginServiceInterface;
import ro.utcluj.api.UserServiceInterface;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.notification.NotificationService;

import java.io.IOException;

@Service
public class FXMLLoginController {

    @FXML
    public TextField usernameLabel;

    @FXML
    public PasswordField passwordLabel;

    @FXML
    public Label wrongCredentialsLabel;

    @Autowired
    private static UserInfoDTO currentUser;

    private UserServiceInterface userService;
    private LoginServiceInterface loginService;
    private ApplicationContext applicationContext;

    private NotificationService notificationService;

    public FXMLLoginController(TextField usernameLabel, PasswordField passwordLabel, Label wrongCredentialsLabel, UserServiceInterface userService, ApplicationContext applicationContext, NotificationService notificationService, LoginServiceInterface loginService) {
        this.usernameLabel = usernameLabel;
        this.passwordLabel = passwordLabel;
        this.wrongCredentialsLabel = wrongCredentialsLabel;
        this.userService = userService;
        this.applicationContext = applicationContext;
        this.notificationService = notificationService;
    }

    @Autowired
    public FXMLLoginController(ApplicationContext applicationContext, NotificationService notificationService, LoginServiceInterface loginService) {
        this.applicationContext = applicationContext;
        this.userService = applicationContext.getBean(UserServiceInterface.class);
        this.loginService = applicationContext.getBean(LoginServiceInterface.class);
        this.notificationService = notificationService;
    }


    private void changePage(Event event, String pageName, String pageTitle, Boolean isAdmin){
        Parent root = null;
        try {
            FXMLLoader fxmlLoader;
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            fxmlLoader.setLocation(getClass().getResource(pageName));

            root = fxmlLoader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            if(pageName.contains("/view/clientpage.fxml") || pageName.contains("/view/adminpage.fxml")) {
                if (isAdmin) {
                    FXMLAdminController adminController = fxmlLoader.getController();
                    adminController.initializePage(currentUser);
                } else {
                    FXMLClientController clientController = fxmlLoader.getController();
                    clientController.initializePage(currentUser);
                }
            }

            (((Node)event.getSource())).getScene().getWindow().hide();
            stage.setTitle(pageTitle);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void loginButtonPressed(javafx.event.Event event) throws IOException {
        String userName = usernameLabel.getText();
        String password = passwordLabel.getText();

        Authentication token = new UsernamePasswordAuthenticationToken(userName,password);
        SecurityContextHolder.getContext().setAuthentication(token);

        UserInfoDTO userForLogin = loginService.loginUser();

        boolean validData = true;

        if(userName.equals("") || password.equals(""))
            validData=false;

    if(validData) {
        if (userForLogin != null) {
            FXMLLoginController.currentUser = userForLogin;
            currentUser.setPassword(password);

            if (currentUser.getTypeofuser().equals("client")) {
                changePage(event, "/view/clientpage.fxml", "Pharmacy - Client side", false);
            } else {
                changePage(event, "/view/adminpage.fxml", "Pharmacy - Admin side", true);
            }

            notificationService.connectToNotificationServer(userForLogin.getName());
            notificationService.sendMessageToServer("Login "  + userForLogin.getIduser());


        } else {
            wrongCredentialsLabel.setText("Incorrect credentials!");
        }
    }
    else{
        wrongCredentialsLabel.setText("Invalid data!");
    }

    }

    @FXML
    public void registerButtonPressed(Event event) {
        changePage(event, "/view/registerpage.fxml", "Pharmacy - Register", false);
    }
}
