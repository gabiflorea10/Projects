package ro.utcluj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ro.utcluj.api.*;
import ro.utcluj.service.*;

import java.sql.SQLException;


@SpringBootApplication
public class Server {

    public static void main(String[] args) throws SQLException {
        ApplicationContext context = SpringApplication.run(Server.class, args);
       // System.out.println(new BCryptPasswordEncoder().encode("pass"));
    }

    @Bean(name = "/userService")
    public HttpInvokerServiceExporter userServiceExporter(UserService userService){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(userService);
        exporter.setServiceInterface(UserServiceInterface.class);
        return exporter;
    }

    @Bean(name = "/drugService")
    public HttpInvokerServiceExporter drugServiceExporter(DrugService drugService){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(drugService);
        exporter.setServiceInterface(DrugServiceInterface.class);
        return exporter;
    }

    @Bean(name = "/orderService")
    public HttpInvokerServiceExporter orderServiceExporter(OrderService orderService){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(orderService);
        exporter.setServiceInterface(OrderServiceInterface.class);
        return exporter;
    }

    @Bean(name = "/loginService")
    public HttpInvokerServiceExporter loginServiceExporter(LoginService loginService){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(loginService);
        exporter.setServiceInterface(LoginServiceInterface.class);
        return exporter;
    }

    @Bean(name = "/messageService")
    public HttpInvokerServiceExporter messageServiceExporter(MessageService messageService){
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(messageService);
        exporter.setServiceInterface(MessageServiceInterface.class);
        return exporter;
    }
}
