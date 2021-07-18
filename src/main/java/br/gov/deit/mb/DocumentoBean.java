package br.gov.deit.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Documento;
import br.gov.deit.util.Msg;


@ViewScoped
@ManagedBean
public class DocumentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Documento documento = new Documento();
	private List<Documento> documentos;
	private DAO<Documento> dao = new DAO<Documento>(Documento.class);
	private Long documentoId;
	public Boolean cadastro = true;
	
	public void carregaDocumento() {

		DAO<Documento> dao = new DAO<Documento>(Documento.class);
		if (documentoId != null && documentoId != 0) {
			this.documento = dao.buscaPorId(this.documentoId);
		}
	}
	
	public void grava() {
		for (Documento documentos : this.getDocumentos()) {
			if(documentos.getNumero().equals(this.getDocumento().getNumero()) 
						&& documentos.getAno().equals(this.getDocumento().getAno())){
				this.cadastro = false;
				break;
			}
		}
		if(this.cadastro == true){
			if(this.documento.getNumero() == null){
				Msg.addMsgError("Preencha Corretamento o Campo Número");
			} else {
				if (documento.getId() == null) {
					Msg.addMsgInfo("Projeto Cadastrado Com Sucesso");
					dao.adiciona(documento);
					this.documento = new Documento();
				} else {
					Msg.addMsgInfo("Projeto Recebido");
					dao.atualiza(documento);
					this.documento = new Documento();
				}
			}
		} else {
			Msg.addMsgError("Este Projeto já foi cadastrado no sistema.");
		}
		documentos = dao.listaTodos();
		this.cadastro = true;
	}
	
	public void altera() {
		if (documento.getId() != null) {
			Msg.addMsgInfo("Projeto Alterado Com Sucesso");
			dao.atualiza(documento);
			this.documento = new Documento();
		}
	}

	public List<Documento> getDocumentos() {
		if (documentos == null) {
			System.out.println("Carregando Projetos...");
			documentos = new DAO<Documento>(Documento.class).listaTodos();
		}
		return documentos;
	}
	
			
	/** Getters and Setters **/
	
	public Documento getDocumento() {
		return documento;
	}


	public void setDocumento(Documento documento) {
		this.documento = documento;
	}


	public DAO<Documento> getDao() {
		return dao;
	}


	public void setDao(DAO<Documento> dao) {
		this.dao = dao;
	}


	public Long getDocumentoId() {
		return documentoId;
	}

}
