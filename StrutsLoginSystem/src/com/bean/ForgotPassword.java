package com.bean;

import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorForm;

public class ForgotPassword extends ValidatorForm {

	private String email;
	private String userName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
