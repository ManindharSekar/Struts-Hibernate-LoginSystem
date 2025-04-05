package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bean.ForgotPassword;
import com.bean.ResetPassword;
import com.hibernate.dao.ResetPasswordDAO;
import com.hibernate.util.Account;

public class ResetPasswordActionController extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String email = (String) request.getSession().getAttribute("email");

		ResetPassword resetPassword = (ResetPassword) form;
		String newPassword = resetPassword.getNewPassword();
		ResetPasswordDAO resetPasswordDao = new ResetPasswordDAO();
		boolean update = resetPasswordDao.update(email, newPassword);

		if (update) {
			return mapping.findForward("resetSuccess");
		}

		return mapping.findForward("resetFailure");
	}
}
