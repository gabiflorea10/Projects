package dataAccess;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clasa DataPut este o clasa cu rol de a introduce, modifica, sau sterge date din baza de date considerata
 * Aceasta clasa folosete tehnici de reflection pentru a putea efectua operatiile necesare pe oricare din tabelele bazei de date
 */

public class DataPut {

    private Class myClass;

    public void addInTable(Object obj) throws SQLException, IllegalAccessException{
        Connection connect=null;
        PreparedStatement statement=null;
        myClass=obj.getClass();
        String statementQuery="insert into "+"sys."+myClass.getSimpleName()+" values (";
        for(Field field: myClass.getDeclaredFields()){
            field.setAccessible(true);
            if(field.get(obj) instanceof Integer){
                statementQuery+=field.get(obj)+", ";
            }
            else{
                statementQuery+="'"+field.get(obj)+"',";
            }
        }
        if(statementQuery.charAt(statementQuery.length()-1)==',') {
            statementQuery = statementQuery.substring(0, statementQuery.length() - 1);
        }
        else {
            statementQuery = statementQuery.substring(0, statementQuery.length() - 2);
        }
        statementQuery+=");";
        ConnectionFactory cf=new ConnectionFactory();
        connect=cf.makeConnection();
        statement=connect.prepareStatement(statementQuery);
        statement.executeUpdate();

    }

    public void editInTable(Object obj) throws SQLException, IllegalAccessException{
        Connection connect=null;
        PreparedStatement statement=null;
        myClass=obj.getClass();
        String statementQuery="update "+"sys."+myClass.getSimpleName()+" set ";
        int numberCounter=0;
        String idPartStatement=null;
        for(Field field: myClass.getDeclaredFields()){
            field.setAccessible(true);
            numberCounter++;
            if(numberCounter==1){
                idPartStatement=field.getName()+"="+field.get(obj)+";";
            }
            else if(numberCounter>1) {
                if (field.get(obj) instanceof Integer) {
                    if (!field.get(obj).equals(-1)) {
                        statementQuery += field.getName() + "=" + field.get(obj);
                        break;
                    }
                }
                else {
                    if (field.get(obj) != null) {
                        statementQuery += field.getName() + "=" + "'" + field.get(obj)+"'";
                        break;
                    }
                }
            }

        }
        statementQuery+=" where "+idPartStatement;
        ConnectionFactory cf=new ConnectionFactory();
        connect=cf.makeConnection();
        statement=connect.prepareStatement(statementQuery);
        statement.executeUpdate();

    }

    public void deleteFromTable(Object obj) throws SQLException, IllegalAccessException{
        Connection connect=null;
        PreparedStatement statement=null;
        myClass=obj.getClass();
        String statementQuery="delete from "+"sys."+myClass.getSimpleName()+" where ";
        int numberCount=0;
        for(Field field: myClass.getDeclaredFields()){
            numberCount++;
            field.setAccessible(true);
            if(numberCount==1) {
                statementQuery+=field.getName()+"="+field.get(obj);
            }
            else if(numberCount>1) {
                break;
            }
        }

        ConnectionFactory cf=new ConnectionFactory();
        connect=cf.makeConnection();
        statement=connect.prepareStatement(statementQuery);
        statement.executeUpdate();
    }
}
