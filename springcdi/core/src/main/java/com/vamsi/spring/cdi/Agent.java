package com.vamsi.spring.cdi;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Agent {

	private String type;

	public String getType() {
		return this.type;
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("pre destroy");

	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("post construct..");
	}

	public void setType(String type) {
		this.type = type;
	}

}
