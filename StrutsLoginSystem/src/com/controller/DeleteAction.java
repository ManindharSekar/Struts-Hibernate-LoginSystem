package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hibernate.dao.EmployeeDao;

public class DeleteAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EmployeeDao empDao = new EmployeeDao();

        
            int id = Integer.parseInt(request.getParameter("id"));
            
            if(id!=0){
            empDao.deleteEmployee(id);  
            return mapping.findForward("deletesuccess");  
            }
			return mapping.findForward("failure");
     
    }
}
