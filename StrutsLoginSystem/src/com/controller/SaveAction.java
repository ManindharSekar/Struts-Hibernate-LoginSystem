package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.bean.EmployeeBean;
import com.hibernate.dao.EmployeeDao;
import com.hibernate.util.Account;
import com.hibernate.util.Employee;

public class SaveAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	System.out.println("----test-----");
        EmployeeBean empForm = (EmployeeBean) form;

        EmployeeDao empDao = new EmployeeDao();
        String name = (String) request.getSession().getAttribute("name");

        Account account = empDao.find(name);
        
        if (account == null) {
            System.out.println("Account not found for user: " + name);
           // return mapping.findForward("failure");
        }

        List<Employee> employees = empForm.getEmployeeList();
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees in the list to process.");
           // return mapping.findForward("failure");
        }

     //   for (Employee emp : employees) {
            String address = empForm.getAddress();
            String city = empForm.getCity();
            String state = empForm.getState();
            int pincode = empForm.getPincode();

            System.out.println(", Address: " + address + ", City: " + city + ", State: " + state + ", Pincode: " + pincode);

            try {
                int result = empDao.saveEmp(address, city, state, pincode, account);
                if (result != 1) {
                //    System.out.println("Failed to save employee: " + emp.getId());
                    return mapping.findForward("failure");
                }
            } catch (Exception e) {
              //  System.out.println("Error saving employee: " + emp.getId());
                e.printStackTrace();
                return mapping.findForward("failure");
            }
    //    }

        return mapping.findForward("savesuccess");
    }
}
