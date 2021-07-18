package br.gov.deit.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.deit.entity.TipoDocumento;

public class DocumentoDAO {

	public TipoDocumento pegaCarta() {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from TipoDocumento t where t.nome = " + ":pNome");
		query.setParameter("pNome", "Carta");
		
		TipoDocumento tipoDocumento = new TipoDocumento();
		if(!query.getResultList().isEmpty()){
			tipoDocumento = (TipoDocumento) query.getSingleResult();
		}
		
		return tipoDocumento;
		
	}
}
