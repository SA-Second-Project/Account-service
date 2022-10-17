package miu.edu.account.service;

import miu.edu.account.client.PaymentClient;
import miu.edu.account.model.Account;
import miu.edu.account.model.BankInfo;
import miu.edu.account.model.PayPalInfo;
import miu.edu.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentClient paymentClient;

    public BankInfo addBankInfo(BankInfo bankInfo, Long id) {
        return paymentClient.addBankInfo(bankInfo,id);
    }
    public PayPalInfo addPayPal(PayPalInfo payPalInfo, Long id) {
        return paymentClient.addPayPal(payPalInfo,id);
    }
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    public Account updateAccount(Long id, Account accountBody) {
        Optional<Account> accountOptional= accountRepository.findById(id);
        if(accountOptional.isPresent()){
            accountBody.setId(id);
            return accountRepository.save(accountBody);
        }
        return accountRepository.save(accountBody);
    }
    public boolean removeAccount(Long accountId) {
        Optional<Account> accountOptional =accountRepository.findById(accountId);
        if(accountOptional.isPresent()){
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    public Boolean checkUser(String email, String password) {
        Account account = accountRepository.findAccountByEmail(email);
        if (account == null){
            return false;
        }
        return account.getPassword().equals(password);
    }
}
