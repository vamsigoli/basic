package com.vamsi.spring.springmvc.service;

import java.util.List;

import org.springframework.validation.Errors;

import com.vamsi.spring.beans.Account;

public interface AccountService {
	
	boolean registerAccount(
			Account account, String password, Errors errors);
	
	Account getAccountDetails(String user) ;
	boolean updateAccount(
			Account account, String password, Errors errors);
	
	List<Account> getAllAccounts(int start, int size);
	

}
