package com.vamsi.spring.forms;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.ScriptAssert;


//instead of using scriptassert having java script and loosing out the strong typing,
//for crossfield validations use a @AssertTrue validation over a boolean returning method like isValidPassword below
//also @ScriptAssert is a global error while @AssertTrue below is a field error
//@ScriptAssert(message="account.password.mismatch.message",lang="javascript", script="_this.password.equals(_this.confirmPassword)")
@XmlRootElement
public class AccountFormValidation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username, password, confirmPassword, firstName, lastName,
			email;
	private boolean marketingOk = true;
	private boolean acceptTerms = false;
	private int version;
	private long Id;

	
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@NotNull
	@Size(min = 1, max = 50)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("username", username).append("firstName", firstName)
				.append("lastName", lastName).append("email", email)
				.append("marketingOk", marketingOk)
				.append("acceptTerms", acceptTerms).toString();
	}

	@NotNull
	@Size(min = 6, max = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	@AssertTrue(message="account.password.mismatch.message")
	private boolean getValidPassword() {
		return this.password.equals(this.confirmPassword);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	@NotNull
	@Size(min = 6, max = 50)
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMarketingOk() {
		return marketingOk;
	}

	public void setMarketingOk(boolean marketingOk) {
		this.marketingOk = marketingOk;
	}

	
	@AssertTrue(message = "{account.acceptTerms.assertTrue.message}")
	public boolean getAcceptTerms() {
		return acceptTerms;
	}

	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}
}