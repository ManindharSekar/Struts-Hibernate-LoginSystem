package com.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.util.Account;
import com.hibernate.util.Employee;
import com.hibernate.util.HibernateUtil;

public class EmployeeDao {
    
    public Account find(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Account WHERE name = :name");
        query.setParameter("name", name);

        Account account = (Account) query.uniqueResult();
        session.close();
        return account;
    }
    
    
    public List<Employee> getAllAccount(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employee = null;
        try {
            Query query = session.createQuery("from Employee WHERE account.name= :name");
            query.setParameter("name", name);
            employee = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employee;
    }
    

        public int saveEmployee(Employee employee, Account account) {
            // Logic to save employee to the database
            // Example: using Hibernate or JDBC to persist employee data
            try {
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(employee);
                session.getTransaction().commit();
                session.close();
                return 1;  // Return success
            } catch (Exception e) {
                e.printStackTrace();
                return 0;  // Return failure
            }
        }

    
    public int saveEmp( String address, String city, String state, int pincode, Account name) {
        Employee emp = new Employee();
        
        emp.setAddress(address);
        emp.setCity(city);
        emp.setState(state);
        emp.setPincode(pincode);
        emp.setAccount(name);  

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.save(emp);  

            transaction.commit();

            return 1;  

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return 0;  

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Employee findEmployeeById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee employee = null;
        try {
            employee = (Employee) session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }

    public void updateEmployee(Employee employee) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.update(employee);  

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteEmployee(int id) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Employee employee = (Employee) session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    public void saveEmployee(Employee employee) {
    	 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	 Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(employee); 
        transaction.commit();   

        session.close();
    }
    public List<Employee> getAllEmployees() {
    	 SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    	 Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

       
        Query query = session.createQuery("from Employee");

       
        List<Employee> employees = query.list();

        transaction.commit();
        session.close();

        return employees;
    }
}
   