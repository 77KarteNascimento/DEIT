package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.TipoDocumento;
import br.gov.deit.util.Msg;

@ViewScoped
@ManagedBean
public class TipoDocumentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private TipoDocumento tipo = new TipoDocumento();
	private List<TipoDocumento> tipos;
	private Long tipoId;
	DAO<TipoDocumento> dao = new DAO<TipoDocumento>(TipoDocumento.class);
	public Boolean cadastro = true;
	
	public void carregaTipoDocumento() {

		DAO<TipoDocumento> dao = new DAO<TipoDocumento>(TipoDocumento.class);
		if (tipoId != null && tipoId != 0) {
			this.tipo = dao.buscaPorId(this.tipoId);
		}
	}
	
	
	public void grava() {
		for (TipoDocumento tipos : this.getTipos()) {
			if(tipos.getNome().equals(this.getTipo().getNome())) {
				this.cadastro = false;
				break;
			}
		}
		if(this.cadastro == true){
			if(this.tipo.getNome() == null){
				Msg.addMsgError("Preencha Corretamento o Campo Nome");
			} else {
				if (tipo.getId() == null) {
					Msg.addMsgInfo("Tipo de Documento Cadastrado Com Sucesso");
					dao.adiciona(tipo);
					this.tipo = new TipoDocumento();
				} else {
					Msg.addMsgInfo("Tipo de Documento Recebido");
					dao.atualiza(tipo);
					this.tipo = new TipoDocumento();
				}
			}
		} else {
			Msg.addMsgError("Este Tipo de Documento ja foi cadastrado.");
		}
		tipos = dao.listaTodos();
		this.cadastro = true;
	}
	
	public List<TipoDocumento> getTipos() {
		if (tipos == null) {
			System.out.println("Carregando Tipo de Documentos...");
			tipos = new DAO<TipoDocumento>(TipoDocumento.class).listaTodos();
		}
		return tipos;
	}

					
	/** Getters and Setters **/
	
	public TipoDocumento getTipo() {
		return tipo;
	}

	public void setTipo(TipoDocumento tipo) {
		this.tipo = tipo;
	}

	public DAO<TipoDocumento> getDao() {
		return dao;
	}

	public void setDao(DAO<TipoDocumento> dao) {
		this.dao = dao;
	}

	public void setTipos(List<TipoDocumento> tipos) {
		this.tipos = tipos;
	}


	public Long getTipoId() {
		return tipoId;
	}


	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}
	
}
