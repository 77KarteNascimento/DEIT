package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Empresa;
import br.gov.deit.util.Msg;


@ViewScoped
@ManagedBean
public class EmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa = new Empresa();
	private List<Empresa> empresas;
	private DAO<Empresa> dao = new DAO<Empresa>(Empresa.class);
	private Long empresaId;
	public Boolean cadastro = true;
	
	public void carregaEmpresa() {

		DAO<Empresa> dao = new DAO<Empresa>(Empresa.class);
		if (empresaId != null && empresaId != 0) {
			this.empresa = dao.buscaPorId(this.empresaId);
		}
	}
	
	public void grava() {
		for (Empresa empresas : this.getEmpresas()) {
			if(empresas.getCnpj().equals(this.getEmpresa().getCnpj())) {
				this.cadastro = false;
				break;
			}
		}
		if(this.cadastro == true){
			if(this.empresa.getCnpj() == null){
				Msg.addMsgError("Preencha Corretamento o Campo CNPJ");
			} else {
				if (empresa.getId() == null) {
					Msg.addMsgInfo("Empresa Cadastrada Com Sucesso");
					dao.adiciona(empresa);
					this.empresa = new Empresa();
				} else {
					Msg.addMsgInfo("Atualização Realizada Com Sucesso");
					dao.atualiza(empresa);
					this.empresa = new Empresa();
				}
			}
		} else {
			Msg.addMsgError("Este CNPJ ja foi cadastrado para uma Empresa.");
		}
		empresas = dao.listaTodos();
		this.cadastro = true;
	}
	
	public void altera() {
		if (empresa.getId() != null) {
			Msg.addMsgInfo("Empresa Editada Com Sucesso");
			dao.atualiza(empresa);
			this.empresa = new Empresa();
		}
	}

	public List<Empresa> getEmpresas() {
		if (empresas == null) {
			System.out.println("Carregando Empresas...");
			empresas = new DAO<Empresa>(Empresa.class).listaTodos();
		}
		return empresas;
	}
	
	/** Getters and Setters **/

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public DAO<Empresa> getDao() {
		return dao;
	}

	public void setDao(DAO<Empresa> dao) {
		this.dao = dao;
	}

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public Boolean getCadastro() {
		return cadastro;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
			
	
	
}
