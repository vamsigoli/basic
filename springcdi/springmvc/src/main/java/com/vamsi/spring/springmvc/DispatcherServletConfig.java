package com.vamsi.spring.springmvc;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.vamsi.spring.springmvc.controller.NomineeController;

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

		super.addViewControllers(registry);
	}

}