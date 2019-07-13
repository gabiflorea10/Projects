package ro.utcluj.notification;

import ro.utcluj.model.Mess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class ClientSocketConnection {


    private final NotificationService notificationService;
    private BufferedReader in;
    private PrintWriter out;


    ClientSocketConnection(Socket clientSocket, NotificationService notificationService) {

        this.notificationService = notificationService;
        createReaderAndWriter(clientSocket);
        new Thread(this::listenAndRespond).start();
    }

    private void createReaderAndWriter(Socket clientSocket) {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception exception) {
            System.out.println("Client disconnected");
        }
    }


    private void listenAndRespond() {
        try {
            while (true) {
                String received = in.readLine();

                if (received.contains("Login")){
                    String []receivedStrings = received.split(" ");
                    ConcurrentHashMap<String, ClientSocketConnection> connectionConcurrentHashMap = notificationService.connectionConcurrentHashMap;
                    Set<String> setView = connectionConcurrentHashMap.keySet();

                    if (setView.contains(receivedStrings[1])){
                        out.println("User already logged in!");
                        notificationService.removeClient(this);
                    }
                    else{
                        out.println("Logged in successfully");
                        notificationService.connectionConcurrentHashMap.put(receivedStrings[1],this);
                        if(!notificationService.connectedClients.contains(this)){
                            notificationService.connectedClients.add(this);
                        }
                    }
                }

                if(received.contains("MSGADM")){
                    String []receivedStrings = received.split("~");
                    notificationService.messageRepository.save(new Mess(receivedStrings[1], receivedStrings[2]));
                    notificationService.sendMessageToAdmin();
                }

                if (received.contains("Logout")){
                    String []receivedStrings = received.split(" ");
                    out.println("Logged out successfully");
                    notificationService.connectionConcurrentHashMap.remove(receivedStrings[1]);
                    notificationService.removeClient(this);
                }
            }
        } catch (Exception exception) {
            System.out.println("Client disconnected");
            notificationService.removeClient(this);
        }
    }

    void sendMessage(String message) {
        out.println(message);
    }
}
