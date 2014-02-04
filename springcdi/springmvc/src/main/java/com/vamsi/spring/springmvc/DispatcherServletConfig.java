package com.vamsi.spring.springmvc;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.vamsi.spring.beans.Account;

@Configuration
@EnableWebMvc
// this registers all the default handlermappings and handleradapters to the
// context
@ComponentScan("com.vamsi.spring.springmvc")
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver result = new InternalResourceViewResolver();
		result.setPrefix("/WEB-INF/jsp/");
		result.setSuffix(".jsp");
		return result;
	}

	/*
	 * @Bean public NomineeController nomineeController() { NomineeController
	 * nominee = new NomineeController(); nominee.setThanksViewName("thanks");
	 * return nominee; }
	 * the above code didnot work. the controller did not get the view name injected properly
	 * the bean returned by this configuration is not used when request is received
	 */

	@Bean
	public PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		/*
		 * Resource[] resources = new ClassPathResource[] { new
		 * ClassPathResource( "springmvc.properties") };
		 * pspc.setLocations(resources);
		 */
		pspc.setIgnoreUnresolvablePlaceholders(true);

		Properties properties = new Properties();
		properties.put("thanksviewname", "thanks");

		pspc.setProperties(properties);

		return pspc;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/users/registration_ok").setViewName(
				"users/registrationOk");
		registry.addViewController("/nominee/thanks").setViewName(
				"nominee/thanks");
		registry.addViewController("/registerusers/registration_ok").setViewName(
				"users/registrationOk");

		super.addViewControllers(registry);
	}
	
	@Bean
	public Account account() {
		return new Account();
	}
	
	
	@Bean(name="accounts")
	public Set<Account> accounts() {
		Set<Account> accountSet = new HashSet<Account>();
		Account a = new Account();
		a.setLastName("initial");
		accountSet.add(a);
		
		return accountSet;
		
		
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		return messageSource;
	}

}