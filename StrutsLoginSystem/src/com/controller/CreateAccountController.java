package com.controller;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bean.CreateAccount;
import com.hibernate.dao.CreateAccountDao;
import com.hibernate.util.Account;
import com.mail.sender.MailSender;

public class CreateAccountController extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreateAccount createAccountForm=(CreateAccount)form;
		String name=createAccountForm.getName();
		String city = createAccountForm.getCity();
		int phone = createAccountForm.getPhone(); 
		String userName = createAccountForm.getUserName();
		String email = createAccountForm.getEmail();
		String password = createAccountForm.getPassword();
		String conformPassword = createAccountForm.getConfirmPassword();
		
		 request.setAttribute("userName", userName);
		
		String token=UUID.randomUUID().toString();
		String activationLink="http://localhost:8080/UserLoginForm/active.do?token="+token;
		String subject="Activate Your Account";
		MailSender mailSender=new MailSender();
		mailSender.sendActivationEmail(createAccountForm.getEmail(),activationLink,subject);
		
		CreateAccountDao createAccountDao=new CreateAccountDao();
		
		int i = createAccountDao.saveAccount(name,city,phone,userName,email,password,conformPassword,token);
		
		
		
		if(i==0){
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}
	
	
}
