package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.BankController;
import ee.bcs.valiit.tasks.Lesson3Hard;
import ee.bcs.valiit.tasks.solution.SolutionEmployee;
import ee.bcs.valiit.tasks.solution.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Lesson4Controller {

    @Autowired
    private BankService bankService;
    private List<Bank> bankList = new ArrayList<>();


    @PostMapping("createCustomer")
    public void createCustomer(@RequestBody Bank bank) {
        bankService.createCustomer(bank);
    }

    @PostMapping("createAccount")
    public void createAccount(@RequestBody Bank bank) {
        bankService.createAccount(bank);
    }

    @CrossOrigin
    @GetMapping("getBalance")
    public BigDecimal getBalance(@RequestParam("accountid") int accountid) {
        return bankService.getBalance(accountid);
    }

    @PutMapping("depositMoney")
    public void depositMoney(@RequestBody Bank bank) {
        bankService.depositMoney(bank);
    }

    @PutMapping("withdrawMoney")
    public void withdrawMoney(@RequestBody Bank bank) {
        bankService.withdrawMoney(bank);
    }


    @PutMapping("transfer")
    public void transfer(@RequestBody Transferbank transferbank) {
        bankService.transfer(transferbank);
    }

    @GetMapping("transactionhistory")
    public List<Bank> transactionHistory() {
        return bankService.transactionHistory();
    }



}

