package ro.utcluj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.utcluj.dto.MessageInfoDTO;
import ro.utcluj.mapper.MessageInfoMapper;
import ro.utcluj.model.Mess;
import ro.utcluj.repositories.MessageRepository;
import ro.utcluj.service.MessageService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    @Mock private MessageRepository messageRepository;
    @Mock private MessageInfoMapper messageInfoMapper;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private MessageService messageService;
    private Mess mess;

    @Before
    public void setup(){
        messageService = new MessageService(messageRepository, messageInfoMapper);
        mess = new Mess("Andrei", "Informations about paracetamol");
    }

    @Test
    public void findAllTest(){
        when(messageRepository.findAll()).thenReturn(new ArrayList<>());
        when(messageInfoMapper.mapAll(anyList())).thenReturn(new ArrayList());

        List<MessageInfoDTO> returnedList = messageService.findAll();

        Assert.assertEquals(new ArrayList<>(),returnedList);

        verify(messageRepository,times(1)).findAll();
        verify(messageInfoMapper,times(1)).mapAll(anyList());

    }


    @Test
    public void deleteMessageTest(){
        String returnedString = messageService.deleteMessage(2);
        Assert.assertEquals(returnedString, "Deleted");
        verify(messageRepository,times(1)).deleteById(2);
    }

}
