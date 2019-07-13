package ro.utcluj.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddrug;
    private String name;
    private String producer;
    private String usefulfor;
    private Date termofvalidity;
    private Integer price;

    @OneToMany(mappedBy = "drug", cascade = CascadeType.ALL)
    private Set<UserDrug> userDrugs;

    public Drug(String name, String producer, String usefulfor, Date termofvalidity, Integer price) {
        this.name = name;
        this.producer = producer;
        this.usefulfor = usefulfor;
        this.termofvalidity = termofvalidity;
        this.price = price;
    }

    public Drug() {
    }

    public Integer getIddrug() {
        return iddrug;
    }

    public void setIddrug(Integer iddrug) {
        this.iddrug = iddrug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getUsefulfor() {
        return usefulfor;
    }

    public void setUsefulfor(String usefulfor) {
        this.usefulfor = usefulfor;
    }

    public Date getTermofvalidity() {
        return termofvalidity;
    }

    public void setTermofvalidity(Date termofvalidity) {
        this.termofvalidity = termofvalidity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "iddrug=" + iddrug +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", usefulfor='" + usefulfor + '\'' +
                ", termofvalidity=" + termofvalidity +
                ", price=" + price +
                '}';
    }
}
