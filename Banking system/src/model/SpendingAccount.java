package model;

public class SpendingAccount extends Account implements java.io.Serializable {

    public SpendingAccount(int accountId, String holderName, double balance, String accountType) {
        super(accountId, holderName, balance, accountType);
    }

    public String deposit(int amount){
        balance=balance+amount;
        return null;
    }

    public String withdrawal(int amount){
        if((balance-amount)>=0){
            balance=balance-amount;
            return null;
        }
        else return "Fail";
    }
}
