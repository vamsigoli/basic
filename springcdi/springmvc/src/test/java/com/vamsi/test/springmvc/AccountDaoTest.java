package com.vamsi.test.springmvc;

import static org.junit.Assert.*;

import javax.persistence.NoResultException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vamsi.spring.beans.Account;
import com.vamsi.spring.springmvc.dao.AccountDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { EmbeddedDbConfig.class })
public class AccountDaoTest {

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

}