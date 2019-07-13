package ro.utcluj.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;


@Entity
@IdClass(UserDrug.class)
public class UserDrug implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_drug")
    private Drug drug;

    private Date orderdate;

    public UserDrug(User user, Drug drug, Date orderdate) {
        this.user = user;
        this.drug = drug;
        this.orderdate = orderdate;
    }

    public UserDrug() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    @Override
    public String toString() {
        return "UserDrug{" +
                "idorder=" + id +
                ", orderdate=" + orderdate +
                ", user=" + user +
                ", drug=" + drug +
                '}';
    }
}
