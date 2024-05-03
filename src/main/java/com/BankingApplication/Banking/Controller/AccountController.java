package com.BankingApplication.Banking.Controller;

import com.BankingApplication.Banking.Dto.AccountDto;
import com.BankingApplication.Banking.Services.AccountService;
import com.BankingApplication.Banking.Services.Implementation.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
private AccountService accountService;
    //add acount Rest api
    @PostMapping("newaccount")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);

    }
    //Get Account Rest Api
    @GetMapping("/byId/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") long id){
       AccountDto accountDto= accountService.getAccountByid(id);
       return ResponseEntity.ok(accountDto);
    }

    //Deposit Rest Api
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount=request.get("amount");

        AccountDto accountDto=accountService.deposit(id,amount);
        return  ResponseEntity.ok(accountDto);
    }

    //withdraw api
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long id,@RequestBody Map<String,Double> request) {
        double amount=request.get("amount");
       AccountDto accountDto= accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);



    }
    //all accounts
    @GetMapping("/getAllAccount")
    public ResponseEntity<List<AccountDto>> getAccounts(){
        List<AccountDto> accounts=  accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);


    }
    // Delete Account Reat api
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  deleteAccount(@PathVariable("id") Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");


    }





}

