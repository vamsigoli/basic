package com.vamsi.spring.cdi;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SimpleObjectConfiguration.class,PojoAsSpringBean.class })

public class SimpleObjectTest {
	
	@Inject private SimpleObject so;
	
	@Test public void testThatSimpleObjectIsInitialized() {
		System.out.println(so.getValue());
	}

}
