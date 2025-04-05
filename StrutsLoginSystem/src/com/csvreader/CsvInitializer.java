package com.csvreader;

import java.io.FileReader;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.util.Account;
import com.hibernate.util.HibernateUtil;
import com.opencsv.CSVReader;

public class CsvInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			CSVReader readCsv = new CSVReader(new FileReader(
					"C:/Users/Manju/Documents/My Notes/Book2.csv"));
			List<String[]> records = readCsv.readAll();

			for (int i = 1; i < records.size(); i++) {
				String[] record = records.get(i);
				String email = record[1];
				String name = record[2];
				String city = record[3];
				int phone = Integer.parseInt(record[4]);
				String userName = record[5];
				String password = record[6];
				String activation = record[7];
				boolean activated = "1".equals(record[8]);

				Account account = new Account();
				account.setEmail(email);
				account.setName(name);
				account.setCity(city);
				account.setPhone(phone);
				account.setUserName(userName);
				account.setPassword(password);
				account.setActivationToken(activation);
				account.setActivated(activated);
				session.save(account);

			}

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		HibernateUtil.getSessionFactory().close();

	}

}
