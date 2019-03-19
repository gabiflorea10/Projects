package model;

/**
 * Clasa Seller este o clasa ce modeleaza o entitate de tip Seller, respectand intocmai campurile acestuia
 * Clasa Seller respecta intocmai structura tabelului Seller din baza de date considerata pentru aplicatie
 */

public class Seller {
    private int idSeller;
    private String name;
    private String phone;
    private String address;

    public Seller(int idSeller, String name, String phone, String address) {
        this.idSeller = idSeller;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Seller() {
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
