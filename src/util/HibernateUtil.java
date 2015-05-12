package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	private static EntityManagerFactory FACTORY = 
			Persistence.createEntityManagerFactory("ClinicaMedica");

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
}
