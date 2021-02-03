package ee.bcs.valiit.tasks.solution.repository;

import ee.bcs.valiit.tasks.BankController;
import ee.bcs.valiit.tasks.solution.SolutionEmployee;
import ee.bcs.valiit.tasks.solution.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createCustomerRepo(Bank bank) {
        String sql2 = "INSERT INTO customers (customerid, accountid, eesnimi, perekonnanimi, username, password) VALUES (:customeridParameter, :accountidParameter, :eesnimiParameter, :perekonnanimiParameter, :usernameParameter, :passwordParameter)";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("customeridParameter", bank.getCustomerid());
        paramMap2.put("accountidParameter", bank.getAccountid());
        paramMap2.put("eesnimiParameter", bank.getEesnimi());
        paramMap2.put("perekonnanimiParameter", bank.getPerekonnanimi());
        paramMap2.put("usernameParameter", bank.getUsername());
        paramMap2.put("passwordParameter", passwordEncoder.encode(bank.getPassword()));

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

    public BigDecimal getBalanceRepo(int accountid) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountidParameter", accountid);
        try {
            return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public BigDecimal getBalanceRepo2(Bank bank) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountidParameter", bank.getAccountid());
        try {
            return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String findByUserName(String username) {
        String sql = "SELECT password FROM customers WHERE username = :usernameParameter";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("usernameParameter", username);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
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
        String sql = "INSERT INTO transactionhistory (accountid, deposit, time) VALUES (:idParameter, :depositParameter, :timeParameter)";
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
        String sql = "INSERT INTO transactionhistory (accountid, withdraw, time) VALUES (:idParameter, :withdrawParameter, :timeParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", bank.getAccountid());
        paramMap.put("withdrawParameter", bank.getAmount());
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
        String sql = "INSERT INTO transferhistory (fromaccountid, toaccountid, amount, time) VALUES (:fromaccountidParameter, :toaccountidParameter, :amountParameter, :timeParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromaccountidParameter", transferbank.getFromaccount());
        paramMap.put("toaccountidParameter", transferbank.getToaccount());
        paramMap.put("amountParameter", transferbank.getTransferamount());
        paramMap.put("timeParameter", LocalDateTime.now());
        jdbcTemplate.update(sql, paramMap);
    }


    public List<Bank> transactionHistoryRepo() {
        String sql = "SELECT * FROM transactionhistory";
        List<Bank> result = jdbcTemplate.query(sql, new HashMap<>(), new ObjectRowMapper());
        return result;
    }

    private static class ObjectRowMapper implements RowMapper<Bank> {
        @Override
        public Bank mapRow(ResultSet resultSet, int i) throws SQLException {
            Bank bank = new Bank();
            bank.setAccountid(resultSet.getInt("accountid"));
            bank.setAmount(resultSet.getBigDecimal("deposit"));
            bank.setAmount(resultSet.getBigDecimal("withdraw"));
//            bank.setTime(resultSet.getObject("time", LocalDateTime.class));
            return bank;
        }
//controller peab andma sisse kogu info. services teed selle kaheks eraldi ja repo peab callima ainult ühte.
//
//


    }
}
