package com.vamsi.spring.springmvc.service;

import org.springframework.validation.Errors;

import com.vamsi.spring.beans.Account;

public interface AccountService {
	
	boolean registerAccount(
			Account account, String password, Errors errors);

}
