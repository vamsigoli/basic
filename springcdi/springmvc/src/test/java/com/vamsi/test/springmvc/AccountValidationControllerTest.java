package com.vamsi.test.springmvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.vamsi.spring.beans.Account;
import com.vamsi.spring.forms.AccountFormValidation;
import com.vamsi.spring.springmvc.controller.AccountValidationController;
import com.vamsi.spring.springmvc.service.AccountService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AccountValidationControllerTest {

	@Configuration
	public static class BasicConfig {

		@Bean
		public MappingJackson2HttpMessageConverter converterConfig() {

			MappingJackson2HttpMessageConverter jackson = new MappingJackson2HttpMessageConverter();
			jackson.setObjectMapper(jacksonMapper().getObject());
			return jackson;
		}

		@Bean
		public Jackson2ObjectMapperFactoryBean jacksonMapper() {
			Jackson2ObjectMapperFactoryBean objmapper = new Jackson2ObjectMapperFactoryBean();
			objmapper.setFeaturesToEnable(SerializationFeature.WRAP_ROOT_VALUE,
					DeserializationFeature.UNWRAP_ROOT_VALUE);
			return objmapper;

		}

	}

	private static final Logger logger = LoggerFactory
			.getLogger(AccountValidationControllerTest.class);

	@Mock
	private AccountService mockAccountService;
	
	@Mock
	private AuthenticationManager authenticationManager;

	@InjectMocks
	private AccountValidationController controller;

	@Autowired
	MappingJackson2HttpMessageConverter converter;
	
	private TestingAuthenticationProvider authProvider = new TestingAuthenticationProvider();

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setMessageConverters(converter).build();

	}

	@Test
	public void test_getRegistrationForm() throws Exception {
		this.mockMvc.perform(get("/registerusers/newaccount")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("accountFormValidation"))
				.andExpect(view().name("users/registrationForm"));
	}

	@Test
	public void testSuccess_postRegistrationForm() throws Exception {

		doAnswer(new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocationOnMock)
					throws Throwable {
				return Boolean.TRUE;
			}
		}).when(mockAccountService).registerAccount(any(Account.class),
				anyString(), any(BindingResult.class));

		doAnswer(new Answer<Authentication>() {
			@Override
			public Authentication answer(InvocationOnMock invocationOnMock)
					throws Throwable {
				Authentication request = (Authentication) invocationOnMock.getArguments()[0];
				Authentication result = authProvider.authenticate(request);
				return result;
			}
		}).when(authenticationManager).authenticate(any(Authentication.class));
		
		this.mockMvc
				.perform(
						post("/registerusers/adduser")
								.param("username", "abc123")
								.param("password", "abc123")
								.param("confirmPassword", "abc123")
								.param("email", "abc@g.com")
								.param("firstName", "abc")
								.param("lastName", "abc")
								.param("marketingOk", "true")
								.param("_marketingOk", "on")
								.param("acceptTerms", "true")
								.param("_acceptTerms", "on")).andDo(print())
				.andExpect(status().isMovedTemporarily())
				.andExpect(redirectedUrl("registration_ok"))
				.andExpect(flash().attributeExists("accountFormValidation"));
	}

	@Test
	public void testError_postRegistrationForm() throws Exception {

		doAnswer(new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocationOnMock)
					throws Throwable {
				BindingResult errors = (BindingResult) invocationOnMock
						.getArguments()[2];
				errors.rejectValue("password", "error.mismatch");
				return Boolean.FALSE;
			}
		}).when(mockAccountService).registerAccount(any(Account.class),
				anyString(), any(BindingResult.class));

		this.mockMvc
				.perform(
						post("/registerusers/adduser")
								.param("username", "abc123")
								.param("password", "abc123")
								.param("confirmPassword", "abc123")
								.param("email", "abc@g.com")
								.param("firstName", "abc")
								.param("lastName", "abc")
								.param("marketingOk", "true")
								.param("_marketingOk", "on")
								.param("acceptTerms", "true")
								.param("_acceptTerms", "on")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("users/registrationForm"));

	}

	@Test
	public void thatAccountCreationRendersAsJson() throws Exception {
		
		//any of the below to somehow mock getId of the account class doesnot work.
		//we get account.getId method as null only. verification of the location information
		//can be done through integration test only using a rest template and a full fleged 
		//spring containter availability

		// Account account = new Account.Builder(null).build();

		// Account spyacnt = spy(account);

		// when(spyacnt.getId()).thenReturn(22L);
		// when(any(Account.class).getId()).thenReturn(22L);

		// doReturn(22).when(spyacnt).getId();
		// doReturn(22).when(any(Account.class)).getId();

		// Account a = Mockito.spy(new Account.Builder("abc").build());
		// doReturn(22L).when(a).getId();

		// Long a = new Long(22L);
		//
		// Account mock = mock(Account.class);
		//
		// doAnswer(new Answer<Long>() {
		// @Override
		// public Long answer(InvocationOnMock invocationOnMock) throws
		// Throwable {
		// return 22L;
		// }
		// }).when(any(Account.class)).getId();

		doAnswer(new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocationOnMock)
					throws Throwable {

				Account account = (Account) invocationOnMock.getArguments()[0];
				account.setEnabled(true);
				account.setFirstName("bca123");
				// below also doesnt work because of pass by value semantics.
				// Account spier = spy(account);
				// doReturn(new Long(22)).when(spier).getId();

				// spier.setFirstName("cba123");

				// account = spier;

				return Boolean.FALSE;
			}
			// }).when(mockAccountService).registerAccount(eq(spyacnt),
		}).when(mockAccountService).registerAccount(any(Account.class),
				anyString(), any(BindingResult.class));

		// logger.debug("calling getId on a {} ",a.getId());

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

}
