package com.vamsi.test.springmvc;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vamsi.spring.forms.AccountFormValidation;
import com.vamsi.spring.config.DispatcherServletConfig;
import com.vamsi.spring.config.WebAppInitializer;

@WebAppConfiguration
@ContextConfiguration(classes = { EmbeddedDbConfig.class,
		WebAppInitializer.class, DispatcherServletConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class AccountValidationIntegrationTest {

	private static Logger logger = LoggerFactory
			.getLogger(AccountValidationIntegrationTest.class);

	@Autowired
	protected WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(
				status().isOk()).build();
	}

	@Test
	public void thatAccountCreationRendersAsJson() throws Exception {

		this.mockMvc.perform(
				post("/registerusers/addrest")
						.content(standardAccountFormValidationJSON())
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)).andExpect(
				jsonPath("$.Account.username").value("abc123"));
	}

	private static String standardAccountFormValidationJSON() {

		String accountStr = null;

		AccountFormValidation account = new AccountFormValidation();
		account.setUsername("abc123");
		account.setPassword("abc123");
		account.setConfirmPassword("abc123");
		account.setEmail("abc@g.com");
		account.setFirstName("abc");
		account.setLastName("abc");
		account.setMarketingOk(true);
		account.setAcceptTerms(true);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);

		try {
			accountStr = mapper.writeValueAsString(account);
		} catch (Exception e) {
			// do nothing. should not come here.
		}

		logger.debug("json binded account {} " + accountStr);

		return accountStr;
	}

	@Test
	public void thatAccountCreationRendersAsXML() throws Exception {

		this.mockMvc.perform(
				post("/registerusers/addrest")
						.content(standardAccountFormValidationXML())
						.contentType(MediaType.APPLICATION_XML)
						.accept(MediaType.APPLICATION_XML)).andExpect(
				xpath("Account/username").string("abc123"));
	}

	private static String standardAccountFormValidationXML() {

		String accountStr = null;

		AccountFormValidation account = new AccountFormValidation();
		account.setUsername("abc123");
		account.setPassword("abc123");
		account.setConfirmPassword("abc123");
		account.setEmail("abc@g.com");
		account.setFirstName("abc");
		account.setLastName("abc");
		account.setMarketingOk(true);
		account.setAcceptTerms(true);

		try {
			JAXBContext jc = JAXBContext
					.newInstance(AccountFormValidation.class);
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			JAXBElement<AccountFormValidation> je2 = new JAXBElement<AccountFormValidation>(
					new QName("AccountFormValidation"),
					AccountFormValidation.class, account);

			StringWriter str = new StringWriter();
			marshaller.marshal(je2, str);
			accountStr = str.toString();
		} catch (Exception e) {
			// do nothing. should not come here.
		}

		logger.debug("xml binded account {} " + accountStr);

		return accountStr;
	}

}
