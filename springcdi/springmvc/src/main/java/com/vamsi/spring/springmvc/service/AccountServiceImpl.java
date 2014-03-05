package com.vamsi.spring.springmvc.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.vamsi.spring.beans.Account;
import com.vamsi.spring.springmvc.dao.AccountDao;

@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger log = LoggerFactory
			.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountDao accountDao;

	@Transactional(readOnly = false)
	public boolean registerAccount(Account account, String password,
			Errors errors) {
		
		log.debug("received account to register {}" ,account );
		
		validateUsername(account.getUsername(), errors);
		boolean valid = !errors.hasErrors();
		if (valid) {
			accountDao.create(account, password);
		}
		return valid;
	}
	
	@Transactional(readOnly=true)
	public Account getAccountDetails(String username) {
		log.debug("received call for getting details for user");
		
		Account account = accountDao.findByUsername(username);
		return account;
	}

	private void validateUsername(String username, Errors errors) {
		if (accountDao.findByUsername(username) != null) {
			errors.rejectValue("username", "error.duplicate",
					new String[] { username }, null);
		}
	}

}
