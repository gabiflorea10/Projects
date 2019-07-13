package ro.utcluj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmessage;
    private String clientname;
    private String messagetext;

    public Mess(String clientname, String messagetext) {
        this.clientname = clientname;
        this.messagetext = messagetext;
    }

    public Mess() {
    }

    public Integer getIdmessage() {
        return idmessage;
    }

    public void setIdmessage(Integer idmessage) {
        this.idmessage = idmessage;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getMessagetext() {
        return messagetext;
    }

    public void setMessagetext(String messagetext) {
        this.messagetext = messagetext;
    }

    @Override
    public String toString() {
        return "Mess{" +
                "idmessage=" + idmessage +
                ", clientname='" + clientname + '\'' +
                ", messagetext='" + messagetext + '\'' +
                '}';
    }
}
