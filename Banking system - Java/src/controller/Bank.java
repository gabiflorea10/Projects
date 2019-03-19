package controller;

import model.Account;
import model.Person;
import model.SavingAccount;
import model.Observer;
import java.io.*;
import java.util.*;

public class Bank implements BankProc, java.io.Serializable {

    public Hashtable<Person, ArrayList<Account>> data= new Hashtable<>();
    public List<Observer> observerList=new ArrayList<>();

    public void addPerson(Person person){
        assert (!person.name.equals("")): "Invalid person!";
        data.put(person, new ArrayList<Account>());
    }

    public void removePerson(Person person){
        assert(!person.name.equals("")): "Invalid person!";
        Hashtable<Person, ArrayList<Account>> dataAux= new Hashtable<>();
        Set<Person> personSet=data.keySet();
        int ok=0;
        for(Person p: personSet){
            if(!p.name.equals(person.name)){
                dataAux.put(p, data.get(p));
            }
            else ok=1;
        }
        data=dataAux;
        assert(ok==1) :"The person was not found!";
    }

    public void addAccount(Person person, Account account){
        assert(!person.name.equals("")): "Invalid person!";
        assert(account.accountId!=0): "Invalid account!";
        Set<Person> personSet=data.keySet();
        int ok=0;
        if(account instanceof SavingAccount){
            ((SavingAccount) account).computeInterest();
        }
        for(Person p: personSet){
            if(p.name.equals(person.name)) {
                data.get(p).add(account);
                ok=1;
            }
        }
        assert(ok==1) :"The person was not found!";
    }

    public void removeAccount(Person person, Account account){
        assert(!person.name.equals("")): "Invalid person!";
        assert(account.accountId!=0): "Invalid account!";
        Set<Person> personSet=data.keySet();
        int ok=0;
        for(Person p: personSet){
            if(p.name.equals(person.name)){
                ArrayList<Account> list=new ArrayList<>();
                for(Account acc: data.get(p)){
                    if(acc.accountId!=account.accountId){
                        list.add(acc);
                        ok=1;
                    }
                }
                data.get(p).clear();
                data.get(p).addAll(list);
            }
        }
        assert(ok==1) :"The person was not found!";
    }

    public void readAccountData(){
        try {
            FileInputStream fileInputStream=new FileInputStream("datafile.ser");
            ObjectInputStream input=new ObjectInputStream(fileInputStream);
            data=(Hashtable<Person, ArrayList<Account>>)input.readObject();
            observerList=(ArrayList<Observer>)input.readObject();
            input.close();
            fileInputStream.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }

    }

    public void writeAccountData(){
        try {
            FileOutputStream fileOutputStream=new FileOutputStream("datafile.ser");
            ObjectOutputStream output=new ObjectOutputStream(fileOutputStream);
            output.writeObject(data);
            output.writeObject(observerList);
            output.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void reportGenerator(Person person, Account account, int sum, int type){
        if(type==0){
            System.out.println("In the " + person.name + "'s account a withdrawal was done, amount of " + sum +". The available amount in account is "+ account.balance );
        }
        else {
            System.out.println("In the " + person.name + "'s account a deposit was done, amount of " + sum +". The available amount in account is "+ account.balance );
        }
    }

    public void wellFormed(){
        int ok=0;
        Set<Person> personSet=data.keySet();
        for(Person p: personSet){
            if(data.get(p).size()==0) {
                ok++;
                System.out.println("Bank is not well formed. Person " + p.name + " have not any associated accounts.");
            }
        }
        if(ok==0){
            System.out.println("Bank is well formed.");
        }
    }

}
