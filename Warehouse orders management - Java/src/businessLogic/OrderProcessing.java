package businessLogic;

import dataAccess.DataObtain;
import dataAccess.DataPut;
import model.Order;
import javax.swing.*;
import java.beans.IntrospectionException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Clasa OrderProcessing este o clasa ce efectueaza partea de bussiness a aplicatie pentru tabelul, respectiv clasa Order
 * Din aceasta clasa sunt invocate metodele de manipulare a datelor bazei de date, aflate in pachetul dataAccess
 * Metodele acestei clase sunt invocate direct din interfata grafica din cadrul ActionListener-ilor
 * Metoda addOrder a acestei clase efectueaza pe langa introducerea propriu-zisa a unei entitati Order, si actualizarea
 * tabelei Product (campul quantity_in_stock), respectiv tiparirea unei facturi, in format .txt
 */

public class OrderProcessing {
    private DataObtain dataObtain=new DataObtain();
    private DataPut dataPut=new DataPut();

    public JTable showOrders() throws NoSuchMethodException, IntrospectionException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        JTable myTable=new JTable();
        myTable = dataObtain.showingTable(dataObtain.viewTable("model.Order"));
        return myTable;
    }

    public String addOrder(String s2, String s3, String s4) throws SQLException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, IntrospectionException, InstantiationException, InvocationTargetException {
        int nextIndex=0, existingQuantity=-1, requestedQuantity=0, numberCounter=0, idProd=0, price=0;
        boolean var=false, var1=false;
        String message=null, prodName=null;
        idProd=Integer.parseInt(s2);
        requestedQuantity=Integer.parseInt(s4);

        List<Object> list=dataObtain.viewTable("model.Product");
        for(Object obj: list){
            numberCounter=0;
            for(Field field: obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                numberCounter++;
                if(numberCounter==1){
                    if(field.get(obj).equals(idProd)) var=true;
                }
                if(numberCounter==2 && var){
                    prodName=(String)field.get(obj);
                }
                if(numberCounter==5 && var){
                    existingQuantity=(Integer)field.get(obj);
                    var1=true;
                }
                if(numberCounter==6 && var && var1){
                    price=(Integer)field.get(obj);
                    break;
                }
            }
            if(var1) break;
        }
        list.clear();
        list=dataObtain.viewTable("model.Order");
        for(Object obj: list){
            numberCounter=0;
            for(Field field: obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                numberCounter++;
                if(numberCounter==1){
                    nextIndex=(Integer)field.get(obj);
                }
            }
        }

        nextIndex++;

        if(requestedQuantity>existingQuantity) {
            message="Under-stock";
        }
        else{
            Order order=new Order(nextIndex, Integer.parseInt(s2), Integer.parseInt(s3), Integer.parseInt(s4));
            dataPut.addInTable(order);

            WarehouseAdmin warehouseAdmin=new WarehouseAdmin();
            warehouseAdmin.editProduct(s2, null, null, null, Integer.toString(existingQuantity-requestedQuantity), null, null);

            printBill(nextIndex, requestedQuantity, price, prodName, s3);
        }

        return message;
    }

    private void printBill(int id, int quantity, int price, String nameProduct, String idCustomer){
        String fileName="bill"+ Integer.toString(id)+".txt";
        try {
            PrintWriter pw=new PrintWriter(fileName, "UTF-8");
            pw.println("Factura\n: " + nameProduct+ ", in cantitate de: " + quantity + ", pret per unitate: "+ price +",\ntotal de plata: "+(price*quantity)+",\ncumparator: "+ idCustomer);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
