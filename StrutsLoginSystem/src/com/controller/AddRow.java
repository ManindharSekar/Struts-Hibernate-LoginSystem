package com.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bean.EmployeeBean;
import com.hibernate.util.Employee;

public class AddRow extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EmployeeBean empForm = (EmployeeBean) form;

        if (empForm.getEmployeeList() == null) {
            empForm.setEmployeeList(new ArrayList<Employee>());
        }

        Employee newEmployee = new Employee();
     

        empForm.getEmployeeList().add(newEmployee);

        request.getSession().setAttribute("employeeForm", empForm);

        return mapping.findForward("success");
    }
}
