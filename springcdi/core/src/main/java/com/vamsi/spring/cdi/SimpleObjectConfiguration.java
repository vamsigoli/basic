package com.vamsi.spring.cdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SimpleObjectConfiguration {
	
	//configuration returns a BeanFactory. not a bean. 
	//spring is smart enough to see that this bean implements a factorybean interface
	//and calls the getObject method of this factory when ever there is a need for simpleobject
	//any dependencies of the object are the responsibility of the factorybean. not the container
	//container is responsible for the factory. not the beans produced by the factory
	@Bean public SimpleObjectFactoryBean factoryBean() {
		return new SimpleObjectFactoryBean();
	}

}
