package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bean.CreateAccount;
import com.hibernate.dao.CreateAccountDao;
import com.hibernate.util.Account;

public class ActivateActionController extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreateAccountDao createAccountDao = new CreateAccountDao();
		String token = request.getParameter("token");
		if (token != null && !token.isEmpty()) {
			Account createAccount = createAccountDao.findByToken(token);
			if (createAccount != null && !createAccount.isActivated()) {
				createAccount.setActivated(true);
				createAccountDao.updateUser(createAccount);
				return mapping.findForward("activateSuccess");
			} else {
				return mapping.findForward("activateFailure");
			}
		}
		// TODO Auto-generated method stub
		return mapping.findForward("activateFailure");
	}

}
