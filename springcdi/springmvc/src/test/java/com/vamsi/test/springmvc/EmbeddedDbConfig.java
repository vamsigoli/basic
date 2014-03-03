package com.vamsi.test.springmvc;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vamsi.spring.springmvc.dao.AccountDao;
import com.vamsi.spring.springmvc.dao.AccountDaoImpl;


@Configuration
@EnableTransactionManagement
@ComponentScan({"com.vamsi.spring.springmvc.dao","com.vamsi.spring.springmvc.service"})
public class EmbeddedDbConfig {
	
	Logger logger = LoggerFactory.getLogger(EmbeddedDbConfig.class);
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
		.setType(EmbeddedDatabaseType.H2)
		.addScript("classpath:setmode.sql")
		.addScript("classpath:createaccount.sql")
		.addScript("classpath:defaultdata.sql")
		.build();
	}
	
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
	      LocalContainerEntityManagerFactoryBean factoryBean
	       = new LocalContainerEntityManagerFactoryBean();
	      factoryBean.setDataSource( dataSource());
	      factoryBean.setPersistenceUnitName("entities-test");
	      
	      //this statement below is really helping the transaction management.
	      //if we dont add this vendor adaptor, h2 is trying to get the lock on
	      //account table for the update statement and it is unable to get 
	      //i believe hibernate is managing the transaction internally and 
	      //allowing h2 to be successful in updating the database as well
	      
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(){
	         {
	            // JPA properties ...
	         }
	      };
	      factoryBean.setJpaVendorAdapter( vendorAdapter );
	      
//	      JpaVendorAdapter a = factoryBean.getJpaVendorAdapter();
	      
	      
//	      logger.debug("jpa adaptor got is " + a.getClass().getSimpleName());

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
	
	@Bean
	   public PlatformTransactionManager transactionManager(){
	      JpaTransactionManager transactionManager = new JpaTransactionManager();
	      transactionManager.setEntityManagerFactory(
	       this.entityManagerFactoryBean().getObject() );
	      
	      return transactionManager;
	   }
	
	

}
