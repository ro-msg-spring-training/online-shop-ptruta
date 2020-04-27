package ro.msg.learning.shop;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
//		SessionFactory sessionFactory = HibernateAnnotation.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		System.out.println("Session created");
//
//		Transaction tx = session.beginTransaction();
//		tx.commit();
		SpringApplication.run(ShopApplication.class, args);
	}
}
