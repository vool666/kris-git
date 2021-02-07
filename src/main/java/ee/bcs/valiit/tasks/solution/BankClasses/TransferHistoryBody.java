package ee.bcs.valiit.tasks.solution.BankClasses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferHistoryBody {
    private int fromaccountid;
    private int toaccountid;
    private BigDecimal amount;
    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
