package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson3Hard;
import ee.bcs.valiit.tasks.solution.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson4Controller {

    @Autowired
    private BankService bankService;


    @PostMapping("createCustomer")
    public void createCustomer(@RequestBody Bank bank) {
        bankService.createCustomer(bank);
    }

    @PostMapping("createAccount")
    public void createAccount(@RequestBody Bank bank) {
        bankService.createAccount(bank);
    }

    @GetMapping("getBalance")
    public void getBalance(@RequestBody Bank bank) {
        bankService.getBalance(bank);
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

    @GetMapping("getHistory")
    public void getHistory(@RequestBody Bank bank) {
        bankService.
    }

}

