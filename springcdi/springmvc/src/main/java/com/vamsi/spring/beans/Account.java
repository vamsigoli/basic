package com.vamsi.spring.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

@NamedQuery(
name = "findAccountByUsername",
query = "from Account where username = :username")

@Entity
public class Account {
	
	
	
	@SequenceGenerator(name = "ACCOUNT_ID_GENERATOR", 
			sequenceName = "ACC_SEQ" ,initialValue=10 ,allocationSize=1)
	@Id
	@GeneratedValue(generator = "ACCOUNT_ID_GENERATOR")
	Long id;
	
	
	public Long getId() {
		return id;
	}
@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	private String username;
	
	@Column( name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	String email;
	@Column(name="marketing_ok")
	private boolean marketingOk = true;
	@Column(name="accept_terms")
	private boolean acceptTerms = false;
	private boolean enabled = true;
	@Column(name="date_created")
	private Date dateCreated;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public boolean isAcceptTerms() {
		return acceptTerms;
	}
	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}
