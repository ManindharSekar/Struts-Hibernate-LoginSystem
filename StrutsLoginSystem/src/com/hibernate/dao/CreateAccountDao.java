package com.hibernate.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.CreateAccount;
import com.controller.CreateAccountController;
import com.hibernate.util.Account;
import com.hibernate.util.HibernateUtil;

public class CreateAccountDao {

	public int saveAccount(String name, String city, int phone,
			String userName, String email, String password,
			String conformPassword, String token) {
		Account account = new Account();
		account.setName(name);
		account.setCity(city);
		account.setPhone(phone);
		account.setUserName(userName);
		account.setEmail(email);
		account.setPassword(password);
		account.setConformPassword(conformPassword);
		account.setActivated(false);
		account.setActivationToken(token);
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		Transaction transaction = null;

		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			session.save(account);

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

	public Account findByToken(String token) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session
				.createQuery("FROM Account WHERE activationToken = :token");
		query.setParameter("token", token);

		Account account = (Account) query.uniqueResult();
		session.close();
		return account;
	}

	public boolean isActivated(String userName, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "SELECT activated FROM Account WHERE userName = :userName and password = :password";
			Query query = session.createQuery(hql);
			query.setParameter("userName", userName);
			query.setParameter("password", password);

			Boolean activated = (Boolean) query.uniqueResult();

			if (activated != null && activated) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return false;
	}

	public void updateUser(Account account) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.update(account);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public List<Account> getAllAccount() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Account> accounts = null;
		try {
			Query query = session.createQuery("from Account");
			accounts = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return accounts;
	}
}
