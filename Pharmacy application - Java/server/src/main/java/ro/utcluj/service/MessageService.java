package ro.utcluj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcluj.api.MessageServiceInterface;
import ro.utcluj.dto.MessageInfoDTO;
import ro.utcluj.mapper.MessageInfoMapper;
import ro.utcluj.model.Mess;
import ro.utcluj.repositories.MessageRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class MessageService implements MessageServiceInterface {

    private MessageRepository messageRepository;
    private MessageInfoMapper messageInfoMapper;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageInfoMapper messageInfoMapper) {
        this.messageRepository = messageRepository;
        this.messageInfoMapper = messageInfoMapper;
    }

    public List<MessageInfoDTO> findAll(){
        List<Mess> lista = messageRepository.findAll();
        Collections.reverse(lista);
        return messageInfoMapper.mapAll(lista);
    }

    public String deleteMessage(Integer idMessage){
        messageRepository.deleteById(idMessage);
        return "Deleted";
    }

}
