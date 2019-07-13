package ro.utcluj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.utcluj.config.SecurityContextUsernameGet;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.mapper.UserInfoMapper;
import ro.utcluj.model.User;
import ro.utcluj.repositories.UserRepository;
import ro.utcluj.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private UserInfoMapper userInfoMapper;
    @Mock private SecurityContextUsernameGet securityContextUsernameGet;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    private UserService userService;
    private UserInfoDTO userInfoDTO;
    private User user;


    @Before
    public void setup(){
        userService = new UserService(userRepository, userInfoMapper, securityContextUsernameGet);
        user = new User("name", "0744223112", "email", "username", "pass", "client",7000 );
        userInfoDTO = new UserInfoDTO(1, "name", "0744223112", "email", "username", "pass", "client",7000 );
    }

    @Test
    public void findAllUserObservableTest(){

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(userInfoMapper.mapAll(anyList())).thenReturn(new ArrayList());

        List<UserInfoDTO> returnedList = userService.findAllUserObservable();

        Assert.assertEquals(new ArrayList<>(),returnedList);
        verify(userRepository,times(1)).findAll();
        verify(userInfoMapper,times(1)).mapAll(anyList());
    }

    @Test
    public void findByUserTest(){

        when(securityContextUsernameGet.getUserNameFromContext()).thenReturn("username");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(userInfoMapper.map(any(User.class))).thenReturn(userInfoDTO);

        UserInfoDTO returnedUserInfoDTO = userService.findByUser();

        Assert.assertEquals(returnedUserInfoDTO.getUsername(), "username");
        verify(securityContextUsernameGet,times(1)).getUserNameFromContext();
        verify(userRepository,times(1)).findByUsername(anyString());
        verify(userInfoMapper,times(1)).map(any(User.class));
    }


    @Test
    public void deleteUserTest(){
        int returnedInt = userService.deleteUser(3);
        Assert.assertEquals(1,returnedInt);
        verify(userRepository,times(1)).deleteById(3);
    }


    @Test
    public void addMoneyTest(){
        when(securityContextUsernameGet.getUserNameFromContext()).thenReturn("name");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(userRepository.getOne(anyInt())).thenReturn(user);

        String returnedString = userService.addMoney(100);

        Assert.assertEquals(returnedString,"Added");
        verify(securityContextUsernameGet,times(1)).getUserNameFromContext();
        verify(userRepository,times(1)).findByUsername(anyString());
        verify(userRepository,times(2)).getOne(anyInt());
    }

    @Test
    public void updateUserDataTest(){
        when(securityContextUsernameGet.getUserNameFromContext()).thenReturn("name");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        int returnedInt = userService.updateUserData("name", "0748225335", "email", "pass");

        Assert.assertEquals(1,returnedInt);

        verify(securityContextUsernameGet,times(1)).getUserNameFromContext();
        verify(userRepository,times(1)).save(any(User.class));
    }

    @Test
    public void updateUserTest(){
        when(userRepository.getOne(anyInt())).thenReturn(user);
        when(userRepository.findByUsername(anyString())).thenReturn(user);

        String returnedString = userService.updateUser(2,"name", "0748225335", "email", "unername", "pass", 5000);

        Assert.assertEquals("",returnedString);

        verify(userRepository,times(1)).getOne(anyInt());
        when(userRepository.findByUsername(anyString())).thenReturn(user);

    }
}
