package dataAccess;

/**
 * Clasa ConnectionFactory este o clasa cu rol de a realiza conexiunea dintre aplicatia java si baza de date considerata
 * Aceasta clasa foloseste proprietatile de acces la baza de date pentru a o accesa
 */

import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {

    public Connection makeConnection(){
        try {
            Connection connect;
            Properties properties = new Properties();
            properties.setProperty("user","root");
            properties.setProperty("password","efvefv");
            properties.setProperty("useSSL","false");
            properties.setProperty("autoReconnect","true");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", properties);
            return connect;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

}