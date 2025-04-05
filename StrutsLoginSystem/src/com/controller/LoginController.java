package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bean.LogIn;
import com.hibernate.dao.CheckLoginDao;
import com.hibernate.dao.CreateAccountDao;
import com.hibernate.util.Account;

public class LoginController extends Action {
	// private static final Logger log =
	// LogManager.getLogger(LoginController.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		LogIn loginForm = (LogIn) form;
		// log.info("Submit Button: " + userInfo.getSubmitButton());
		// log.info("Create Account Button: " +
		// userInfo.getCreateAccountButton());

		String userName = loginForm.getUserName();
		String password = loginForm.getPassword();
		CheckLoginDao checkLoginDao = new CheckLoginDao();
		boolean isLoginValid = checkLoginDao.checkLogin(userName, password);
		CreateAccountDao createAccountDao = new CreateAccountDao();

		List<Account> accounts = createAccountDao.getAllAccount();
		request.setAttribute("accounts", accounts);

		request.getSession().setAttribute("userName", userName);

		if (loginForm.getForgotPassword() != null) {

			return mapping.findForward("forgotPassword");

		}

		if (loginForm.getCreateAccountButton() != null) {
			return mapping.findForward("createAccount");
		}

		if (loginForm.getSubmitButton() != null) {

			if (isLoginValid) {

				if (createAccountDao.isActivated(userName, password))

					return mapping.findForward("success");

			} else {
				ActionErrors errors = new ActionErrors();
				errors.add("login", new ActionMessage("error.login.invalid"));
				saveErrors(request, errors);
				return mapping.findForward("failure");
			}
		}

		return mapping.findForward("failure");

	}

}
