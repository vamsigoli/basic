package com.vamsi.spring.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vamsi.spring.forms.AccountForm;

@Controller
@RequestMapping("/users")
public class AccountController {
	private static final Logger log = LoggerFactory
			.getLogger(AccountController.class);
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	binder.setAllowedFields(new String[] {
	"username", "password", "confirmPassword", "firstName",
	"lastName", "email", "marketingOk", "acceptTerms" });
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String getRegistrationForm(Model model) {
		model.addAttribute("account", new AccountForm());
		return "users/registrationForm";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postRegistrationForm(AccountForm form) {
		log.info("Created registration: {}", form);
		return "redirect:registration_ok";
	}
}