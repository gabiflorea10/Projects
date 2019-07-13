package ro.utcluj.api;

import ro.utcluj.dto.MessageInfoDTO;

import java.util.List;

public interface MessageServiceInterface {

    List<MessageInfoDTO> findAll();
    String deleteMessage(Integer idMessage);
}
