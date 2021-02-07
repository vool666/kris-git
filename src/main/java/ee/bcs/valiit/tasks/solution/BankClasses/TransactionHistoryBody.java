package ee.bcs.valiit.tasks.solution.BankClasses;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;

public class TransactionHistoryBody {
    private int accountid;
    private BigDecimal deposit;
    private BigDecimal withdraw;
    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }
}
