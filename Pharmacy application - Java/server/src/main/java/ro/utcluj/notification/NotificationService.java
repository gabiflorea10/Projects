package ro.utcluj.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import ro.utcluj.repositories.MessageRepository;

import javax.annotation.PostConstruct;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@EnableScheduling
public class NotificationService {
    private static final int PORT_NUMBER = 8043;

    public List<ClientSocketConnection> connectedClients = new CopyOnWriteArrayList<>();
    ConcurrentHashMap<String,ClientSocketConnection> connectionConcurrentHashMap = new ConcurrentHashMap<>();

    @Autowired
    public MessageRepository messageRepository;

    public NotificationService() {
        System.out.println();
    }

    @PostConstruct
    public void startServer() {
        Thread thread = new Thread(this::startNotificationServer);
        thread.start();
    }

    private void startNotificationServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientSocketConnection clientConnection = new ClientSocketConnection(clientSocket, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToAllClients(String message) {

        ClientSocketConnection clientSocketConnectionAdmin = connectionConcurrentHashMap.get("1");

        for (ClientSocketConnection connection : connectedClients) {
            if (!connection.equals(clientSocketConnectionAdmin))
            connection.sendMessage(message);
        }
    }

    public void sendMessageToAdmin(){

        ClientSocketConnection clientSocketConnectionAdmin = connectionConcurrentHashMap.get("1");

        clientSocketConnectionAdmin.sendMessage("SNDALERT");

    }

    void removeClient(ClientSocketConnection clientConnection) {
        connectedClients.remove(clientConnection);
    }

}
