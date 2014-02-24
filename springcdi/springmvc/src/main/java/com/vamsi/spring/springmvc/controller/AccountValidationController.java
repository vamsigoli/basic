package com.vamsi.spring.springmvc.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vamsi.spring.beans.Account;
import com.vamsi.spring.forms.AccountFormValidation;
import com.vamsi.spring.springmvc.service.AccountService;

@Controller
@RequestMapping("/registerusers")
public class AccountValidationController {
	private static final Logger log = LoggerFactory
			.getLogger(AccountValidationController.class);

	private static final String VN_REG_FORM = "users/registrationForm";
	private static final String VN_REG_OK = "redirect:registration_ok";
	
	@Autowired
	AccountService accountService;
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "username", "password",
				"confirmPassword", "firstName", "lastName", "email",
				"marketingOk", "acceptTerms" });
	}

	@RequestMapping(value = "newaccount", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		model.addAttribute("accountFormValidation", new AccountFormValidation());
		return VN_REG_FORM;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postRegistrationForm(
			@ModelAttribute("accountFormValidation") @Valid AccountFormValidation form,
			BindingResult result) {
		log.info("Created registration: {}", form);
		
		Account account = toAccount(form);
		
		accountService.registerAccount(account, form.getPassword(), result);
		
		convertPasswordError(result);
		return (result.hasErrors() ? VN_REG_FORM : VN_REG_OK);
	}
	
	private static Account toAccount(AccountFormValidation form) {
//		Account account = new Account();
//		account.setUsername(form.getUsername());
//		account.setFirstName(form.getFirstName());
//		account.setLastName(form.getLastName());
//		account.setEmail(form.getEmail());
//		account.setMarketingOk(form.isMarketingOk());
//		account.setAcceptTerms(form.getAcceptTerms());
//		account.setEnabled(true);
//		converted to builder pattern as below
//		the build method does constraint validations and throws a constraint violation
//		exception as done by @Valid annotation of spring mvc
		
		Account account = new Account.Builder(form.getLastName()).firstName(form.getFirstName())
								.userName(form.getUsername()).email(form.getEmail())
								.marketingOk(form.isMarketingOk()).acceptTerms(form.getAcceptTerms())
								.enabled(true).build();
		
		log.info("converted to entity: {}",account);
		
		return account;
		}

	private static void convertPasswordError(BindingResult result) {
		
		log.info("recieved call to convert");
		
		
		for (ObjectError error : result.getGlobalErrors()) {
			String msg = error.getDefaultMessage();
			
			log.info("error received is :" + msg);
			if ("account.password.mismatch.message".equals(msg)) {
				if (!result.hasFieldErrors("password")) {
					result.rejectValue("password", "error.mismatch");
		log.info("converted password error");
				}
			}
		}
		for (ObjectError error : result.getFieldErrors()) {
			String msg = error.getDefaultMessage();
			
			log.info("field error received is :" + msg);
			if ("account.password.mismatch.message".equals(msg)) {
				if (!result.hasFieldErrors("password")) {
					result.rejectValue("password", "error.mismatch");
		log.info("converted password error");
				}
			}
		}
		
		
	}

}