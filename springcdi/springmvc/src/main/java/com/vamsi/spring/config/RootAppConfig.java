package com.vamsi.spring.springmvc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
//import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.vamsi.spring.springmvc.dao.AccountDao;
import com.vamsi.spring.springmvc.dao.AccountDaoImpl;

@Configuration
@EnableTransactionManagement
public class RootAppConfig {
	
	@Bean
	public DataSource dataSource() {
		
		JndiDataSourceLookup jndiLookup =  new  JndiDataSourceLookup();
	    return jndiLookup.getDataSource("jdbc/empDS");
		
	}
	
	@Bean
	public DefaultPersistenceUnitManager defaultmanager() {
		DefaultPersistenceUnitManager defaultmanager = new DefaultPersistenceUnitManager();
		return defaultmanager;
	}
	
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
	      LocalContainerEntityManagerFactoryBean factoryBean
	       = new LocalContainerEntityManagerFactoryBean();
	      factoryBean.setPersistenceUnitManager(defaultmanager());
	      
	      return factoryBean;
	}
	@Bean
	public JdbcTemplate jdbcTemplate() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	@Bean
	public AccountDao accountDaoImpl() {
		return new AccountDaoImpl();
	}
	
//	@Bean
//	   public PlatformTransactionManager transactionManager(){
//	      JpaTransactionManager transactionManager = new JpaTransactionManager();
//	      transactionManager.setEntityManagerFactory(
//	       entityManagerFactoryBean().getObject() );
//	      
//	      return transactionManager;
//	   }
	
	@Bean
	   public PlatformTransactionManager transactionManager(){
		JtaTransactionManager transactionManager = new JtaTransactionManager();
	  
	      
	      return transactionManager;
	   }

	

}
