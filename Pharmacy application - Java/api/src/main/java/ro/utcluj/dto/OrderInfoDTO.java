package ro.utcluj.dto;

import java.io.Serializable;
import java.sql.Date;

public class OrderInfoDTO implements Serializable {
    private Integer id;
    private Date orderdate;
    private UserInfoDTO userInfoDTO;
    private DrugInfoDTO drugInfoDTO;

    public OrderInfoDTO() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }

    public DrugInfoDTO getDrugInfoDTO() {
        return drugInfoDTO;
    }

    public void setDrugInfoDTO(DrugInfoDTO drugInfoDTO) {
        this.drugInfoDTO = drugInfoDTO;
    }

    @Override
    public String toString() {
        return "OrderInfoDTO{" +
                "id=" + id +
                ", orderdate=" + orderdate +
                ", userInfoDTO=" + userInfoDTO +
                ", drugInfoDTO=" + drugInfoDTO +
                '}';
    }
}
