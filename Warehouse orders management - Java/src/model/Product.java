package model;

/**
 * Clasa Product este o clasa ce modeleaza o entitate de tip Product, respectand campurile acestuia
 * Clasa Product respecta intocmai structura tabelului Product din baza de date considerata pentru aplicatia curenta
 */

public class Product {
    private int idProduct;
    private String name;
    private String type;
    private String others;
    private int quantity_in_stock;
    private int price_per_unit;
    private int idSeller;

    public Product(int idProduct, String name, String type, String others, int quantity_in_stock, int price_per_unit, int idSeller) {
        this.idProduct = idProduct;
        this.name = name;
        this.type = type;
        this.others = others;
        this.quantity_in_stock = quantity_in_stock;
        this.price_per_unit = price_per_unit;
        this.idSeller = idSeller;
    }

    public Product() {
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public int getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public void setQuantity_in_stock(int quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    public int getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(int price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    public int getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(int idSeller) {
        this.idSeller = idSeller;
    }
}
