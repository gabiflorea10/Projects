package ro.utcluj.api;

import ro.utcluj.dto.OrderInfoDTO;
import ro.utcluj.dto.UserInfoDTO;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface OrderServiceInterface {

    List<OrderInfoDTO> findAllByUser();

    String buyDrug(Integer idOfDrug, String prescriptionName, String prescriptionDrug, String prescriptionType);

    List<OrderInfoDTO> findAllOrders();

}
