package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.bean.EmployeeBean;
import com.hibernate.dao.EmployeeDao;
import com.hibernate.util.Employee;

public class UpdateAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EmployeeBean empForm = (EmployeeBean) form;
        EmployeeDao empDao = new EmployeeDao();

        int id = Integer.parseInt(request.getParameter("id"));
        String address = empForm.getAddress();
        String city = empForm.getCity();
        String state = empForm.getState();
        int pincode = empForm.getPincode();

        Employee employee = empDao.findEmployeeById(id);

        if (employee != null) {
            employee.setAddress(address);
            employee.setCity(city);
            employee.setState(state);
            employee.setPincode(pincode);

            empDao.updateEmployee(employee);
            return mapping.findForward("updatesuccess");
        } else {
            ActionErrors errors = new ActionErrors();
            errors.add("id", new ActionMessage("error.id.notfound"));
            saveErrors(request, errors);
            return mapping.findForward("updatefailure");
        }
    }
}

