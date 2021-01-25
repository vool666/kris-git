package ee.bcs.valiit.tasks.solution.repository;

import ee.bcs.valiit.tasks.solution.controller.Bank;
import ee.bcs.valiit.tasks.solution.controller.Transferbank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createCustomerRepo(Bank bank) {
        String sql2 = "INSERT INTO customers (customerid, eesnimi, perekonnanimi) VALUES (:idParameter, :eesnimiParameter, :perekonnanimiParameter)";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("idParameter", bank.getCustomerid());
        paramMap2.put("eesnimiParameter", bank.getEesnimi());
        paramMap2.put("perekonnanimiParameter", bank.getPerekonnanimi());
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void createAccountRepo(Bank bank) {
        String sql = "INSERT INTO accounts (accountid, balance) VALUES (:idParameter, :balanceParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", bank.getAccountid());
        paramMap.put("balanceParameter", BigDecimal.ZERO);
        jdbcTemplate.update(sql, paramMap);
    }

    public BigDecimal getBalanceRepo(Bank bank) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountidParameter", bank.getAccountid());
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
    }


    public void depositMoneyUpdateRepo(Bank bank, BigDecimal newBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("accountidParameter", bank.getAccountid());
        paramMap2.put("balance", newBalance);
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void withdrawMoneyUpdateRepo(Bank bank, BigDecimal newBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("accountidParameter", bank.getAccountid());
        paramMap2.put("balance", newBalance);
        jdbcTemplate.update(sql2, paramMap2);
    }

    public BigDecimal transferFromGetBalance(Transferbank transferbank) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :fromaccountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromaccountNumber", transferbank.getFromaccount());
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
    }

    public BigDecimal transferToGetBalance(Transferbank transferbank) {
        String sql3 = "SELECT balance FROM accounts WHERE accountid = :toaccountNumber";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("toaccountNumber", transferbank.getToaccount());
        return jdbcTemplate.queryForObject(sql3, paramMap3, BigDecimal.class);
    }

    public void transferFromUpdateRepo(Transferbank transferbank, BigDecimal newFromBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :fromaccountNumber";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("fromaccountNumber", transferbank.getFromaccount());
        paramMap2.put("balance", newFromBalance);
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void transferToUpdateRepo(Transferbank transferbank, BigDecimal newToBalance) {
        String sql4 = "UPDATE accounts SET balance = :balance WHERE accountid = :toaccountNumber";
        Map<String, Object> paramMap4 = new HashMap();
        paramMap4.put("toaccountNumber", transferbank.getToaccount());
        paramMap4.put("balance", newToBalance);
        jdbcTemplate.update(sql4, paramMap4);
    }


}
