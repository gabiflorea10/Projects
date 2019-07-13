package ro.utcluj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.utcluj.config.SecurityContextUsernameGet;
import ro.utcluj.dto.DrugInfoDTO;
import ro.utcluj.dto.OrderInfoDTO;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.mapper.OrderInfoMapper;
import ro.utcluj.model.Drug;
import ro.utcluj.model.User;
import ro.utcluj.model.UserDrug;
import ro.utcluj.repositories.DrugRepository;
import ro.utcluj.repositories.OrderRepository;
import ro.utcluj.repositories.UserRepository;
import ro.utcluj.service.OrderService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class OrderServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private DrugRepository drugRepository;
    @Mock private OrderRepository orderRepository;
    @Mock private OrderInfoMapper orderInfoMapper;
    @Mock private SecurityContextUsernameGet securityContextUsernameGet;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private OrderService orderService;
    private User user;
    private UserInfoDTO userInfoDTO;
    private Drug drug;
    private DrugInfoDTO drugInfoDTO;
    private UserDrug order;

    @Before
    public void setup(){

        orderService = new OrderService(orderRepository, drugRepository, userRepository, orderInfoMapper, securityContextUsernameGet);
        user = new User("name", "0744223112", "email", "username", "pass", "client",7000 );
        userInfoDTO = new UserInfoDTO(1, "name", "0744223112", "email", "username", "pass", "client",7000 );
        drug = new Drug("name", "prducer", "usefulfor", Date.valueOf("2019-07-07"), 100);
        order = new UserDrug(user, drug, Date.valueOf("2019-05-05"));

    }

    @Test
    public void findByUserTest(){

        when(securityContextUsernameGet.getUserNameFromContext()).thenReturn("username");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(orderRepository.findByUser(any(User.class))).thenReturn(new ArrayList<>());
        when(orderInfoMapper.mapAll(anyList())).thenReturn(new ArrayList<>());

       List<OrderInfoDTO> returnedList = orderService.findAllByUser();

        Assert.assertEquals(new ArrayList<>(), returnedList);

        verify(securityContextUsernameGet,times(1)).getUserNameFromContext();
        verify(userRepository,times(1)).findByUsername(anyString());
        verify(orderRepository,times(1)).findByUser(any(User.class));
        verify(orderInfoMapper, times(1)).mapAll(anyList());
    }


    @Test
    public void buyDrugTest(){
        when(userRepository.getOne(anyInt())).thenReturn(user);
        when(drugRepository.getOne(anyInt())).thenReturn(drug);
        when(orderRepository.save(any(UserDrug.class))).thenReturn(order);

        String returnedString = orderService.buyDrug(1, "", "", "");

        Assert.assertEquals(returnedString, "Drug bought!");

        verify(userRepository,times(2)).getOne(anyInt());
        verify(drugRepository,times(1)).getOne(anyInt());
        verify(orderRepository,times(1)).save(any(UserDrug.class));


    }

    @Test
    public void findAllTest(){

        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        when(orderInfoMapper.mapAll(anyList())).thenReturn(new ArrayList<>());

        List<OrderInfoDTO> returnedList = orderService.findAllOrders();

        Assert.assertEquals(new ArrayList<>(), returnedList);

        verify(orderRepository,times(1)).findAll();
        verify(orderInfoMapper, times(1)).mapAll(anyList());
    }

}
