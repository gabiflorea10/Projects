package businessLogic;

import dataAccess.DataObtain;
import dataAccess.DataPut;
import model.Product;
import javax.swing.*;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Clasa WarehouseAdmin este o clasa ce efectueaza partea de bussiness a aplicatie pentru tabelul, respectiv clasa Product
 * Din aceasta clasa sunt invocate metodele de manipulare a datelor bazei de date, aflate in pachetul dataAccess
 * Metodele acestei clase sunt invocate direct din interfata grafica din cadrul ActionListener-ilor
 */

public class WarehouseAdmin {
    private DataObtain dataObtain=new DataObtain();
    private DataPut dataPut=new DataPut();

    public JTable showProducts() throws NoSuchMethodException, IntrospectionException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        JTable myTable=new JTable();
        myTable = dataObtain.showingTable(dataObtain.viewTable("model.Product"));
        return myTable;
    }

    public void addProduct(String s1, String s2, String s3, String s4, String s5, String s6, String s7) throws SQLException, IllegalAccessException {
        Product product=new Product(Integer.parseInt(s1), s2, s3, s4, Integer.parseInt(s5), Integer.parseInt(s6), Integer.parseInt(s7));
        dataPut.addInTable(product);
    }

    public void editProduct(String s1, String s2, String s3, String s4, String s5, String s6, String s7) throws SQLException, IllegalAccessException {
        int t5=-1, t6=-1, t7=-1;
        if(s5!=null) t5=Integer.parseInt(s5);
        if(s6!=null) t6=Integer.parseInt(s6);
        if(s7!=null) t6=Integer.parseInt(s7);
        Product product=new Product(Integer.parseInt(s1), s2, s3, s4, t5, t6, t7);
        dataPut.editInTable(product);
    }

    public void deleteProduct(String s1) throws SQLException, IllegalAccessException {
        Product product=new Product(Integer.parseInt(s1), null, null, null, 0, 0, 0);
        dataPut.deleteFromTable(product);
    }
}
