package ro.utcluj.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.dto.MessageInfoDTO;
import ro.utcluj.model.Mess;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageInfoMapper {


    public MessageInfoDTO map(Mess mess){
        MessageInfoDTO messageInfoDTO = new MessageInfoDTO();

        messageInfoDTO.setIdmessage(mess.getIdmessage());
        messageInfoDTO.setClientname(mess.getClientname());
        messageInfoDTO.setMessagetext(mess.getMessagetext());

        return messageInfoDTO;
    }

    public List<MessageInfoDTO> mapAll(List<Mess> messes){
        List<MessageInfoDTO> messageInfoDTOS = new ArrayList<>();
        for(Mess mess : messes){
            messageInfoDTOS.add(map(mess));
        }
        return messageInfoDTOS;
    }
}
