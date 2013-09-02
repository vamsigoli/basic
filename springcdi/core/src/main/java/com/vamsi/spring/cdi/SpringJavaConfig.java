package com.vamsi.spring.cdi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@Configuration not required if we are using scan and named annotations on components
public class SpringJavaConfig {

	/*
	 * not required if we use @Component spring annotation or @Named javax
	 * annotation and use scan method
	 * 
	 * @Bean HollywoodServiceJSR330 hollywoodServiceJSR330() { return new
	 * HollywoodServiceJSR330(); }
	 * 
	 * @Bean AgentFinder agentFinder() { return new WebServiceAgentFinder(); }
	 */

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.scan("com.vamsi");
		ctx.refresh();

		for (String beanname : ctx.getBeanDefinitionNames()) {

			System.out.println(beanname);
		}

		HollywoodServiceJSR330 hollywoodservice = ctx.getBean(
				"hollywoodServiceJSR330", HollywoodServiceJSR330.class);

		hollywoodservice.getFriendlyAgents();

		ctx.close();

	}
}
