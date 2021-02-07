package ee.bcs.valiit.tasks.solution.repository;

import ee.bcs.valiit.tasks.solution.BankClasses.*;
import ee.bcs.valiit.tasks.solution.controller.MyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createCustomerRepo(CreateCustomerBody createCustomerBody) {
        String sql2 = "INSERT INTO customers (customerid, accountid, eesnimi, perekonnanimi, username, password) VALUES (:customeridParameter, :accountidParameter, :eesnimiParameter, :perekonnanimiParameter, :usernameParameter, :passwordParameter)";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("customeridParameter", createCustomerBody.getCustomerid());
        paramMap2.put("accountidParameter", createCustomerBody.getAccountid());
        paramMap2.put("eesnimiParameter", createCustomerBody.getEesnimi());
        paramMap2.put("perekonnanimiParameter", createCustomerBody.getPerekonnanimi());
        paramMap2.put("usernameParameter", createCustomerBody.getUsername());
        paramMap2.put("passwordParameter", passwordEncoder.encode(createCustomerBody.getPassword()));

        try {
            jdbcTemplate.update(sql2, paramMap2);
        } catch (Exception e) {
            System.out.println(e.getClass());
        }
    }


    public void createAccountRepo(CreateAccountBody createAccountBody) {
        String sql = "INSERT INTO accounts (accountid, balance, customerid) VALUES (:idParameter, :balanceParameter, :customerid)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", createAccountBody.getAccountid());
        paramMap.put("balanceParameter", BigDecimal.ZERO);
        paramMap.put("customerid", createAccountBody.getCustomerid());
        try {
            jdbcTemplate.update(sql, paramMap);
        } catch (DuplicateKeyException e) {
        }
    }

    public void createAccountWithCustomerRepo(CreateCustomerBody createCustomerBody) {
        String sql = "INSERT INTO accounts (accountid, balance, customerid) VALUES (:idParameter, :balanceParameter, :customerid)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", createCustomerBody.getAccountid());
        paramMap.put("balanceParameter", BigDecimal.ZERO);
        paramMap.put("customerid", createCustomerBody.getCustomerid());
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

    public BigDecimal getBalanceForDeposit(DepositMoneyBody depositMoneyBody) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountidParameter", depositMoneyBody.getAccountid());
        try {
            return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public BigDecimal getBalanceForWithdraw(WithdrawMoneyBody withdrawMoneyBody) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountidParameter", withdrawMoneyBody.getAccountid());
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

    public void depositMoneyUpdateRepo(DepositMoneyBody depositMoneyBody, BigDecimal newBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("accountidParameter", depositMoneyBody.getAccountid());
        paramMap2.put("balance", newBalance);
        int minkontroll = (depositMoneyBody.getAmount()).intValue();
        if (minkontroll < 0) {
            throw new MyExeption("Lisatav rahasumma peab olema positiivne.");
        }
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void depositMoneyHistory(DepositMoneyBody depositMoneyBody) {
        String sql = "INSERT INTO transactionhistory (accountid, deposit, time) VALUES (:idParameter, :depositParameter, :timeParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", depositMoneyBody.getAccountid());
        paramMap.put("depositParameter", depositMoneyBody.getAmount());
        paramMap.put("timeParameter", LocalDateTime.now());
        jdbcTemplate.update(sql, paramMap);
    }

    public void withdrawMoneyUpdateRepo(WithdrawMoneyBody withdrawMoneyBody, BigDecimal newBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :accountidParameter";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("accountidParameter", withdrawMoneyBody.getAccountid());
        paramMap2.put("balance", newBalance);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new MyExeption("Not enough money");
        }
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void withdrawMoneyHistory(WithdrawMoneyBody withdrawMoneyBody) {
        String sql = "INSERT INTO transactionhistory (accountid, withdraw, time) VALUES (:idParameter, :withdrawParameter, :timeParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("idParameter", withdrawMoneyBody.getAccountid());
        paramMap.put("withdrawParameter", withdrawMoneyBody.getAmount());
        paramMap.put("timeParameter", LocalDateTime.now());
        jdbcTemplate.update(sql, paramMap);
    }

    public BigDecimal transferFromGetBalance(TransferBody transferBody) {
        String sql = "SELECT balance FROM accounts WHERE accountid = :fromaccountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromaccountNumber", transferBody.getFromaccountid());
        try {
            return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public BigDecimal transferToGetBalance(TransferBody transferBody) {
        String sql3 = "SELECT balance FROM accounts WHERE accountid = :toaccountNumber";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("toaccountNumber", transferBody.getToaccountid());
        try {
            return jdbcTemplate.queryForObject(sql3, paramMap3, BigDecimal.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void transferFromUpdateRepo(TransferBody transferBody, BigDecimal newFromBalance) {
        String sql2 = "UPDATE accounts SET balance = :balance WHERE accountid = :fromaccountNumber";
        Map<String, Object> paramMap2 = new HashMap();
        paramMap2.put("fromaccountNumber", transferBody.getFromaccountid());
        paramMap2.put("balance", newFromBalance);
        if (newFromBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new MyExeption("Not enough money");
        }
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void transferToUpdateRepo(TransferBody transferBody, BigDecimal newToBalance) {
        String sql4 = "UPDATE accounts SET balance = :balance WHERE accountid = :toaccountNumber";
        Map<String, Object> paramMap4 = new HashMap();
        paramMap4.put("toaccountNumber", transferBody.getToaccountid());
        paramMap4.put("balance", newToBalance);
        jdbcTemplate.update(sql4, paramMap4);
    }

    public void transferMoneyHistory(TransferBody transferBody) {
        String sql = "INSERT INTO transferhistory (fromaccountid, toaccountid, amount, time) VALUES (:fromaccountidParameter, :toaccountidParameter, :amountParameter, :timeParameter)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromaccountidParameter", transferBody.getFromaccountid());
        paramMap.put("toaccountidParameter", transferBody.getToaccountid());
        paramMap.put("amountParameter", transferBody.getAmount());
        paramMap.put("timeParameter", LocalDateTime.now());
        jdbcTemplate.update(sql, paramMap);
    }


    public List<TransactionHistoryBody> transactionHistoryRepo() {
        String sql = "SELECT * FROM transactionhistory";
        List<TransactionHistoryBody> result = jdbcTemplate.query(sql, new HashMap<>(), new ObjectRowMapper());
        return result;
    }

    private static class ObjectRowMapper implements RowMapper<TransactionHistoryBody> {
        @Override
        public TransactionHistoryBody mapRow(ResultSet resultSet, int i) throws SQLException {
            TransactionHistoryBody transactionHistoryBody = new TransactionHistoryBody();
            transactionHistoryBody.setAccountid(resultSet.getInt("accountid"));
            transactionHistoryBody.setDeposit(resultSet.getBigDecimal("deposit"));
            transactionHistoryBody.setWithdraw(resultSet.getBigDecimal("withdraw"));
            transactionHistoryBody.setTime(resultSet.getObject("time", LocalDateTime.class));
            return transactionHistoryBody;
        }
    }

    public List<TransferHistoryBody> transferHistoryRepo() {
        String sql = "SELECT * FROM transferhistory";
        List<TransferHistoryBody> result = jdbcTemplate.query(sql, new HashMap<>(), new ObjectRowMapper2());
        return result;
    }

    private static class ObjectRowMapper2 implements RowMapper<TransferHistoryBody> {
        @Override
        public TransferHistoryBody mapRow(ResultSet resultSet, int i) throws SQLException {
            TransferHistoryBody transferHistoryBody = new TransferHistoryBody();
            transferHistoryBody.setFromaccountid(resultSet.getInt("fromaccountid"));
            transferHistoryBody.setToaccountid(resultSet.getInt("toaccountid"));
            transferHistoryBody.setAmount(resultSet.getBigDecimal("amount"));
            transferHistoryBody.setTime(resultSet.getObject("time", LocalDateTime.class));
            return transferHistoryBody;
        }

    }
}
