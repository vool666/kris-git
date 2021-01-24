package ee.bcs.valiit.tasks.solution.controller;

import ee.bcs.valiit.tasks.Lesson3Hard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson4Controller {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;


    @PostMapping("createCustomer")
    public void createCustomer(@RequestBody Bank bank) {
        String sql2= "INSERT INTO customers (customerid, eesnimi, perekonnanimi) VALUES (:idParameter, :eesnimiParameter, :perekonnanimiParameter)";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("idParameter", bank.getId());
        paramMap2.put("eesnimiParameter", bank.getEesnimi());
        paramMap2.put("perekonnanimiParameter", bank.getPerekonnanimi());
        jdbcTemplate.update(sql2, paramMap2);
    }

    @PostMapping("createAccount")
    public void createAccount(@RequestBody Bank bank) {
        String sql = "INSERT INTO accounts (account, balance) VALUES (:accountParameter, :balanceParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountParameter", bank.getAccount());
        paramMap.put("balanceParameter", BigDecimal.ZERO);
        jdbcTemplate.update(sql, paramMap);
    }

    @GetMapping("getBalance")
    public BigDecimal getBalance(@RequestBody Bank bank) {
        //BigDecimal x = accountBalanceMap.get(accountNr);
        String sql = "SELECT balance FROM accounts WHERE account = :accountParameter";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountParameter", bank.getAccount());
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
    }

    @PutMapping("depositMoney")
    public void depositMoney(@RequestBody Bank bank) {
        String sql = "SELECT balance FROM accounts WHERE account = :accountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", bank.getAccount());
        BigDecimal balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);

                BigDecimal newBalance = balance.add(bank.getAmount());

        String sql2 = "UPDATE accounts SET balance = :balance WHERE account = :accountNumber";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("accountNumber", bank.getAccount());
        paramMap2.put("balance", newBalance);
        jdbcTemplate.update(sql2, paramMap2);
    }

    @PutMapping("withdrawMoney")
    public void withdrawMoney(@RequestBody Bank bank) {
        String sql = "SELECT balance FROM accounts WHERE account = :accountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", bank.getAccount());
        BigDecimal balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);

        BigDecimal newBalance = balance.subtract(bank.getAmount());

        String sql2 = "UPDATE accounts SET balance = :balance WHERE account = :accountNumber";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("accountNumber", bank.getAccount());
        paramMap2.put("balance", newBalance);
        jdbcTemplate.update(sql2, paramMap2);
    }

    @PutMapping("transfer")
    public void transfer(@RequestBody Transferbank transferbank) {
        String sql = "SELECT balance FROM accounts WHERE account = :fromaccountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromaccountNumber", transferbank.getFromaccount());
        BigDecimal balance = jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);

        BigDecimal subtractBalance = balance.subtract(transferbank.getTransferamount());

        String sql2 = "UPDATE accounts SET balance = :balance WHERE account = :fromaccountNumber";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("fromaccountNumber", transferbank.getFromaccount());
        paramMap2.put("balance", subtractBalance);
        jdbcTemplate.update(sql2, paramMap2);

        String sql3 = "SELECT balance FROM accounts WHERE account = :toaccountNumber";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("toaccountNumber", transferbank.getToaccount());
        BigDecimal balance2 = jdbcTemplate.queryForObject(sql3, paramMap3, BigDecimal.class);

        BigDecimal addBalance = balance2.add(transferbank.getTransferamount());

        String sql4 = "UPDATE accounts SET balance = :balance WHERE account = :toaccountNumber";
        Map<String, Object> paramMap4 = new HashMap();
        paramMap4.put("toaccountNumber", transferbank.getToaccount());
        paramMap4.put("balance", addBalance);
        jdbcTemplate.update(sql4, paramMap4);
    }
 // to do- pane kontode tabel klientide tabelisse. keyks jääb tolle tabeli id.
}

