package com.vamsi.test.springmvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;

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

import com.vamsi.spring.beans.Account;
import com.vamsi.spring.springmvc.controller.AccountValidationController;
import com.vamsi.spring.springmvc.service.AccountService;

public class AccountValidationControllerTest {

	@Mock
	private AccountService mockAccountService;

	@InjectMocks
	private AccountValidationController controller;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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

		this.mockMvc
				.perform(
						post("/registerusers").param("username", "abc123")
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
				.andExpect(flash().attributeExists("account"));
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
						post("/registerusers").param("username", "abc123")
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

}
