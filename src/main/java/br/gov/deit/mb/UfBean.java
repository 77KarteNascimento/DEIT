package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.converter.BaseEntity;
import br.gov.deit.dao.DAO;
import br.gov.deit.entity.UF;
import br.gov.deit.util.Msg;

@ViewScoped
@ManagedBean
public class UfBean implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private UF uf = new UF();
	private List<UF> unidades;
	private DAO<UF> dao = new DAO<UF>(UF.class);
	private Long ufId;
	
	public void carregaUf() {

		DAO<UF> dao = new DAO<UF>(UF.class);
		if (ufId != null && ufId != 0) {
			this.uf = dao.buscaPorId(this.ufId);
		}
	}
	
	public void grava() {
		if (uf.getId() == null) {
			Msg.addMsgInfo("Unidade da Federacao Cadastrado Com Sucesso!");
			dao.adiciona(uf);
		} else {
			Msg.addMsgInfo("Alteracao Realizada Com Sucesso!");
			dao.atualiza(uf);
		}
		unidades = dao.listaTodos();
		 this.uf = new UF();
	}
	
	public List<UF> getUFS() {
		if (unidades == null) {
			System.out.println("Carregando UFS...");
			unidades = new DAO<UF>(UF.class).listaTodos();
		}
		return unidades;
	}


	/** Getters and Setters **/
	
	public UF getUf() {
		return uf;
	}


	public void setUf(UF uf) {
		this.uf = uf;
	}

	public List<UF> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<UF> unidades) {
		this.unidades = unidades;
	}

	public DAO<UF> getDao() {
		return dao;
	}


	public void setDao(DAO<UF> dao) {
		this.dao = dao;
	}


	public Long getUfId() {
		return ufId;
	}


	public void setUfId(Long ufId) {
		this.ufId = ufId;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
