package com.vamsi.spring.springmvc;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringMVCAppInitializer implements WebApplicationInitializer {
//or we could have extended the AbstractDispatcherServletInitializer and override 
	//the specific methods needed
	
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		registerDispatcherServlet(servletContext);

	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		WebApplicationContext dispatcherContext = createContext();
				DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
				ServletRegistration.Dynamic dispatcher;
				dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
				dispatcher.setLoadOnStartup(1);
				dispatcher.addMapping("/");
				
				
				}

	private WebApplicationContext createContext(final Class<?>... annotatedClasses) {
		AnnotationConfigWebApplicationContext context;
		context = new AnnotationConfigWebApplicationContext();
		//context.scan("com.vamsi.spring.springmvc");		
		context.register(DispatcherServletConfig.class);
		return context;
	}

}
