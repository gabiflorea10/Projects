package ro.utcluj;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.mapper.UserInfoMapper;
import ro.utcluj.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserInfoMapperTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private User user;
    private UserInfoMapper userInfoMapper;

    @Before
    public void setup(){
        user = new User("name", "0744223112", "email", "username", "pass", "client",7000 );
        userInfoMapper = new UserInfoMapper();
    }

    @Test
    public void mapTest(){

        UserInfoDTO userInfoDTO = userInfoMapper.map(user);

        Assert.assertEquals(user.getName(), userInfoDTO.getName());

    }

    @Test
    public void mapAllTest(){

        List<UserInfoDTO> returnedList = userInfoMapper.mapAll(new ArrayList<>());

        Assert.assertEquals(new ArrayList<>(),returnedList);
    }

}
