package ee.bcs.valiit.tasks.solution.service;

import ee.bcs.valiit.tasks.solution.BankClasses.*;
import ee.bcs.valiit.tasks.solution.controller.*;
import ee.bcs.valiit.tasks.solution.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public void createCustomer(CreateCustomerBody createCustomerBody) {
        bankRepository.createCustomerRepo(createCustomerBody);
        bankRepository.createAccountWithCustomerRepo(createCustomerBody);
    }

    public void createAccount(CreateAccountBody createAccountBody) {
        bankRepository.createAccountRepo(createAccountBody);
    }

    public BigDecimal getBalance(int accountid) {
        return bankRepository.getBalanceRepo(accountid);
    }

    public void depositMoney(DepositMoneyBody depositMoneyBody) {
        BigDecimal currentBalance = bankRepository.getBalanceForDeposit(depositMoneyBody);
        BigDecimal newBalance = currentBalance.add(depositMoneyBody.getAmount());
        bankRepository.depositMoneyUpdateRepo(depositMoneyBody, newBalance);
        bankRepository.depositMoneyHistory(depositMoneyBody);
    }

    public void withdrawMoney(WithdrawMoneyBody withdrawMoneyBody) {
        int minkontroll = (withdrawMoneyBody.getAmount()).intValue();
        if (minkontroll < 0) {
            throw new MyExeption("Lisatav rahasumma peab olema positiivne.");
        }
        BigDecimal currentBalance = bankRepository.getBalanceForWithdraw(withdrawMoneyBody);
        BigDecimal newBalance = currentBalance.subtract(withdrawMoneyBody.getAmount());
        bankRepository.withdrawMoneyUpdateRepo(withdrawMoneyBody, newBalance);
        bankRepository.withdrawMoneyHistory(withdrawMoneyBody);
    }

    public void transfer(TransferBody transferBody) {
        int minkontroll = (transferBody.getAmount()).intValue();
        if (minkontroll < 0) {
            throw new MyExeption("Lisatav rahasumma peab olema positiivne.");
        }
        BigDecimal currentFromBalance = bankRepository.transferFromGetBalance(transferBody);
        BigDecimal newFromBalance = currentFromBalance.subtract(transferBody.getAmount());
        bankRepository.transferFromUpdateRepo(transferBody, newFromBalance);
        BigDecimal currentToBalance = bankRepository.transferToGetBalance(transferBody);
        BigDecimal newToBalance = currentToBalance.add(transferBody.getAmount());
        bankRepository.transferToUpdateRepo(transferBody, newToBalance);
        bankRepository.transferMoneyHistory(transferBody);
    }

    public List<TransactionHistoryBody> transactionHistory() {
        return bankRepository.transactionHistoryRepo();
    }

    public List<TransferHistoryBody> transferHistory() {
        return bankRepository.transferHistoryRepo();
    }

}
