package controller;

import model.Account;
import model.Person;

public interface BankProc {

    /**
     * This method is used for add a person in Hashtable
     * @param person
     * @pre Verify if the person is not null
     */
    void addPerson(Person person);

    /**
     * This method is used for remove a person from Hashtable
     * @param person
     * @pre Verify if the person is not null
     * @post Verify if the person was not found
     */
    void removePerson(Person person);

    /**
     * This method add an account
     * @param person
     * @param account
     * @pre Verify if the account is not null
     * @pre Verify if the person is not null
     * @post Verify if the person was found
     */
    void addAccount(Person person, Account account);

    /**
     * This method remove an account of a person
     * @param person
     * @param account
     * @pre Verify if the person is not null
     * @pre Verify if the account is not null
     * @post Verify if the person was not found
     */
    void removeAccount(Person person, Account account);

    void readAccountData();

    void writeAccountData();

    void reportGenerator(Person person, Account account, int sum, int type);

    void wellFormed();

}


