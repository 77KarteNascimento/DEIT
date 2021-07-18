package br.gov.deit.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.deit.entity.Funcionario;

public class UsuarioDAO {
	
	public Funcionario existe(Funcionario funcionario) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from Funcionario u where u.matricula = " + ":pMatricula and u.senha = :pSenha");
		query.setParameter("pMatricula", funcionario.getMatricula());
		query.setParameter("pSenha", funcionario.getSenha());
		
		Funcionario funcLogin = new Funcionario();
		if(!query.getResultList().isEmpty()){
			funcLogin = (Funcionario) query.getSingleResult();
			if(funcLogin.getStatus().equalsIgnoreCase("Ativado")){
				System.out.println("Funcionário Ativado");
			} else if(funcLogin.getStatus().equalsIgnoreCase("Em Espera")) {
				funcLogin = null;
				System.out.println("Funcionário Em Espera");
			} else if(funcLogin.getStatus().equalsIgnoreCase("Desativado")){
				System.out.println("Funcionário Desativado");
				funcLogin = null;
			}
		} else {
			funcLogin = null;
		}
		
		em.getTransaction().commit();
		em.close();
		
		return funcLogin;
	}
	
	public Funcionario esqueciSenha(Funcionario funcionario){
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("from Funcionario u where u.matricula = " + ":pMatricula and u.campo_secreto = :pSecreto");
		query.setParameter("pMatricula", funcionario.getMatricula());
		
		Funcionario funcLogin = new Funcionario();
		if(!query.getResultList().isEmpty()){
			funcLogin = (Funcionario) query.getSingleResult();
		} else {
			funcLogin = null;
			// Ver o que realizar aqui
		}
		
		em.getTransaction().commit();
		em.close();
		
		return funcLogin;
	}

}
