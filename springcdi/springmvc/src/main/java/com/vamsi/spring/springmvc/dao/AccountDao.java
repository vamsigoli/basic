package com.vamsi.spring.springmvc.dao;

import com.vamsi.spring.beans.Account;

public interface AccountDao extends Dao<Long,Account > {
	
	void create(Account account, String password);
	Account findByUsername(String username);

}