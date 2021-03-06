package com.vamsi.spring.springmvc.service;



import java.util.List;

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

	@Transactional(readOnly = false)
	public boolean updateAccount(Account account, String password,
			Errors errors) {
		
		log.debug("received account to register {}" ,account );
		
		existingUsername(account.getUsername(), errors);
		boolean valid = !errors.hasErrors();
		if (valid) {
			accountDao.update(account, password);
		}
		return valid;
	}
	
	private void existingUsername(String username, Errors errors) {
		if (accountDao.findByUsername(username) == null) {
			errors.rejectValue("username", "error.usernotpresent",
					new String[] { username }, null);
		}
	}

	@Override
	public List<Account> getAllAccounts(int start, int size) {
		
		return accountDao.findAllAccounts(start, size);
	}

}
