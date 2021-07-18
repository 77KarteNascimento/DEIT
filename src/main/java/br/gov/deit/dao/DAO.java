package br.gov.deit.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class DAO<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Class<T> classe;
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getClasse() {
		return classe;
	}

	public DAO(Class<T> classe) {
		this.classe = classe;
	}
	
	

	public void adiciona(T t) {
		//consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();
		//abre a transação
		em.getTransaction().begin();

		//persiste o objeto
		em.persist(t);

		//commita a transação
		em.getTransaction().commit();

		//fecha a entity manager
		em.close();
	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}

	public void atualiza(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	public List<T> listaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).getResultList();

		em.close();
		return lista;
	}
	
	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> lista = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return lista;
	}

	public T buscaPorId(Long id) {
		EntityManager em = new JPAUtil().getEntityManager();
		return (T) em.find(classe, id);
	}
	
	public int contaTodos() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from NotaFiscal n").getSingleResult();
		em.close();
		
		return (int) result;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllByName(String field, String name) {
		String querySelect = "SELECT obj FROM " + classe.getSimpleName() + " obj WHERE upper(" + field + ") like upper('%" + name + "%')";
		Query query = getEntityManager().createQuery(querySelect);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String querySelect = "SELECT obj FROM "
				+ classe.getSimpleName() + " obj";
		Query query = getEntityManager().createQuery(querySelect);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllOrder(String field) {
		String querySelect = "SELECT obj FROM "
				+ classe.getSimpleName() + " obj ORDER BY " + field;
		Query query = getEntityManager().createQuery(querySelect);
		return query.getResultList();
	}
}
