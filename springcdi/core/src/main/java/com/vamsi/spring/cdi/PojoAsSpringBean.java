package com.vamsi.spring.cdi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vamsi.core.*;



@Configuration
public class PojoAsSpringBean {
	
	@Bean
	public Box<Integer>  intBox() {
		return new IntegerBox();
	}

}
