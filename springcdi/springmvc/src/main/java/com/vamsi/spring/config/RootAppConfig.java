package com.vamsi.spring.config;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
//import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.vamsi.spring.jobs.AccountsListJob;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.vamsi.spring.springmvc.dao",
		"com.vamsi.spring.springmvc.service", "com.vamsi.spring.jobs" })
public class RootAppConfig {

	@Bean
	public DataSource dataSource() {

		JndiDataSourceLookup jndiLookup = new JndiDataSourceLookup();
		return jndiLookup.getDataSource("jdbc/empDS");

	}

	@Bean
	public DefaultPersistenceUnitManager defaultmanager() {
		DefaultPersistenceUnitManager defaultmanager = new DefaultPersistenceUnitManager();
		return defaultmanager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceUnitManager(defaultmanager());

		return factoryBean;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}

	// @Bean
	// public AccountDao accountDaoImpl() {
	// return new AccountDaoImpl();
	// }

	// @Bean
	// public PlatformTransactionManager transactionManager(){
	// JpaTransactionManager transactionManager = new JpaTransactionManager();
	// transactionManager.setEntityManagerFactory(
	// entityManagerFactoryBean().getObject() );
	//
	// return transactionManager;
	// }

	@Bean
	public PlatformTransactionManager transactionManager() {
		JtaTransactionManager transactionManager = new JtaTransactionManager();

		return transactionManager;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {

		SchedulerFactoryBean sfb = new SchedulerFactoryBean();
		sfb.setDataSource(dataSource());
		sfb.setTransactionManager(transactionManager());
		sfb.setConfigLocation(new ClassPathResource("quartz.properties"));
		sfb.setJobFactory(beanJobFactory());

		return sfb;

	}

	
	//the quartzbean AccountListJob has a @Autowire. to make the injection happen, we need to have the functionality extended
	@Bean
	public AutowiringSpringBeanJobFactory beanJobFactory() {
		AutowiringSpringBeanJobFactory sbjf = new AutowiringSpringBeanJobFactory();
		return sbjf;
	}

	@Bean
	public JobDetailFactoryBean accountListJob() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(AccountsListJob.class);
		jobDetailFactory.setGroup("accounts-quartz");
		return jobDetailFactory;
	}

	@Bean
	public CronTriggerFactoryBean accountListTrigger() {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(accountListJob().getObject());
		cronTriggerFactoryBean.setCronExpression("0 0/30 * * * ?");
		cronTriggerFactoryBean.setGroup("accounts-quartz");
		return cronTriggerFactoryBean;
	}

}
