package ee.bcs.valiit.tasks.solution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ObjectRowMapper implements RowMapper<Bank> {
    @Autowired
    private List<Bank> bankList = new ArrayList<>();

    @Override
    public Bank mapRow(ResultSet resultSet, int i) throws SQLException {
        Bank bank = new Bank();
        bank.setAccountid(resultSet.getInt("accountid"));
        bank.setCustomerid(resultSet.getInt("customerid"));
        bank.setAmount(resultSet.getBigDecimal("amount"));
        return bank;
    }


}
