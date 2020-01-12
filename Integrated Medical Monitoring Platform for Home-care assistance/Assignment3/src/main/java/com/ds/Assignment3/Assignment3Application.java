package com.ds.Assignment3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.TimeZone;


@SpringBootApplication
public class Assignment3Application extends Application {

	private FXMLLoader fxmlLoader;

	@Override
	public void init() throws Exception{
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		ApplicationContext context = SpringApplication.run(Assignment3Application.class);
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(context::getBean);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader = new FXMLLoader(
				Assignment3Application.class.getResource("/FirstPage.fxml"));
		AnchorPane page = (AnchorPane) fxmlLoader.load();
		Scene scene = new Scene(page);

		primaryStage.setTitle("Pill Dispenser");
		primaryStage.setScene(scene);

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}


}
