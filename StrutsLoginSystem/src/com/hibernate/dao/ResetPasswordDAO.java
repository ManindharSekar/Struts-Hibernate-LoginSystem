package com.hibernate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import com.hibernate.util.HibernateUtil;
import com.hibernate.util.Account;

public class ResetPasswordDAO {

	public boolean findAccount(String userName, String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean accountExists = false;

		try {
			transaction = session.beginTransaction();

			String hql = "FROM Account a WHERE a.userName = :userName AND a.email = :email";
			Query query = session.createQuery(hql);
			query.setParameter("userName", userName);
			query.setParameter("email", email);

			List result = query.list();

			if (!result.isEmpty()) {
				accountExists = true;
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();

			return accountExists;
		}
	}

	public void updateToken(String token, String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		try {
			Account account = (Account) session
					.createQuery("FROM Account WHERE email = :email")
					.setParameter("email", email).uniqueResult();

			if (account != null) {
				account.setResetPasswordToken(token);
				session.update(account);
				transaction.commit();
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public boolean update(String email, String newPassword) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		boolean isUpdated = false;

		try {
			transaction = session.beginTransaction();

			String hql = "FROM Account a WHERE a.email = :email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);

			Account account = (Account) query.uniqueResult();

			if (account != null) {
				account.setPassword(newPassword);
				session.update(account);
				transaction.commit();
				isUpdated = true;
			} else {
				System.out.println("Account with email " + email
						+ " not found.");
			}

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return isUpdated;
	}

}
