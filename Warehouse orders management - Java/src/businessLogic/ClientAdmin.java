package businessLogic;

import dataAccess.DataObtain;
import dataAccess.DataPut;
import model.Customer;
import javax.swing.*;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Clasa ClientAdmin este o clasa ce efectueaza partea de bussiness a aplicatie pentru tabelul, respectiv clasa Customer
 * Din aceasta clasa sunt invocate metodele de manipulare a datelor bazei de date, aflate in pachetul dataAccess
 * Metodele acestei clase sunt invocate direct din interfata grafica din cadrul ActionListener-ilor
 */

public class ClientAdmin {
    private DataObtain dataObtain=new DataObtain();
    private DataPut dataPut=new DataPut();
    public JTable showCustomers() throws NoSuchMethodException, IntrospectionException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        JTable myTable=new JTable();
        myTable = dataObtain.showingTable(dataObtain.viewTable("model.Customer"));
        return myTable;
    }

    public void addCustomer(String s1, String s2, String s3, String s4, String s5) throws SQLException, IllegalAccessException {
        Customer customer=new Customer(Integer.parseInt(s1), s2, s3, s4, s5);
        dataPut.addInTable(customer);
    }

    public void editCustomer(String s1, String s2, String s3, String s4, String s5) throws SQLException, IllegalAccessException {
        Customer customer=new Customer(Integer.parseInt(s1), s2, s3, s4, s5);
        dataPut.editInTable(customer);
    }

    public void deleteCustomer(String s1) throws SQLException, IllegalAccessException {
        Customer customer=new Customer(Integer.parseInt(s1), null, null, null, null);
        dataPut.deleteFromTable(customer);
    }
}
