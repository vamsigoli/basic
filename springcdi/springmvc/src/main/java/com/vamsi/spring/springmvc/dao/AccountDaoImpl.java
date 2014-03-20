package com.vamsi.spring.springmvc.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.vamsi.spring.beans.Account;

@Repository
public class AccountDaoImpl extends JpaDao<Long, Account> implements AccountDao {

	private static final String UPDATE_PASSWORD_SQL = "update account set password = ? where username = ?";
	
	private static final String PAGINATING_QUERY = " select * from ( select /*+ FIRST_ROWS(n) */ a.*, ROWNUM rnum "
			+ " from ( select * from account order by id ) a where ROWNUM <= ? ) " 
	+ " where rnum  >= ? ";
	
	private static Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	@Autowired	
	private JdbcTemplate jdbcTemplate;

	public void create(Account account, String password) {
		create(account);
		
		logger.debug("created account successfully. adding password");
		
		
		jdbcTemplate.update(UPDATE_PASSWORD_SQL, password,
				account.getUsername());
	}

	public void update(Account account, String password) {
		update(account);
		
		logger.debug("created account successfully. adding password");
		
		
		jdbcTemplate.update(UPDATE_PASSWORD_SQL, password,
				account.getUsername());
	}

	
	//only one user is expected in the system with the username.
	public Account findByUsername(String username) {
		TypedQuery<Account>q = getEM().createNamedQuery("findAccountByUsername",Account.class);
		q.setParameter("username", username);
		
		Account result = null;	
		
		try {
			
		result = (Account) q.getSingleResult();
		}catch (NoResultException nre) {
			result = null;
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAllAccounts(int startIndex, int pageSize) {
		
		return (List<Account>)em.createNativeQuery(PAGINATING_QUERY, Account.class).setParameter(1, pageSize).setParameter(2,startIndex ).getResultList();
		
	}

}
