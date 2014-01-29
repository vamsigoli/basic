package com.vamsi.spring.springmvc.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import com.vamsi.spring.beans.Account;



@Service
public class AccountServiceImpl implements AccountService {
	
	
	private static final Logger  log = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	Set<Account> accounts ;

	@Override
	public boolean registerAccount(Account account, String password,
			Errors errors) {
		
	
		log.info("received account to register: {}" + account );
		
		
		boolean valid = !errors.hasErrors();
		if (valid) accounts.add(account);
		
		
		return valid;
	}

}
