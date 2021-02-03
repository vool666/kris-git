package ee.bcs.valiit.tasks.solution.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankTransactionHistory {
    private int fromaccountid;
    private int toaccountid;
    private BigDecimal deposit;
    private BigDecimal withdraw;
    private BigDecimal transfer;
    private LocalDateTime time;

    public int getFromaccountid() {
        return fromaccountid;
    }

    public void setFromaccountid(int fromaccountid) {
        this.fromaccountid = fromaccountid;
    }

    public int getToaccountid() {
        return toaccountid;
    }

    public void setToaccountid(int toaccountid) {
        this.toaccountid = toaccountid;
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

    public BigDecimal getTransfer() {
        return transfer;
    }

    public void setTransfer(BigDecimal transfer) {
        this.transfer = transfer;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
