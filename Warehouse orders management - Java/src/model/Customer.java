package model;

/**
 * Clasa Customer este o clasa ce modeleaza o entitate de tip Customer, avand principalele sale caracteristici
 * Clasa Customer respecta intocmai structura tabelului Customer din baza de date considerata pentru aplicatie
 */

public class Customer {
    private int idCustomer;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Customer(int idCustomer, String name, String address, String phone, String email) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Customer() {
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
