package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Medicao;
import br.gov.deit.util.Msg;


@ViewScoped
@ManagedBean
public class MedicaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Medicao medicao = new Medicao();
	private List<Medicao> medicoes;
	private DAO<Medicao> dao = new DAO<Medicao>(Medicao.class);
	private Long medicaoId;
	public Boolean cadastro = true;
	
	public void carregaMedicao() {

		DAO<Medicao> dao = new DAO<Medicao>(Medicao.class);
		if (medicaoId != null && medicaoId != 0) {
			this.medicao = dao.buscaPorId(this.medicaoId);
		}
	}
	
	public void grava() {
		if (medicao.getId() == null) {
			Msg.addMsgInfo("Cadastro Realizado Com Sucesso");
			dao.adiciona(medicao);
			
		} else {

		}
	}
	
//	public void grava() {
//		for (Medicao medicoes : this.getMedicoes()) {
//			if(medicoes.get().equals(this.getMedicao().getNumero()) 
//						&& Medicaos.getTipo().getNome().equalsIgnoreCase(this.getMedicao().getTipo().getNome())
//						&& Medicaos.getAno().equals(this.getMedicao().getAno())){
//				this.cadastro = false;
//				break;
//			}
//		}
//		if(this.cadastro == true){
//			if(this.Medicao.getNumero() == null){
//				Msg.addMsgError("Preencha Corretamento o Campo Número");
//			} else {
//				if (Medicao.getId() == null) {
//					Msg.addMsgInfo("Medicao Cadastrado Com Sucesso");
//					dao.adiciona(Medicao);
//					this.Medicao = new Medicao();
//				} else {
//					Msg.addMsgInfo("Medicao Recebido");
//					dao.atualiza(Medicao);
//					this.Medicao = new Medicao();
//				}
//			}
//		} else {
//			Msg.addMsgError("Este Medicao já foi cadastrado.");
//		}
//		Medicaos = dao.listaTodos();
//		this.cadastro = true;
//	}
	
	public void altera() {
		if (medicao.getId() != null) {
			Msg.addMsgInfo("Medicao Alterada Com Sucesso");
			dao.atualiza(medicao);
			this.medicao = new Medicao();
		}
	}

	public List<Medicao> getMedicaos() {
		if (medicoes == null) {
			System.out.println("Carregando Medicaos...");
			medicoes = new DAO<Medicao>(Medicao.class).listaTodos();
		}
		return medicoes;
	}

		
			
	/** Getters and Setters **/
	
	public Medicao getMedicao() {
		return medicao;
	}

	public void setMedicao(Medicao medicao) {
		this.medicao = medicao;
	}

	public List<Medicao> getMedicoes() {
		return medicoes;
	}

	public void setMedicoes(List<Medicao> medicoes) {
		this.medicoes = medicoes;
	}

	public DAO<Medicao> getDao() {
		return dao;
	}

	public void setDao(DAO<Medicao> dao) {
		this.dao = dao;
	}

	public Long getMedicaoId() {
		return medicaoId;
	}

	public void setMedicaoId(Long medicaoId) {
		this.medicaoId = medicaoId;
	}

	public Boolean getCadastro() {
		return cadastro;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}
	
	

}
