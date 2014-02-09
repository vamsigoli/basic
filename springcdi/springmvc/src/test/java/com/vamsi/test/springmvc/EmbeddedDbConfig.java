package com.vamsi.test.springmvc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.vamsi.spring.springmvc.dao.AccountDao;
import com.vamsi.spring.springmvc.dao.AccountDaoImpl;


@Configuration
public class EmbeddedDbConfig {
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
		.setType(EmbeddedDatabaseType.H2)
		.addScript("classpath:setmode.sql")
		.addScript("classpath:createaccount.sql")
		.build();
	}
	
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
	      LocalContainerEntityManagerFactoryBean factoryBean
	       = new LocalContainerEntityManagerFactoryBean();
	      factoryBean.setDataSource( dataSource());
	      factoryBean.setPersistenceUnitName("entities-test");
	      
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(){
	         {
	            // JPA properties ...
	         }
	      };
	      factoryBean.setJpaVendorAdapter( vendorAdapter );

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

}
