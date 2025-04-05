package com.bean;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class LogIn extends ActionForm {
	
	private String userName;
	private String password;
	private String submitButton;
	private String createAccountButton;
	private String forgotPassword;
	
	
	public String getSubmitButton() {
		return submitButton;
	}
	public void setSubmitButton(String submitButton) {
		this.submitButton = submitButton;
	}
	public String getCreateAccountButton() {
		return createAccountButton;
	}
	public void setCreateAccountButton(String createAccountButton) {
		this.createAccountButton = createAccountButton;
	}

	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getForgotPassword() {
		return forgotPassword;
	}
	public void setForgotPassword(String forgotPassword) {
		this.forgotPassword = forgotPassword;
	}



	}


