package model;

public class SavingAccount extends Account implements java.io.Serializable{
    private int period;
    private int ok=0;
    private int ok1=0;
    private static final double yearlyInterest=0.20;

    public SavingAccount(int accountId, String holderName, double balance, String accountType, int period) {
        super(accountId, holderName, balance, accountType);
        this.period = period;
    }

    public String deposit(int amount){
        if(ok==0) {
            balance = balance + amount;
            ok++;
            return null;
        }
        else return "Deposit already done!";
    }

    public String withdrawal(int amount){
        if(ok1==0) {
            if ((balance - amount) >= 0) {
                balance = balance - amount;
                ok1++;
                return null;
            }
            else return "Fail";
        }
        else return "Withdrawal already done!";
    }

    public void computeInterest(){
        balance+=balance*yearlyInterest*(double)(period/360);
    }

}
