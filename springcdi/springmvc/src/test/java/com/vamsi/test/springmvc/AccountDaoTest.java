package com.vamsi.test.springmvc;

import static org.junit.Assert.*;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.vamsi.spring.beans.Account;
import com.vamsi.spring.springmvc.dao.AccountDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmbeddedDbConfig.class })
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class AccountDaoTest {
	
	Logger logger = LoggerFactory.getLogger(AccountDaoTest.class);

	@Autowired
	private AccountDao myDAO;

	@Test
	public void testCreateMilliTimeItem() {

		// This operation should not throw an Exception
		// long id = myDAO.createMilliTimeItem();

	}

	@Test(expected = NoResultException.class)
	public void testFindByUserName() {
		Account account = myDAO.findByUsername("junk");
		assertNotNull(account);
	}
	@Test(expected = NoResultException.class)
	public void testNullFindByUserName() {
		Account account = myDAO.findByUsername(null);
		assertNotNull(account);
	}
	@Test
	public void testDefaultFindByUserName() {
		Account account = myDAO.findByUsername("vam123");
		assertNotNull(account);
		assertEquals("vamsi", account.getFirstName());
	}

	@Test
	public void testDefaultFindByPK() {
		Account account = myDAO.find(new Long(1));
		assertNotNull(account);
		assertEquals("vamsi", account.getFirstName());
		assertNotEquals("gol", account.getLastName());
	}
	@Test
	public void testSuccessfulCreate() {
		Account freshAccount = new Account();
		freshAccount.setFirstName("geetha");
		freshAccount.setLastName("ch");
		freshAccount.setUsername("gee123");
		freshAccount.setEmail("gee@g.com");
		freshAccount.setAcceptTerms(true);
		freshAccount.setEnabled(true);
		freshAccount.setMarketingOk(true);
		
		myDAO.create(freshAccount, "gee123");
		
		logger.debug("account created {}", freshAccount);
		
		assertNotNull(freshAccount.getId());
		
		
		Account justCreated = myDAO.find(freshAccount.getId());
		
		logger.debug("saved password is {}", justCreated);
		
		
	}
	
	
	
	
	
	
	
	
}