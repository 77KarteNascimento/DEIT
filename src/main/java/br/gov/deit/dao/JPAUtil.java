package br.gov.deit.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("deit");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}

