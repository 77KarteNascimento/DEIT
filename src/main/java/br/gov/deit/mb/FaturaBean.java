package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Fatura;
import br.gov.deit.util.Msg;


@ViewScoped
@ManagedBean
public class FaturaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Fatura fatura = new Fatura();
	private List<Fatura> faturas;
	private DAO<Fatura> dao = new DAO<Fatura>(Fatura.class);
	private Long faturaId;
	public Boolean cadastro = true;
	
	public void carregaFatura() {

		DAO<Fatura> dao = new DAO<Fatura>(Fatura.class);
		if (faturaId != null && faturaId != 0) {
			this.fatura = dao.buscaPorId(this.faturaId);
		}
	}
	
	public void grava() {
		for (Fatura faturas : this.getFaturas()) {
			if(faturas.getNumero().equals(this.getFatura().getNumero()) && 
					faturas.getObra().getEmpresa().getId().equals(this.getFatura().getObra().getEmpresa().getId())) {
				this.cadastro = false;
				break;
			}
		}
		if(this.cadastro == true){
			if(this.fatura.getNumero() == null){
				Msg.addMsgError("Preencha Corretamento o Campo Numero");
			} else {
				if (fatura.getId() == null) {
					Msg.addMsgInfo("Fatura Cadastrada Com Sucesso");
					dao.adiciona(fatura);
					this.fatura = new Fatura();
				} else {
					Msg.addMsgInfo("Atualização Realizada Com Sucesso");
					dao.atualiza(fatura);
					this.fatura = new Fatura();
				}
			}
		} else {
			Msg.addMsgError("Este numero da Fatura ja foi cadastrado.");
		}
		faturas = dao.listaTodos();
		this.cadastro = true;
	}
	
	public void altera() {
		if (fatura.getId() != null) {
			Msg.addMsgInfo("Fatura Editada Com Sucesso");
			dao.atualiza(fatura);
			this.fatura = new Fatura();
		}
	}

	public List<Fatura> getFaturas() {
		if (faturas == null) {
			System.out.println("Carregando fatura...");
			faturas = new DAO<Fatura>(Fatura.class).listaTodos();
		}
		return faturas;
	}
	
	/** Getters and Setters **/

	
	public Fatura getFatura() {
		return fatura;
	}

	public void setFatura(Fatura fatura) {
		this.fatura = fatura;
	}

	public Long getFaturaId() {
		return faturaId;
	}

	public void setFaturaId(Long faturaId) {
		this.faturaId = faturaId;
	}

	public void setFaturas(List<Fatura> faturas) {
		this.faturas = faturas;
	}

	
	public Boolean getCadastro() {
		return cadastro;
	}

	
	
			
	
	
}
