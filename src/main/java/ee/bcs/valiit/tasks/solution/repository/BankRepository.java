package ee.bcs.valiit.tasks.solution.repository;

import ee.bcs.valiit.tasks.solution.controller.Bank;
import ee.bcs.valiit.tasks.solution.controller.MyExeption;
import ee.bcs.valiit.tasks.solution.controller.ObjectRowMapper;
import ee.bcs.valiit.tasks.solution.controller.Transferbank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createCustomerRepo(Bank bank) {
        String sql2 = "INSERT INTO customers (customerid, accountid, eesnimi, perekonnanimi) VALUES (:customeridParameter, :accountidParameter, :eesnimiParameter, :perekonnanimiParameter)";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("customeridParameter", bank.getCustomerid());
        paramMap2.put("accountidParameter", bank.getAccountid());
        paramMap2.put("eesnimiParameter", bank.getEesnimi());
        paramMap2.put("perekonnanimiParameter", bank.getPerekonnanimi());
        try {
            jdbcTemplate.update(sql2, paramMap2);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }


    public void createAccountRepo(Bank bank) {
        String sql = "INSERT INTO accounts (accountid, balance, customerid) VALUES (:idParameter, :balanceParameter, :customerid)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", bank.getAccountid());
        paramMap.put("balanceParameter", BigDecimal.ZERO);
        paramMap.put("customerid", bank.getCustomerid());
        try {
            jdbcTemplate.update(sql, paramMap);
        } catch (DuplicateKeyException e) {
        }
    }

    public BigDecimal getBalanceRepo(Bank bank) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountidParameter", bank.getAccountid());
        try {
            return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void depositMoneyUpdateRepo(Bank bank, BigDecimal newBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("accountidParameter", bank.getAccountid());
        paramMap2.put("balance", newBalance);
        int minkontroll = (bank.getAmount()).intValue();
        if (minkontroll < 0) {
            throw new MyExeption("Lisatav rahasumma peab olema positiivne.");
        }
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void depositMoneyHistory(Bank bank) {
        String sql = "INSERT INTO transactionhistory (toaccountid, deposit, time) VALUES (:idParameter, :depositParameter, :timeParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", bank.getAccountid());
        paramMap.put("depositParameter", bank.getAmount());
        paramMap.put("timeParameter", LocalDateTime.now());
        jdbcTemplate.update(sql, paramMap);
    }

    public void withdrawMoneyUpdateRepo(Bank bank, BigDecimal newBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("accountidParameter", bank.getAccountid());
        paramMap2.put("balance", newBalance);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new MyExeption("Not enough money");
        }
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void withdrawMoneyHistory(Bank bank) {
        String sql = "INSERT INTO transactionhistory (fromaccountid, deposit, time) VALUES (:idParameter, :depositParameter, :timeParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", bank.getAccountid());
        paramMap.put("depositParameter", bank.getAmount());
        paramMap.put("timeParameter", LocalDateTime.now());
        jdbcTemplate.update(sql, paramMap);
    }

    public BigDecimal transferFromGetBalance(Transferbank transferbank) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :fromaccountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromaccountNumber", transferbank.getFromaccount());
        try {
            return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public BigDecimal transferToGetBalance(Transferbank transferbank) {
        String sql3 = "SELECT balance FROM accounts WHERE accountid = :toaccountNumber";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("toaccountNumber", transferbank.getToaccount());
        try {
            return jdbcTemplate.queryForObject(sql3, paramMap3, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void transferFromUpdateRepo(Transferbank transferbank, BigDecimal newFromBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :fromaccountNumber";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("fromaccountNumber", transferbank.getFromaccount());
        paramMap2.put("balance", newFromBalance);
        if (newFromBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new MyExeption("Not enough money");
        }
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void transferToUpdateRepo(Transferbank transferbank, BigDecimal newToBalance) {
        String sql4 = "UPDATE accounts SET balance = :balance WHERE accountid = :toaccountNumber";
        Map<String, Object> paramMap4 = new HashMap();
        paramMap4.put("toaccountNumber", transferbank.getToaccount());
        paramMap4.put("balance", newToBalance);
        jdbcTemplate.update(sql4, paramMap4);
    }

    public void transferMoneyHistory(Transferbank transferbank) {
        String sql = "INSERT INTO transactionhistory (fromaccountid, toaccountid, transfer, time) VALUES (:fromidParameter, :toidParameter, :transferParameter, :timeParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromidParameter", transferbank.getFromaccount());
        paramMap.put("toidParameter", transferbank.getToaccount());
        paramMap.put("transferParameter", transferbank.getTransferamount());
        paramMap.put("timeParameter", LocalDateTime.now());
        jdbcTemplate.update(sql, paramMap);
    }

//    public <HistoryList> getHistoryRepo(Bank bank) {
//        String sql = "SELECT * FROM transactionhistory WHERE fromaccountid = :accountidParameter";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("accountidParameter", bank.getAccountid());
//        return jdbcTemplate.queryForObject(sql, paramMap, <HistoryList>.class);
//        }
//    }

//    public List<Bank> findByAccountId(int id) {
//        String sql = "SELECT * FROM transactionhistory";
//        List<Bank> list = new ArrayList<>();
//        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
//        for (Map row : rows) {
//            Bank obj = new Bank();
//        }
//    }


}
