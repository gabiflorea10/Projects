package ro.utcluj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.utcluj.dto.OrderInfoDTO;
import ro.utcluj.mapper.OrderInfoMapper;
import ro.utcluj.model.Drug;
import ro.utcluj.model.User;
import ro.utcluj.model.UserDrug;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoMapperTest {

    @Mock private User user;
    @Mock private Drug drug;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private UserDrug order;
    private OrderInfoMapper orderInfoMapper;

    @Before
    public void setup(){
        order = new UserDrug(user, drug, Date.valueOf("2019-05-05"));
        orderInfoMapper = new OrderInfoMapper();
    }

    @Test
    public void mapTest(){

        OrderInfoDTO orderInfoDTO = orderInfoMapper.map(order);

        Assert.assertEquals(order.getId(), orderInfoDTO.getId());

    }

    @Test
    public void mapAllTest(){

        List<OrderInfoDTO> returnedList = orderInfoMapper.mapAll(new ArrayList<>());

        Assert.assertEquals(new ArrayList<>(),returnedList);
    }
}
