package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Municipio;
import br.gov.deit.util.Msg;

@ViewScoped
@ManagedBean
public class MunicipioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Municipio municipio = new Municipio();
	private List<Municipio> municipios;
	private DAO<Municipio> dao = new DAO<Municipio>(Municipio.class);
	private Long municipioId;
	public Boolean cadastro = true;
	
	public void carregaMunicipio() {

		DAO<Municipio> dao = new DAO<Municipio>(Municipio.class);
		if (municipioId != null && municipioId != 0) {
			this.municipio = dao.buscaPorId(this.municipioId);
		}
	}
	
	public void grava() {
		for (Municipio municipios : this.getMunicipios()) {
			if(municipios.getNome().equalsIgnoreCase(this.getMunicipio().getNome()) && municipios.getUf().getNome().equalsIgnoreCase(this.getMunicipio().getUf().getNome())){
				this.cadastro = false;
				break;
			}
		}
		if(this.cadastro == true){
			if(this.municipio.getUf() == null){
				Msg.addMsgError("O Municipio nao pode ser cadastrado sem estar ligado a um Estado. Por Favor, verifique e tente novamente.");
			} else {
				if (municipio.getId() == null) {
					Msg.addMsgInfo("Municipio Cadastrado Com Sucesso");
					dao.adiciona(municipio);
					this.municipio = new Municipio();
				} else {
					Msg.addMsgInfo("Alteracao Realizada Com Sucesso");
					dao.atualiza(municipio);
				}
			}
		} else {
			Msg.addMsgError("Este Municipio ja esta cadastrado para este Estado.");
		}
		municipios = dao.listaTodos();
		this.cadastro = true;
	}
	
	
	public List<Municipio> getMunicipios() {
		if (municipios == null) {
			System.out.println("Carregando Municipios...");
			municipios = new DAO<Municipio>(Municipio.class).listaTodos();
		}
		return municipios;
	}

	/** Getters and Setters **/
	
	
	public Municipio getMunicipio() {
		return municipio;
	}


	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}


	public DAO<Municipio> getDao() {
		return dao;
	}


	public void setDao(DAO<Municipio> dao) {
		this.dao = dao;
	}


	public Long getMunicipioId() {
		return municipioId;
	}


	public void setMunicipioId(Long municipioId) {
		this.municipioId = municipioId;
	}


	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	

}
