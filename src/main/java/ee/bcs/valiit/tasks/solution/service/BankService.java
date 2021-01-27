package ee.bcs.valiit.tasks.solution.service;

import ee.bcs.valiit.tasks.solution.controller.Bank;
import ee.bcs.valiit.tasks.solution.controller.MyExeption;
import ee.bcs.valiit.tasks.solution.controller.ObjectRowMapper;
import ee.bcs.valiit.tasks.solution.controller.Transferbank;
import ee.bcs.valiit.tasks.solution.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;
    private ObjectRowMapper objectRowMapper;

    public void createCustomer(Bank bank) {
        bankRepository.createCustomerRepo(bank);
        bankRepository.createAccountRepo(bank);
    }

    public void createAccount(Bank bank) {
        bankRepository.createAccountRepo(bank);
    }

    public BigDecimal getBalance(Bank bank) {
        return bankRepository.getBalanceRepo(bank);
    }

    public void depositMoney(Bank bank) {
        BigDecimal currentBalance = bankRepository.getBalanceRepo(bank);
        BigDecimal newBalance = currentBalance.add(bank.getAmount());
        bankRepository.depositMoneyUpdateRepo(bank, newBalance);
        bankRepository.depositMoneyHistory(bank);
    }

    public void withdrawMoney(Bank bank) {
        int minkontroll = (bank.getAmount()).intValue();
        if (minkontroll < 0) {
            throw new MyExeption("Lisatav rahasumma peab olema positiivne.");
        }
        BigDecimal currentBalance = bankRepository.getBalanceRepo(bank);
        BigDecimal newBalance = currentBalance.subtract(bank.getAmount());
        bankRepository.withdrawMoneyUpdateRepo(bank, newBalance);
        bankRepository.withdrawMoneyHistory(bank);
    }

    public void transfer(Transferbank transferbank) {
        int minkontroll = (transferbank.getTransferamount()).intValue();
        if (minkontroll < 0) {
            throw new MyExeption("Lisatav rahasumma peab olema positiivne.");
        }
        BigDecimal currentFromBalance = bankRepository.transferFromGetBalance(transferbank);
        BigDecimal newFromBalance = currentFromBalance.subtract(transferbank.getTransferamount());
        bankRepository.transferFromUpdateRepo(transferbank, newFromBalance);
        BigDecimal currentToBalance = bankRepository.transferToGetBalance(transferbank);
        BigDecimal newToBalance = currentToBalance.add(transferbank.getTransferamount());
        bankRepository.transferToUpdateRepo(transferbank, newToBalance);
        bankRepository.transferMoneyHistory(transferbank);
    }
//
//    public void rowMapper(Bank bank) {
//        objectRowMapper.mapRow();
//    }

}
