package contractfirst;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.apache.cxf.transport.servlet.CXFServlet;

public class SpringMVCAppInitializer 
implements WebApplicationInitializer 
//better use the AbstractDispatcherServletInitializer. commented so that it is not initialized
//created webappinitializer
{
//or we could have extended the AbstractDispatcherServletInitializer and override 
	//the specific methods needed
	//read your dropbox notes for onStartup method
	
	//@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		registerDispatcherServlet(servletContext);

	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		WebApplicationContext dispatcherContext = createContext();
//				DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
//				ServletRegistration.Dynamic dispatcher;
//				dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
//				dispatcher.setLoadOnStartup(1);
//				dispatcher.addMapping("/online");
		
		servletContext.addListener(new ContextLoaderListener(dispatcherContext));
//				
				CXFServlet cxfservlet = new CXFServlet();
				ServletRegistration.Dynamic cxfdispatcher;
				cxfdispatcher= servletContext.addServlet("cxf", cxfservlet);
				cxfdispatcher.setLoadOnStartup(1);
				cxfdispatcher.addMapping("/purchase/*");
				
				
				
				}

	private WebApplicationContext createContext(final Class<?>... annotatedClasses) {
		AnnotationConfigWebApplicationContext context;
		context = new AnnotationConfigWebApplicationContext();
		context.scan("contractfirst");
		return context;
	}


}
