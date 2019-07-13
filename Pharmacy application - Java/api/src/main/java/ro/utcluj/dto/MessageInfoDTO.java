package ro.utcluj.dto;

import java.io.Serializable;

public class MessageInfoDTO implements Serializable {

    private Integer idmessage;
    private String clientname;
    private String messagetext;

    public MessageInfoDTO() {
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
        return "MessageInfoDTO{" +
                "idmessage=" + idmessage +
                ", clientname='" + clientname + '\'' +
                ", messagetext='" + messagetext + '\'' +
                '}';
    }
}
