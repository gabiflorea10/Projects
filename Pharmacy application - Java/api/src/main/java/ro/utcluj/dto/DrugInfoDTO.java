package ro.utcluj.dto;

import java.io.Serializable;
import java.sql.Date;

public class DrugInfoDTO implements Serializable {

    private Integer iddrug;
    private String name;
    private String producer;
    private String usefulfor;
    private Date termofvalidity;
    private Integer price;

    public DrugInfoDTO() {
    }

    public DrugInfoDTO(Integer iddrug, String name, String producer, String usefulfor, Date termofvalidity, Integer price) {
        this.iddrug = iddrug;
        this.name = name;
        this.producer = producer;
        this.usefulfor = usefulfor;
        this.termofvalidity = termofvalidity;
        this.price = price;
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
        return "DrugInfoDTO{" +
                "iddrug=" + iddrug +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", usefulfor='" + usefulfor + '\'' +
                ", termofvalidity=" + termofvalidity +
                ", price=" + price +
                '}';
    }
}
