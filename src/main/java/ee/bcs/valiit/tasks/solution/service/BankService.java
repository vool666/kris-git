package ee.bcs.valiit.tasks.solution.service;

import ee.bcs.valiit.tasks.solution.controller.Bank;
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

    public void createCustomer(Bank bank) {
        bankRepository.createCustomerRepo(bank);
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
    }

    public void withdrawMoney(Bank bank) {
        BigDecimal currentBalance = bankRepository.getBalanceRepo(bank);
        BigDecimal newBalance = currentBalance.subtract(bank.getAmount());
        bankRepository.withdrawMoneyUpdateRepo(bank, newBalance);
    }

    public void transfer(Transferbank transferbank) {
        BigDecimal currentFromBalance = bankRepository.transferFromGetBalance(transferbank);
        BigDecimal newFromBalance = currentFromBalance.subtract(transferbank.getTransferamount());
        bankRepository.transferFromUpdateRepo(transferbank, newFromBalance);
        BigDecimal currentToBalance = bankRepository.transferToGetBalance(transferbank);
        BigDecimal newToBalance = currentToBalance.add(transferbank.getTransferamount());
        bankRepository.transferToUpdateRepo(transferbank, newToBalance);
    }

}
