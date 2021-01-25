package ee.bcs.valiit.tasks.solution.controller;

import java.math.BigDecimal;

public class Transferbank {
    private int fromaccount;
    private int toaccount;
    private BigDecimal transferamount;

    public int getFromaccount() {
        return fromaccount;
    }

    public void setFromaccount(int fromaccount) {
        this.fromaccount = fromaccount;
    }

    public int getToaccount() {
        return toaccount;
    }

    public void setToaccount(int toaccount) {
        this.toaccount = toaccount;
    }

    public BigDecimal getTransferamount() {
        return transferamount;
    }

    public void setTransferamount(BigDecimal transferamount) {
        this.transferamount = transferamount;
    }
}
