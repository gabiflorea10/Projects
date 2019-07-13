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
import org.springframework.stereotype.Service;
import ro.utcluj.api.LoginServiceInterface;
import ro.utcluj.api.UserServiceInterface;

import java.io.IOException;

@Service
public class FXMLRegisterController {

    @FXML
    public TextField nameField;

    @FXML
    public TextField phoneField;

    @FXML
    public TextField emailField;

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField moneyField;

    @FXML
    public Label invalidRegisterLabel;

    private UserServiceInterface userService;
    private ApplicationContext applicationContext;
    private LoginServiceInterface loginService;

    public FXMLRegisterController(TextField nameField, TextField phoneField, TextField emailField, TextField usernameField, PasswordField passwordField, TextField moneyField, Label invalidRegisterLabel, UserServiceInterface userService, ApplicationContext applicationContext) {
        this.nameField = nameField;
        this.phoneField = phoneField;
        this.emailField = emailField;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.moneyField = moneyField;
        this.invalidRegisterLabel = invalidRegisterLabel;
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    @Autowired
    public FXMLRegisterController(ApplicationContext applicationContext, LoginServiceInterface loginService) {
        this.applicationContext = applicationContext;
        this.userService = applicationContext.getBean(UserServiceInterface.class);
        this.loginService = applicationContext.getBean(LoginServiceInterface.class);
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
    public void backFromRegisterPressed(Event event){
        changePage(event, "/view/loginpage.fxml", "Pharmacy - Login");
    }


    @FXML
    public void registerButtonPressed(Event event) {

        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        Integer money = 0;
        boolean validData = true;

        if(name.equals("") || phone.equals("")||email.equals("")||username.equals("")||password.equals("")||moneyField.getText().equals(""))
            validData=false;
        try {
            money = Integer.parseInt(moneyField.getText());
        } catch (NumberFormatException e1) {
            validData = false;
        }


        if(validData) {
            String message = loginService.saveNewUser(name, phone, email, username, password, money);

            if (message.equals("")) {
                changePage(event, "/view/loginpage.fxml", "Pharmacy - Login");
            } else {
                invalidRegisterLabel.setText(message);
            }
        }
        else{
            invalidRegisterLabel.setText("Invalid data!");
        }
    }

}
