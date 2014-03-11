package com.vamsi.spring.cdi;

import javax.inject.Inject;

import org.springframework.beans.factory.FactoryBean;


public class SimpleObjectFactoryBean implements FactoryBean<SimpleObject>{
	
	
	//autowiring happens at factory level. not at individual object level.
	@Inject private Box<Integer> box;

	@Override
	public SimpleObject getObject() throws Exception {
		
		SimpleObject obj = new SimpleObjectImpl();
		//dependencies have to be set to the object
		obj.setValue(box);
		return obj;
	}

	@Override
	public Class<?> getObjectType() {
		return SimpleObject.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
