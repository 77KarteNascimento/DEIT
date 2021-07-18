package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Prazo;
import br.gov.deit.util.Msg;


@ViewScoped
@ManagedBean
public class PrazoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Prazo prazo = new Prazo();
	private List<Prazo> prazos;
	private DAO<Prazo> dao = new DAO<Prazo>(Prazo.class);
	private Long prazoId;
	public Boolean cadastro = true;
	
	public void carregaPrazo() {

		DAO<Prazo> dao = new DAO<Prazo>(Prazo.class);
		if (prazoId != null && prazoId != 0) {
			this.prazo = dao.buscaPorId(this.prazoId);
		}
	}
	
	public void grava() {
			if(this.prazo.getObra().getNome() == null){
				Msg.addMsgError("Preencha Corretamento o Campo Obra");
			} else {
				if (prazo.getId() == null && prazo.getAditivo1() == null) {
					Msg.addMsgInfo("Termo Aditivo 1, Prazo Estabelecido");
					dao.adiciona(prazo);
					this.prazo = new Prazo();
				} 
				if (prazo.getAditivo1() != null && prazo.getAditivo2() == null) {
					Msg.addMsgInfo("Termo Aditivo 2, Prazo Estabelecido");
					dao.atualiza(prazo);
					this.prazo = new Prazo();
				}
				if (prazo.getAditivo1() != null && prazo.getAditivo2() != null && prazo.getAditivo3() == null) {
					Msg.addMsgInfo("Termo Aditivo 3, Prazo Estabelecido");
					dao.atualiza(prazo);
					this.prazo = new Prazo();
			}
			}
		prazos = dao.listaTodos();
		this.cadastro = true;
	}
	
//	public void altera() {
//		if (empenho.getId() != null) {
//			Msg.addMsgInfo("Empenho Editada Com Sucesso");
//			dao.atualiza(empenho);
//			this.empenho = new Empenho();
//		}
//	}

	public List<Prazo> getPrazos() {
		if (prazos == null) {
			System.out.println("Carregando Prazo...");
			prazos = new DAO<Prazo>(Prazo.class).listaTodos();
		}
		return prazos;
	}
	
	/** Getters and Setters **/	

	public Boolean getCadastro() {
		return cadastro;
	}

	public Prazo getPrazo() {
		return prazo;
	}

	public void setPrazo(Prazo prazo) {
		this.prazo = prazo;
	}

	public void setPrazos(List<Prazo> prazos) {
		this.prazos = prazos;
	}

	public DAO<Prazo> getDao() {
		return dao;
	}

	public void setDao(DAO<Prazo> dao) {
		this.dao = dao;
	}

	public Long getPrazoId() {
		return prazoId;
	}

	public void setPrazoId(Long prazoId) {
		this.prazoId = prazoId;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}		
	
	
}
