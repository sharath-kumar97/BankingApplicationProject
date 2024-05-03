package com.BankingApplication.Banking.Services.Implementation;

import com.BankingApplication.Banking.Dto.AccountDto;
import com.BankingApplication.Banking.Entity.Account;
import com.BankingApplication.Banking.Mapper.AccountMapper;
import com.BankingApplication.Banking.Repository.AccountRepository;
import com.BankingApplication.Banking.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAcccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountByid(Long id) {
        Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account does not exists"));
        return AccountMapper.mapToAcccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account=accountRepository .findById(id).orElseThrow(()->new RuntimeException("Account does not exit"));
        double total=account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAcccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount){
        Account account=accountRepository .findById(id).orElseThrow(()->new RuntimeException("Account does not exit"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient amount");


        }
        double total =account.getBalance() - amount;
        account.setBalance(total);
       Account savaAccount= accountRepository.save(account);


        return AccountMapper.mapToAcccountDto(savaAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
      List<Account> accounts=accountRepository.findAll();
      return accounts.stream().map((account)->AccountMapper.mapToAcccountDto(account)).collect(Collectors.toList());




    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository .findById(id).orElseThrow(()->new RuntimeException("Account does not exit"));
        accountRepository.deleteById(id);
    }

}
