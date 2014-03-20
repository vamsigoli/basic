package com.vamsi.spring.springmvc.dao;

import java.util.List;

import com.vamsi.spring.beans.Account;

public interface AccountDao extends Dao<Long,Account > {
	
	void create(Account account, String password);
	Account findByUsername(String username);
	void update(Account account, String password);
	List<Account> findAllAccounts(int startIndex, int pageSize);

}
