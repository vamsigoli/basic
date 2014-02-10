package com.vamsi.spring.cdi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ScopedProxyTest {
 
 @Autowired private SingletonScopedBean singletonScopedBean;
 
 @Test
 public void testScopedProxy() {
	 assertNotEquals(singletonScopedBean.getState(), singletonScopedBean.getState());
 
 }
 
 @Configuration
 @ComponentScan(basePackages="com.vamsi.spring.cdi", resourcePattern="*Scope*")
 public static class SpringContext{}

}
