package com.vamsi.spring.springmvc;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//import com.vamsi.spring.beans.Account;

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
	
//not required now. earlier we were testing with in memory account object	
	
//	@Bean
//	public Account account() {
//		return new Account();
//	}
//	
//this did not work. though the bean definition after adding name didnot give error that atleast one bean need to be defined, 
//this account set is not injected. we got a null pointer when we tried to add in the service method
//the only option left was the above where we will get the set of accounts
//when we are expecting an autowiring of set of accounts, spring is thinking that we need all the account beans that are 
//configured in the application. not a dependency injection of an empty collection. 
//if we want a bean to be configured with an empty collection, we need to have it configured. for example, we need
//to configure the accountserviceimpl as a bean rather than autowire the collection	
//and to do that, use @Bean instead of @Service in the configuration file	
//	@Bean(name="accounts")
//	public Set<Account> accounts() {
//		Set<Account> accountSet = new HashSet<Account>();
//		Account a = new Account();
//		a.setLastName("initial");
//		accountSet.add(a);
//		
//		return accountSet;
//		
//		
//	}
	

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		return messageSource;
	}
	
	
	//This is not working for 404. may be web.xml path need to be
	//taken for this.
	
	@Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r =
              new SimpleMappingExceptionResolver();
        
        r.setDefaultErrorView("error/404");

        r.addStatusCode("error/404", org.springframework.http.HttpStatus.NOT_FOUND.value());
        return r;
    }
	
	

}