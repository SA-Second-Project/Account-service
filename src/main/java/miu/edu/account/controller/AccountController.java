package miu.edu.account.controller;


import miu.edu.account.model.Account;
import miu.edu.account.model.AuthenticateRequest;
import miu.edu.account.model.BankInfo;
import miu.edu.account.model.PayPalInfo;
import miu.edu.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;


    // Get all accounts
    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<Account> accounts = accountService.findAll();
        return ResponseEntity.ok(accounts);
    }
    @PostMapping("/authenticate/")
    public Boolean checkUser(@RequestBody AuthenticateRequest authenticateRequest){
        return accountService.checkUser(authenticateRequest.getUsername(),authenticateRequest.getPassword());
    }

    //Get account by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Account> account = accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.createAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/bank")
    public ResponseEntity<?> addBankAccount(@PathVariable Long id, @RequestBody BankInfo bankInfo) {
        BankInfo paymentMethod =  accountService.addBankInfo(bankInfo, id);
        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }


    @PostMapping("/{id}/paypal")
    public ResponseEntity<?> addPayPal(@PathVariable Long id, @RequestBody PayPalInfo paypal) {
        PayPalInfo paymentMethod =  accountService.addPayPal(paypal, id);
        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Account accountBody){
        Account account = accountService.updateAccount(id, accountBody);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping(path = "/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        if (!accountService.removeAccount(accountId)) {
            return new ResponseEntity<>("No Account Found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }
}
