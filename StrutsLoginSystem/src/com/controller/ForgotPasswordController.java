package com.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bean.ForgotPassword;
import com.bean.LogIn;
import com.hibernate.dao.CheckLoginDao;
import com.hibernate.dao.ResetPasswordDAO;
import com.mail.sender.MailSender;

public class ForgotPasswordController extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ForgotPassword forgotPasswordForm = (ForgotPassword) form;

		String email = forgotPasswordForm.getEmail();
		String userName = forgotPasswordForm.getUserName();
		
		request.setAttribute("email",email);

		request.getSession().setAttribute("email", email);
		ResetPasswordDAO resetPasswordDAO = new ResetPasswordDAO();
		boolean findAccount = resetPasswordDAO.findAccount(userName, email);

		if (findAccount == true) {

			String resetToken = UUID.randomUUID().toString();
			String resetPasswordLink = "http://localhost:8080/UserLoginForm/resetPassword.do?resetToken="
					+ resetToken;
			String subject = "Reset Your Password";

			resetPasswordDAO.updateToken(resetToken, email);

			MailSender mailSender = new MailSender();
			mailSender.sendActivationEmail(email, resetPasswordLink, subject);
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add("forgotError", new ActionMessage("error.forgot.invalid"));
			saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	}

}
