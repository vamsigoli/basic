package com.vamsi.spring.springmvc.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vamsi.spring.beans.Account;
import com.vamsi.spring.forms.AccountFormValidation;
import com.vamsi.spring.springmvc.service.AccountService;

@Controller
@RequestMapping("/updateusers")
@SessionAttributes({ "account" }) // read explanation of session attributes in our notes. this is not same as http session
public class UpdateController {
	private static final Logger log = LoggerFactory
			.getLogger(AccountValidationController.class);

	private static final String VN_UPD_FORM = "users/updateUserForm";
	private static final String VN_UPD_OK = "redirect:main";

	@Autowired
	AccountService accountService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] { "password", "confirmPassword",
				"firstName", "lastName", "email", "marketingOk", "acceptTerms" });
	}

	@RequestMapping(value = "getaccount", method = RequestMethod.GET)
	public String getRegistrationForm(Model model, Principal principal) {

		final String currentUser = principal.getName();

		Account account = accountService.getAccountDetails(currentUser);

		model.addAttribute("accountFormValidation", fromAccount(account));
		model.addAttribute("account", account);
		return VN_UPD_FORM;
	}

	private AccountFormValidation fromAccount(Account account) {

		AccountFormValidation form = new AccountFormValidation();

		form.setAcceptTerms(account.isAcceptTerms());
		form.setEmail(account.getEmail());
		form.setFirstName(account.getFirstName());
		form.setLastName(account.getLastName());
		form.setMarketingOk(account.isMarketingOk());
		form.setUsername(account.getUsername());

		log.info("converted to form: {}", form);

		return form;

	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public String postRegistrationForm(
			@ModelAttribute("accountFormValidation") @Valid AccountFormValidation form,
			BindingResult result, Principal principal,
			@ModelAttribute("account") Account account,
			final RedirectAttributes redirectAttributes) {
		// log.info("Created registration: {}", form);
		//
		// Account account = toAccount(form);
		//
		// boolean registerResult = accountService.registerAccount(account,
		// form.getPassword(), result);
		//
		// log.info("result of registerAccount " + registerResult);
		//
		// convertPasswordError(result);
		//
		// redirectAttributes.addFlashAttribute("account", account);
		// calling the rest method to not repeat the logic. based on
		// http://spring.io/blog/2013/05/11/content-negotiation-using-spring-mvc
		// we need to have the @Valid annotation though in the method signature
		// at form level as well as it is invoked only by the container.
		// our individual call to the rest interface below doesnot invoke the
		// validation features
		log.debug("received regular registration method");

		if (!form.getUsername().equals(principal.getName())) {
			result.rejectValue("username", "username.forged");
			log.info("username forged");

		}

		updateAccountRest(form, result, account);

		redirectAttributes.addFlashAttribute("accountValidationForm", form);
		redirectAttributes.addFlashAttribute("actionmessage",
				"Update Successful");

		return (result.hasErrors() ? VN_UPD_FORM : VN_UPD_OK);
	}

	@RequestMapping(value = "/updaterest", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody
	Account updateAccountRest(@RequestBody @Valid AccountFormValidation form,
			BindingResult result, @ModelAttribute("account") Account account) {
		log.info("Created registration: {}", form);

		// Account account = toAccount(form);
		toAccount(form, account);

		boolean registerResult = accountService.updateAccount(account,
				form.getPassword(), result);

		log.info("result of updateAccount " + registerResult + " "
				+ account.getId());

		convertPasswordError(result);

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

			log.info("field error received is :" + msg + " for field "
					+ error.toString());
			if ("account.password.mismatch.message".equals(msg)) {
				if (!result.hasFieldErrors("password")) {
					result.rejectValue("password", "error.mismatch");
					log.info("converted password error");
				}
			}
		}

	}

	private static Account toAccount(AccountFormValidation form, Account account) {
		account.setFirstName(form.getFirstName());
		account.setLastName(form.getLastName());
		account.setEmail(form.getEmail());
		account.setMarketingOk(form.isMarketingOk());
		account.setAcceptTerms(form.getAcceptTerms());
		account.setEnabled(true);
		// converted to builder pattern as below
		// the build method does constraint validations and throws a constraint
		// violation
		// exception as done by @Valid annotation of spring mvc

		// Account account = new
		// Account.Builder(form.getLastName()).firstName(form.getFirstName())
		// .userName(form.getUsername()).email(form.getEmail())
		// .marketingOk(form.isMarketingOk()).acceptTerms(form.getAcceptTerms())
		// .enabled(true).role("ROLE_USER").build();
		//
		log.info("converted to entity: {}", account);

		return account;
	}
}