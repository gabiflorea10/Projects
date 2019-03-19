package tester;

import controller.BankAdmin;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tester {

    @Test
    public void AaddPersonTest(){
        BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.addPersonInTable(12, "Ionel", "0722236342");
        if(!message.equals("Success!")) assert(false);
    }

    @Test
    public void AaddPersonTest2(){
        BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.addPersonInTable(15, "Mihai", "0264778914");
        if(!message.equals("Success!")) assert(false);
    }

    @Test
    public void BaddSpendingAccountTest(){
        BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.addSpendingAccountInTable(25, "Ionel", 627, "SpendingAccount");
        if(!message.equals("Success!")) assert(false);
    }

    @Test
    public void BaddSavingAccountTest(){
        BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.addSavingAccountInTable(32,"Ionel", 7480, "SavingAccount", 390);
        if(!message.equals("Success!")) assert(false);
    }

    @Test
    public void CeditPersonTest(){
        BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.editPersonInTabel("Ionel", "0744550818");
        if(!message.equals("Success!")) assert(false);
    }

    @Test
    public void CeditAccountTest(){
        BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.editAccountInTabel(32, "Mihai");
        if(!message.equals("Success!")) assert(false);
    }

    @Test
    public void DdepositTest(){
        BankAdmin bankAdmin=new BankAdmin();
        String[] message=bankAdmin.depositAction(25, 312);
        if(!message[1].equals("Success!")) assert(false);
    }

    @Test
    public void DwithdrawalTest(){
        BankAdmin bankAdmin=new BankAdmin();
        String[] message=bankAdmin.withdrawalAction(32, 4000);
        if(!message[1].equals("Success!")) assert(false);
    }

    @Test
    public void EremoveAccountTest(){
     BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.removeAccountFromTable("Mihai", 32);
        if(!message.equals("Success!")) assert(false);
    }

    @Test
    public void EremovePersonTest(){
     BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.removePersonFromTable("Ionel");
        if(!message.equals("Success!")) assert(false);
    }

    @Test
    public void EremovePersonTest2(){
     BankAdmin bankAdmin=new BankAdmin();
        String message=null;
        message=bankAdmin.removePersonFromTable("Mihai");
        if(!message.equals("Success!")) assert(false);
    }

}
