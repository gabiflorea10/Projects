package controller;

import model.Account;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;
import model.Observable;
import model.Observer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.*;

public class BankAdmin implements Observable {

    Bank bank=new Bank();

    public BankAdmin() {
        bank.readAccountData();
    }

    public JTable showPersons() {
        List<Object> myList=new ArrayList<>();
        myList.addAll(bank.data.keySet());
        bank.wellFormed();
        return showingTable(myList);
    }

    public JTable showAccounts(){
        List<Object> myList=new ArrayList<>();
        Set<Person> personSet=bank.data.keySet();
        for(Person person: personSet){
            myList.addAll(bank.data.get(person));
        }
        bank.wellFormed();
        return showingTable(myList);
    }

    public JTable showingTable(List<Object> myList){
        DefaultTableModel model = new DefaultTableModel();
        Vector<String> columnNames = new Vector<>();
        for (Field field : myList.get(0).getClass().getFields()) {
            field.setAccessible(true);
            columnNames.add(field.getName());
        }
        model.setColumnIdentifiers(columnNames);
        for (Object obj : myList) {
            Vector<Object> columnData = new Vector<>();
            for (Field field : obj.getClass().getFields()) {
                field.setAccessible(true);
                try {
                    columnData.add(field.get(obj));
                } catch (IllegalAccessException e) {
                }
            }
            model.addRow(columnData);
        }
        return new JTable((model));
    }

    public String addPersonInTable(int id, String name, String phone){
        Person person=new Person(id, name, phone);
        bank.addPerson(person);
        addObserver(person);
        bank.writeAccountData();
        return "Success!";
    }

    public String addSpendingAccountInTable(int id, String name, double balance, String type){
        Account account=new SpendingAccount(id, name, balance, type);
        Person person=new Person(157, name, "1");
        bank.addAccount(person, account);
        bank.writeAccountData();
        notifyObservers(id);
        return "Success!";
    }

    public String addSavingAccountInTable(int id, String name, double balance, String type, int period){
        Account account=new SavingAccount(id, name, balance, type, period);
        Person person=new Person(157, name, "1");
        bank.addAccount(person, account);
        bank.writeAccountData();
        notifyObservers(id);
        return "Success!";
    }

    public String removeAccountFromTable(String name, int id){
        Person person=new Person(157, name, "1");
        Account account=new SpendingAccount(id, "a", 1, "a");
        bank.removeAccount(person, account);
        bank.writeAccountData();
        System.out.println("There was an event on "+ name +"'s accounts!");
        return "Success!";
    }

    public String removePersonFromTable(String name){
        Person person=new Person(157, name, "1");
        bank.removePerson(person);
        removeObserver(person);
        bank.writeAccountData();
        return "Success!";
    }

    public String editPersonInTabel(String name, String phone){
        Set<Person> personSet=bank.data.keySet();
        for(Person p: personSet){
            if(p.name.equals(name)) {
                p.phone=phone;
            }
        }
        bank.writeAccountData();
        return "Success!";
    }

    public String editAccountInTabel(int id, String name){
        Hashtable<Person, ArrayList<Account>> dataAux= new Hashtable<>();
        Set<Person> personSet=bank.data.keySet();
        ArrayList<Account> list;
        Account auxAccount=null;
        for(Person p: personSet){
            list=bank.data.get(p);
            dataAux.put(p, new ArrayList<Account>());
            for(Account account: list){
                if(account.accountId!=id){
                    dataAux.get(p).add(account);
                }
                else {
                    auxAccount=account;
                }
            }
        }
        if (auxAccount != null) {
            auxAccount.holderName=name;
        }
        for(Person p: personSet){
            if(p.name.equals(name))
                dataAux.get(p).add(auxAccount);
        }
        bank.data=dataAux;
        bank.writeAccountData();
        notifyObservers(id);
        return "Success!";
    }

    /**
     * This method is used to deposit
     * @param s1 representing the index of account
     * @param s2 representing the sum for deposit
     * @return It is returned a message to the graphical interface
     * @pre Check that sum s2 is in interval [1, 100000]
     * @inv Check that the account balance is in interval [0, 100000]
     */
    public String[] depositAction(int s1, int s2){
        assert(s2>=1 && s2<=100000): "An invalid amount!";
        Set<Person> personSet=bank.data.keySet();
        ArrayList<Account> list;
        String[] message=new String[2];
        for(Person p: personSet){
            list=bank.data.get(p);
            for(Account account: list){
                if(account.accountId==s1){
                    assert ((account.balance+s2)>=0 && (account.balance+s2)<=100000) : "Overflow on the account!";
                    if(account instanceof SavingAccount){
                        SavingAccount savingAccount=(SavingAccount)account;
                        message[0]=savingAccount.deposit(s2);
                        if(message[0]==null) {
                            bank.reportGenerator(p, account, s2, 1);
                            message[1]="Success!";
                        }
                        else message[1]="Fail!";
                    }
                    else if(account instanceof SpendingAccount){
                        SpendingAccount spendingAccount=(SpendingAccount)account;
                        message[0]=spendingAccount.deposit(s2);
                        bank.reportGenerator(p, account, s2, 1);
                        message[1]="Success!";
                    }

                }
            }
        }
        bank.writeAccountData();
        notifyObservers(s1);
        return message;
    }

    /**
     * This method is used to withdrawal
     * @param s1 representing the index of account
     * @param s2 representing the sum for withdrawal
     * @return It is returned a message to the graphical interface
     * @pre Check that sum s2 is in interval [1, 100000]
     * @inv Check that the account balance is in interval [0, 100000]
     */
    public String[] withdrawalAction(int s1, int s2){
        assert(s2>=1 && s2<=100000): "An invalid amount!";
        Set<Person> personSet=bank.data.keySet();
        ArrayList<Account> list;
        String[] message=new String[2];
        for(Person p: personSet){
            list=bank.data.get(p);
            for(Account account: list){
                if(account.accountId==s1){
                    assert ((account.balance-s2)>=0 && (account.balance-s2)<=100000) : "Underflow on the account!";
                    if(account instanceof SavingAccount){
                        SavingAccount savingAccount=(SavingAccount)account;
                        message[0]=savingAccount.withdrawal(s2);
                        if(message[0]==null) {
                            bank.reportGenerator(p, account, s2, 0);
                            message[1]="Success!";
                        }
                        else message[1]="Fail!";
                    }
                    else if(account instanceof SpendingAccount){
                        SpendingAccount spendingAccount=(SpendingAccount)account;
                        message[0]=spendingAccount.withdrawal(s2);
                        bank.reportGenerator(p, account, s2, 0);
                        if(message[0]==null) {
                            message[1] = "Success!";
                        }
                        else message[1]="Fail!";
                    }
                }
            }
        }
        bank.writeAccountData();
        notifyObservers(s1);
        return message;
    }

    @Override
    public void addObserver(Observer observer) {
        bank.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        bank.observerList.remove(observer);
    }

    @Override
    public void notifyObservers(int accountId) {
        for(Observer observer: bank.observerList){
            Person person=(Person)observer;
            Set<Person> personSet=bank.data.keySet();
            for(Person p: personSet){
                if(p.name.equals(person.name)){
                    for(Account account: bank.data.get(p)){
                        if(account.accountId==accountId){
                            observer.notifyEvent();
                        }
                    }
                }
            }

        }
    }
}
