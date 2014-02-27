package com.vamsi.spring.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//bean validation can happen on the jpa classes as well to make the system more robust
//currently, i have kept the validations on the spring form bean
//but the builder i have checks for any validations to happen and raise error accordingly

@NamedQuery(name = "findAccountByUsername", query = "from Account where username = :username")
@Entity
public class Account {

	@SequenceGenerator(name = "ACCOUNT_ID_GENERATOR", sequenceName = "ACC_SEQ", initialValue = 10, allocationSize = 1)
	@Id
	@GeneratedValue(generator = "ACCOUNT_ID_GENERATOR")
	Long id;

	private Account(Builder builder) {

		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.acceptTerms = builder.acceptTerms;
		this.marketingOk = builder.marketingOk;
		this.email = builder.email;
		this.username = builder.userName;
		this.enabled = builder.enabled;
		
	}
	
	//jpa needs an default constructor for instantiation. hibernate is ok with private. but others may need protected.
	//http://paulszulc.wordpress.com/2010/01/04/a-proper-way-for-jpa-entities-instantiation/
	
	private Account() {}

	public Long getId() { return id; }

	@SuppressWarnings("unused")
	private void setId(Long id) { this.id = id; }

	private String username;

	@Column(name = "first_name") private String firstName;

	@Column(name = "last_name") private String lastName;
	
	String email;
	
	@Column(name = "marketing_ok")
	@Convert(converter = BooleanYNConverter.class) private boolean marketingOk = true;

	@Column(name = "accept_terms")
	@Convert(converter = BooleanYNConverter.class) private boolean acceptTerms = false;

	@Convert(converter = BooleanYNConverter.class) private boolean enabled = true;

	@Column(name = "date_created") private Date dateCreated;

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public boolean isMarketingOk() { return marketingOk; }

	public void setMarketingOk(boolean marketingOk) { this.marketingOk = marketingOk; }

	public boolean isAcceptTerms() { return acceptTerms; }

	public void setAcceptTerms(boolean acceptTerms) { this.acceptTerms = acceptTerms; }

	public boolean isEnabled() { return enabled; }

	public void setEnabled(boolean enabled) { this.enabled = enabled; }

	public Date getDateCreated() { return dateCreated; }

	public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("username", username).append("firstName", firstName)
				.append("lastName", lastName).append("email", email)
				.append("marketingOk", marketingOk)
				.append("acceptTerms", acceptTerms).toString();
	}

	
	public static class Builder extends AbstractBuilder<Account> {
		private String firstName;
		private String lastName;
		private String email;
		private boolean marketingOk;
		private boolean acceptTerms;
		private boolean enabled;
		private String userName;
		
		public Builder(String lastName) { this.lastName = lastName; }
		
		public Builder firstName(String firstName) {this.firstName = firstName; return this;}
		
		public Builder email(String email) {this.email = email; return this;}
		
		public Builder marketingOk(boolean marketingOk) {this.marketingOk = marketingOk; return this;}
		
		public Builder acceptTerms(boolean acceptTerms) { this.acceptTerms = acceptTerms;return this; }
		
		public Builder userName(String userName) {this.userName = userName; return this;}
		
		public Builder enabled(boolean enabled) {this.enabled = enabled; return this;}
		
		@Override protected Account buildInternal() { return new Account(this); }
	}
}
