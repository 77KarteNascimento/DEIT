package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Empenho;
import br.gov.deit.util.Msg;


@ViewScoped
@ManagedBean
public class EmpenhoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Empenho empenho = new Empenho();
	private List<Empenho> empenhos;
	private DAO<Empenho> dao = new DAO<Empenho>(Empenho.class);
	private Long empenhoId;
	public Boolean cadastro = true;
	
	public void carregaEmpresa() {

		DAO<Empenho> dao = new DAO<Empenho>(Empenho.class);
		if (empenhoId != null && empenhoId != 0) {
			this.empenho = dao.buscaPorId(this.empenhoId);
		}
	}
	
	public void grava() {
		for (Empenho empenhos : this.getEmpenhos()) {
			if(empenhos.getNumero().equals(this.getEmpenho().getNumero())) {
				this.cadastro = false;
				break;
			}
		}
		if(this.cadastro == true){
			if(this.empenho.getNumero() == null){
				Msg.addMsgError("Preencha Corretamento o Campo Número");
			} else {
				if (empenho.getId() == null) {
					Msg.addMsgInfo("Empenho Cadastrada Com Sucesso");
					dao.adiciona(empenho);
					this.empenho = new Empenho();
				} else {
					Msg.addMsgInfo("Atualizalçao Realizada Com Sucesso");
					dao.atualiza(empenho);
					this.empenho = new Empenho();
				}
			}
		} else {
			Msg.addMsgError("Este Emepenho já foi cadastrado.");
		}
		empenhos = dao.listaTodos();
		this.cadastro = true;
	}
	
	public void altera() {
		if (empenho.getId() != null) {
			Msg.addMsgInfo("Alteração Realizada Com Sucesso");
			dao.atualiza(empenho);
			this.empenho = new Empenho();
		}
	}

	public List<Empenho> getEmpenhos() {
		if (empenhos == null) {
			System.out.println("Carregando Empresas...");
			empenhos = new DAO<Empenho>(Empenho.class).listaTodos();
		}
		return empenhos;
	}
	
	/** Getters and Setters **/

	

	public DAO<Empenho> getDao() {
		return dao;
	}

	public Empenho getEmpenho() {
		return empenho;
	}

	public void setEmpenho(Empenho empenho) {
		this.empenho = empenho;
	}

	public void setEmpenhos(List<Empenho> empenhos) {
		this.empenhos = empenhos;
	}

	public void setDao(DAO<Empenho> dao) {
		this.dao = dao;
	}

	public Long getEmpenhoId() {
		return empenhoId;
	}

	public void setEmpenhoId(Long empenhoId) {
		this.empenhoId = empenhoId;
	}

	public Boolean getCadastro() {
		return cadastro;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}		
	
	
}
