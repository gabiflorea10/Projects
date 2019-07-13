package ro.utcluj.mapper;

import org.springframework.stereotype.Component;
import ro.utcluj.dto.OrderInfoDTO;
import ro.utcluj.dto.UserInfoDTO;
import ro.utcluj.model.User;
import ro.utcluj.model.UserDrug;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderInfoMapper {

    UserInfoMapper userInfoMapper;
    DrugInfoMapper drugInfoMapper;

    public OrderInfoMapper() {
        this.userInfoMapper = new UserInfoMapper();
        this.drugInfoMapper = new DrugInfoMapper();
    }

    public OrderInfoDTO map(UserDrug order){
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();

        orderInfoDTO.setId(order.getId());
        orderInfoDTO.setOrderdate(order.getOrderdate());
        orderInfoDTO.setUserInfoDTO(userInfoMapper.map(order.getUser()));
        orderInfoDTO.setDrugInfoDTO(drugInfoMapper.map(order.getDrug()));

        return orderInfoDTO;
    }

    public List<OrderInfoDTO> mapAll(List<UserDrug> orders){
        List<OrderInfoDTO> dtoOrders = new ArrayList<>();
        for(UserDrug order: orders){
            dtoOrders.add(map(order));
        }
        return dtoOrders;
    }

}
