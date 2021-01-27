package ee.bcs.valiit.tasks.solution.controller;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectRowMapper implements RowMapper<Bank> {
    @Override
    public Bank mapRow(ResultSet resultSet, int i) throws SQLException {
        Bank bank = new Bank();
        bank.setAccountid(resultSet.getInt("accountid"));
        bank.setCustomerid(resultSet.getInt("customerid"));
        bank.setAmount(resultSet.getBigDecimal("amount"));
        return bank;
    }
}
