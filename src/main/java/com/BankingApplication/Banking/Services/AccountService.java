package com.BankingApplication.Banking.Services;

import com.BankingApplication.Banking.Dto.AccountDto;
import com.BankingApplication.Banking.Entity.Account;

import java.util.List;

public interface AccountService {
AccountDto createAccount(AccountDto account);
AccountDto getAccountByid(Long id);
AccountDto deposit(Long id,double amount);

AccountDto withdraw(Long id,double amount);


List<AccountDto> getAllAccounts();
void deleteAccount(Long id);



}
