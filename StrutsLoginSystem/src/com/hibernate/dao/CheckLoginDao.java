package com.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import com.hibernate.util.Account;
import com.hibernate.util.HibernateUtil;

public class CheckLoginDao {

	public boolean checkLogin(String userName, String password) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Account WHERE userName = :i AND password = :j";

			Query query = session.createQuery(hql);
			query.setParameter("i", userName);
			query.setParameter("j", password);

			List results = query.list();

			if (results.isEmpty()) {
				return false;
			}

			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public boolean checkLoginForgot(String userName) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			String hql = "FROM Account WHERE userName = :i";

			Query query = session.createQuery(hql);
			query.setParameter("i", userName);

			List results = query.list();

			if (results.isEmpty()) {
				return false;
			}

			return true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public String getUserEmail(String userName) {

		String email = null;
		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Transaction transaction = session.beginTransaction();

			String hql = "SELECT a.email FROM Account a WHERE a.userName = :userName";
			Query query = session.createQuery(hql);
			query.setParameter("userName", userName);

			email = (String) query.uniqueResult();

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return email;

	}
}
