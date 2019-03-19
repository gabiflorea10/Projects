package model;

public abstract class Account implements java.io.Serializable {
    public int accountId;
    public String holderName;
    public double balance;
    public String accountType;

    public Account(int accountId, String holderName, double balance, String accountType) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.balance = balance;
        this.accountType = accountType;
    }

    public abstract String deposit(int amount);

    public abstract String withdrawal(int amount);

}
