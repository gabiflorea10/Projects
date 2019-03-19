package model;

/**
 * Clasa Order este o clasa ce modeleaza o entitate de tip Order, avand principalele sale caracteristici(campuri)
 * Clasa Order respecta intocmai structura tabelului Order din baza de date considerata pentru aplicatie
 */

public class Order {
    private int idOrder;
    private int idProduct;
    private int idCustomer;
    private int quantity;

    public Order(int idOrder, int idProduct, int idCustomer, int quantity) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.idCustomer = idCustomer;
        this.quantity = quantity;
    }

    public Order() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
