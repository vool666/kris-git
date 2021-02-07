package ee.bcs.valiit.tasks.solution.BankClasses;

import java.math.BigDecimal;

public class CreateAccountBody {
    private int accountid;
    private int customerid;
    private BigDecimal balance;

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
