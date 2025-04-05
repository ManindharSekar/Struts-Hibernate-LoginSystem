package com.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;
import com.bean.EmployeeBean;
import com.hibernate.dao.EmployeeDao;
import com.hibernate.util.Account;
import com.hibernate.util.Employee;

public class FindAction extends Action {
	@Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        EmployeeBean empForm = (EmployeeBean) form;
        EmployeeDao empDao = new EmployeeDao();
        
    /*    if(empForm.getFind()!=null){*/
        	
        	String name = empForm.getName();
        	request.getSession().setAttribute("name", name);

        
        Account find = empDao.find(name);
        List<Employee> employees = empDao.getAllAccount(name);
        empForm.setEmployeeList(employees);
        
        request.setAttribute("employee", employees);
        
        if (find == null) {
            ActionErrors errors = new ActionErrors();
            errors.add("error.user.name.required", new ActionMessage("error.user.name.required"));
            saveErrors(request, errors);
            return mapping.findForward("findfailure");
        }
       
    /*    }*/
        
        return mapping.findForward("findsuccess");
    }




}

