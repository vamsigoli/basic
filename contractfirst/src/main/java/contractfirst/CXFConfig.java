package contractfirst;


import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
//import org.apache.cxf.jaxws.EndpointImpl;
//import org.apache.cxf.jaxws.support.JaxWsEndpointImpl;

//import org.apache.cxf.jaxws22.EndpointImpl;

import org.apache.cxf.jaxws22.spring.JAXWS22SpringEndpointImpl;

import com.namespacetest.shopping.PurchasePortTypeImpl;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CXFConfig {
	@Autowired
	Bus cxfBus;

//make sure that the war deployed has wsdl in the WEB-INF/wsdl location.
	//use eclipse deployment assembly and maven pom for the required adjustments
	@Bean
	public Endpoint purchaseorder() {
		String address = "/purchaseorder";
		
		PurchasePortTypeImpl implementor =  new PurchasePortTypeImpl();
		
		JAXWS22SpringEndpointImpl endpoint = new JAXWS22SpringEndpointImpl(cxfBus, implementor);
		endpoint.setAddress(address);
		endpoint.setWsdlLocation("WEB-INF/wsdl/shopping.wsdl");
		endpoint.publish();
		return endpoint;
	}
	
	@Bean
	public LoggingInInterceptor logInbound() {
		return new LoggingInInterceptor();
		
	}
	@Bean
	public LoggingOutInterceptor logOutbound() {
		return new LoggingOutInterceptor();
	}
	
}