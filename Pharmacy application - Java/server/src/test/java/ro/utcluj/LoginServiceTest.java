package ro.utcluj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.utcluj.config.SecurityContextUsernameGet;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.mapper.UserInfoMapper;
import ro.utcluj.model.User;
import ro.utcluj.repositories.UserRepository;
import ro.utcluj.service.LoginService;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LoginServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private UserInfoMapper userInfoMapper;
    @Mock private SecurityContextUsernameGet securityContextUsernameGet;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private LoginService loginService;
    private UserInfoDTO userInfoDTO;
    private User user;

    @Before
    public void setup(){
        loginService = new LoginService(userRepository, userInfoMapper, securityContextUsernameGet);
        user = new User("name", "0744223112", "email", "username", "pass", "client",7000 );
        userInfoDTO = new UserInfoDTO(1, "name", "0744223112", "email", "username", "pass", "client",7000 );
        Authentication token = new UsernamePasswordAuthenticationToken(userInfoDTO.getUsername(),userInfoDTO.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    @Test
    public void loginUserTest(){
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(securityContextUsernameGet.getUserNameFromContext()).thenReturn("name");
        when(userInfoMapper.map(any(User.class))).thenReturn(userInfoDTO);

        UserInfoDTO returnedUser = loginService.loginUser();
        Assert.assertEquals(returnedUser.getUsername(),user.getUsername());

        verify(userRepository,times(1)).findByUsername(anyString());
        verify(securityContextUsernameGet,times(1)).getUserNameFromContext();
        verify(userInfoMapper,times(1)).map(any(User.class));
    }


    @Test
    public void saveNewUserTest(){

        when(userRepository.findByUsername(anyString())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        String returnedMessage = loginService.saveNewUser("newName", "0744223000", "NewEmail", "newUsername", "newPass",4000 );

        Assert.assertEquals("",returnedMessage);
        verify(userRepository,times(1)).findByUsername("newUsername");
        verify(userRepository, times(1)).save(new User("newName", "0744223000", "NewEmail", "newUsername", anyString(),"client",4000 ));

    }

}
