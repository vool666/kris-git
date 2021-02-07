package ee.bcs.valiit.tasks.solution.BankClasses;

import java.math.BigDecimal;

public class WithdrawMoneyBody {
    private int accountid;
    private BigDecimal amount;

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
