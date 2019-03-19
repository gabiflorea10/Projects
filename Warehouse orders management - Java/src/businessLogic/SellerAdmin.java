package businessLogic;

import dataAccess.DataObtain;
import dataAccess.DataPut;
import model.Seller;
import javax.swing.*;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Clasa SellerAdmin este o clasa ce efectueaza partea de bussiness a aplicatie pentru tabelul, respectiv clasa Seller
 * Din aceasta clasa sunt invocate metodele de manipulare a datelor bazei de date, aflate in pachetul dataAccess
 * Metodele acestei clase sunt invocate direct din interfata grafica din cadrul ActionListener-ilor
 */

public class SellerAdmin {
    private DataObtain dataObtain=new DataObtain();
    private DataPut dataPut=new DataPut();
    public JTable showSellers() throws NoSuchMethodException, IntrospectionException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        JTable myTable=new JTable();
        myTable = dataObtain.showingTable(dataObtain.viewTable("model.Seller"));
        return myTable;
    }

    public void addSeller(String s1, String s2, String s3, String s4) throws SQLException, IllegalAccessException {
        Seller seller=new Seller(Integer.parseInt(s1), s2, s3, s4);
        dataPut.addInTable(seller);
    }

    public void editSeller(String s1, String s2, String s3, String s4) throws SQLException, IllegalAccessException {
        Seller seller=new Seller(Integer.parseInt(s1), s2, s3, s4);
        dataPut.editInTable(seller);
    }

    public void deleteSeller(String s1) throws SQLException, IllegalAccessException {
        Seller seller=new Seller(Integer.parseInt(s1), null, null, null);
        dataPut.deleteFromTable(seller);
    }
}
