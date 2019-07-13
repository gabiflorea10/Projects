package ro.utcluj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.security.remoting.httpinvoker.AuthenticationSimpleHttpInvokerRequestExecutor;
import ro.utcluj.api.*;

import static javafx.application.Application.launch;

@SpringBootApplication
public class Client extends Application {

    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception{
        ApplicationContext context = SpringApplication.run(Client.class);
        fxmlLoader = new FXMLLoader();
        fxmlLoader.setControllerFactory(context::getBean);
    }

    @Override
    public void start(Stage stage) throws Exception {
        fxmlLoader.setLocation(getClass().getResource("/view/loginpage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Pharmacy - Login");
        stage.setScene(scene);
        stage.show();
    }

    @Bean
    public AuthenticationSimpleHttpInvokerRequestExecutor httpInvokerRequestExecutor() { // spring-security-remoting dependency (check latest version here https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-dependency-versions.html)
        return new AuthenticationSimpleHttpInvokerRequestExecutor();
    }

    @Bean
    public HttpInvokerProxyFactoryBean userServiceProxy(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor){
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setHttpInvokerRequestExecutor(requestExecutor);
        proxy.setServiceInterface(UserServiceInterface.class);
        proxy.setServiceUrl("http://localhost:1234/userService");
        return proxy;
    }

    @Bean
    public HttpInvokerProxyFactoryBean drugServiceProxy(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor){
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setHttpInvokerRequestExecutor(requestExecutor);
        proxy.setServiceInterface(DrugServiceInterface.class);
        proxy.setServiceUrl("http://localhost:1234/drugService");
        return proxy;
    }

    @Bean
    public HttpInvokerProxyFactoryBean orderServiceProxy(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor){
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setHttpInvokerRequestExecutor(requestExecutor);
        proxy.setServiceInterface(OrderServiceInterface.class);
        proxy.setServiceUrl("http://localhost:1234/orderService");
        return proxy;
    }

    @Bean
    public HttpInvokerProxyFactoryBean messageServiceProxy(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor){
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setHttpInvokerRequestExecutor(requestExecutor);
        proxy.setServiceInterface(MessageServiceInterface.class);
        proxy.setServiceUrl("http://localhost:1234/messageService");
        return proxy;
    }

    @Bean
    public HttpInvokerProxyFactoryBean loginServiceProxy(AuthenticationSimpleHttpInvokerRequestExecutor requestExecutor){
        HttpInvokerProxyFactoryBean proxy = new HttpInvokerProxyFactoryBean();
        proxy.setHttpInvokerRequestExecutor(requestExecutor);
        proxy.setServiceInterface(LoginServiceInterface.class);
        proxy.setServiceUrl("http://localhost:1234/loginService");
        return proxy;
    }
}
