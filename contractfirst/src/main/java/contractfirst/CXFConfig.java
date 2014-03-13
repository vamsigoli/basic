package contractfirst;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws22.spring.JAXWS22SpringEndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

@Configuration
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CXFConfig {
	@Autowired
	Bus cxfBus;

	// More code

	@Bean
	public Endpoint calculator() {
		JAXWS22SpringEndpointImpl endpoint = new JAXWS22SpringEndpointImpl(
				cxfBus, new PurchasePortTypeImpl());
		String address = "http://localhost:8080/contractfirst/purchase/services/purchaseorder";
		endpoint.setAddress(address);
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