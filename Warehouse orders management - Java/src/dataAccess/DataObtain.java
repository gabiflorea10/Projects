package dataAccess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Clasa DataObtain este o clasa cu rol de a furniza date din baza de date, intr-o forma utila pentru aplicatia considerata
 * Clasa aceasta este o clasa ce folosete tehnici de reflection pentru a accesa diversele tabele ale bazei de date
 */

public class DataObtain {

    private Class myClass;

    public List<Object> viewTable(String nameClass) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException, ClassNotFoundException {
        Connection connect=null;
        PreparedStatement statement=null;
        ResultSet rs=null;
        List<Object> myList=new ArrayList<>();
        myClass=myClass.forName(nameClass);
        String statementQuery="select * from "+"sys."+myClass.getSimpleName();
        ConnectionFactory cf=new ConnectionFactory();
        connect=cf.makeConnection();
        statement=connect.prepareStatement(statementQuery);
        rs=statement.executeQuery();
        while(rs.next()){
            Object objInstance=myClass.getDeclaredConstructor().newInstance();
            for(Field field: myClass.getDeclaredFields()){
                Object obj=rs.getObject(field.getName());
                PropertyDescriptor propertyDescriptor=new PropertyDescriptor(field.getName(), myClass);
                Method method=propertyDescriptor.getWriteMethod();
                method.invoke(objInstance, obj);
            }
            myList.add(objInstance);
        }
        return myList;
    }

    public JTable showingTable(List<Object> myList) throws IllegalAccessException {
        DefaultTableModel model=new DefaultTableModel();
        Vector<String> columnNames=new Vector<>();
        for(Field field: myList.get(0).getClass().getDeclaredFields()){
            field.setAccessible(true);
            columnNames.add(field.getName());
        }
        model.setColumnIdentifiers(columnNames);

        for(Object obj: myList){
            Vector<Object> columnData=new Vector<>();

            for(Field field: obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                columnData.add(field.get(obj));
            }
            model.addRow(columnData);
        }

        JTable table=new JTable(model);
        return table;
    }
}
