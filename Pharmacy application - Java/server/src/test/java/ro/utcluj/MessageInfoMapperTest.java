package ro.utcluj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.utcluj.dto.MessageInfoDTO;
import ro.utcluj.mapper.MessageInfoMapper;
import ro.utcluj.model.Mess;

import java.util.ArrayList;
import java.util.List;

public class MessageInfoMapperTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private Mess mess;
    private MessageInfoMapper messageInfoMapper;

    @Before
    public void setup(){
        mess = new Mess("Andrei", "Informations about paracetamol");
        messageInfoMapper = new MessageInfoMapper();
    }

    @Test
    public void mapTest(){

        MessageInfoDTO messageInfoDTO = messageInfoMapper.map(mess);

        Assert.assertEquals(mess.getClientname(), messageInfoDTO.getClientname());

    }

    @Test
    public void mapAllTest(){

        List<MessageInfoDTO> returnedList = messageInfoMapper.mapAll(new ArrayList<>());

        Assert.assertEquals(new ArrayList<>(),returnedList);
    }
}
