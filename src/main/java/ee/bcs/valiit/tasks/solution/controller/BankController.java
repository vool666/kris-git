package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.solution.BankClasses.*;
import ee.bcs.valiit.tasks.solution.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class BankController {

    @Autowired
    private BankService bankService;

    @CrossOrigin
    @PostMapping("createCustomer")
    public void createCustomer(@RequestBody CreateCustomerBody createCustomerBody) {
        bankService.createCustomer(createCustomerBody);
    }
    @CrossOrigin
    @PostMapping("createAccount")
    public void createAccount(@RequestBody CreateAccountBody createAccountBody) {
        bankService.createAccount(createAccountBody);
    }

    @CrossOrigin
    @GetMapping("getBalance")
    public BigDecimal getBalance(@RequestParam("accountid") int accountid) {
        return bankService.getBalance(accountid);
    }

    @CrossOrigin
    @PutMapping("depositMoney")
    public void depositMoney(@RequestBody DepositMoneyBody depositMoneyBody) {
        bankService.depositMoney(depositMoneyBody);
    }
    @CrossOrigin
    @PutMapping("withdrawMoney")
    public void withdrawMoney(@RequestBody WithdrawMoneyBody withdrawMoneyBody) {
        bankService.withdrawMoney(withdrawMoneyBody);
    }
    @CrossOrigin
    @PutMapping("transfer")
    public void transfer(@RequestBody TransferBody transferBody) {
        bankService.transfer(transferBody);
    }

    @CrossOrigin
    @GetMapping("transactionhistory")
    public List<TransactionHistoryBody> transactionHistory() {
        return bankService.transactionHistory();
    }

    @CrossOrigin
    @GetMapping("transferhistory")
    public List<TransferHistoryBody> transferHistory() {
        return bankService.transferHistory();
    }

}

