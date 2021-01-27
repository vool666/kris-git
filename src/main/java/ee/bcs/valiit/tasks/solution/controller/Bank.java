package ee.bcs.valiit.tasks.solution.controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

public class Bank {


    private String account;
    private BigDecimal balance;
    private BigDecimal amount;
    private String eesnimi;
    private String perekonnanimi;
    private int accountid;
    private int customerid;

    public String getAccount() {
        return account;
    }

    public Bank setAccount(String account) {
        this.account = account;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Bank setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public int getAccountid() {
        return accountid;
    }

    public Bank setAccountid(int accountid) {
        this.accountid = accountid;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEesnimi() {
        return eesnimi;
    }

    public void setEesnimi(String eesnimi) {
        this.eesnimi = eesnimi;
    }

    public String getPerekonnanimi() {
        return perekonnanimi;
    }

    public void setPerekonnanimi(String perekonnanimi) {
        this.perekonnanimi = perekonnanimi;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }
}

