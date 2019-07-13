package ro.utcluj.notification;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Runnable listening for incoming messages from the server
 */
public class ServerSocketListener implements Runnable {

    private Socket serverSocket;


    public ServerSocketListener(Socket serverSocket) {
        this.serverSocket = serverSocket;

    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            while (true) {
                String message = in.readLine();
                if (message.contains("User already logged in!")){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("User already exist!");
                            alert.setContentText(message);
                            alert.showAndWait();
                        }
                    });
                    TimeUnit.SECONDS.sleep(2);
                    System.exit(0);
                }

                if (message.contains("SNDALERT")){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("New message!");
                            alert.setContentText("You have a new message!");
                            alert.showAndWait();
                        }
                    });
                }

                if (message.contains("added")){
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("New drug notification");
                            alert.setHeaderText("New drug notification");
                            alert.setContentText(message);
                            alert.showAndWait();
                        }
                    });

                }

            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("The server is no longer available");
        }
    }

}
