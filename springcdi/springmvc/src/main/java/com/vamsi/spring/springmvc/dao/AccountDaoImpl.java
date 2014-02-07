package com.vamsi.spring.springmvc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.vamsi.spring.beans.Account;

@Repository
public class AccountDaoImpl extends JpaDao<Long, Account> implements AccountDao {

	private static final String UPDATE_PASSWORD_SQL = "update account set password = ? where username = ?";
	@Autowired	
	private JdbcTemplate jdbcTemplate;

	public void create(Account account, String password) {
		create(account);
		jdbcTemplate.update(UPDATE_PASSWORD_SQL, password,
				account.getUsername());
	}

	
	//only one user is expected in the system with the username.
	public Account findByUsername(String username) {
		TypedQuery<Account>q = getEM().createNamedQuery("findAccountByUsername",Account.class);
		q.setParameter("username", username);
		
			
		return (Account) q.getSingleResult();
	}

}
