package com.vamsi.spring.springmvc.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.vamsi.spring.beans.Account;



@Service
public class AccountServiceImpl implements AccountService {
	
	
	@Autowired
	Set<Account> accounts ;

	@Override
	public boolean registerAccount(Account account, String password,
			Errors errors) {
		
		
		boolean valid = !errors.hasErrors();
		if (valid) accounts.add(account);
		
		
		return valid;
	}

}
