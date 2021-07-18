package br.gov.deit.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Obra;
import br.gov.deit.util.Msg;

@ViewScoped
@ManagedBean
public class ObraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Obra ob = new Obra();
	private List<Obra> obras;
	private List<Obra> obrasD;
	private List<Obra> orderByNome;
	private DAO<Obra> dao = new DAO<Obra>(Obra.class);
	private Long obraId;
	public Boolean cadastro = true;
		
	public void carregaObra() {

		DAO<Obra> dao = new DAO<Obra>(Obra.class);
		if (obraId != null && obraId != 0) {
			this.ob = dao.buscaPorId(this.obraId);
		}
	}
	//Carrega obra em ordem alfabetica
	
	public List<Obra> getOrderByNome() {
		if (orderByNome == null) {
			System.out.println("Carregando Obra em Ordem Alfabética...");
			orderByNome = new DAO<Obra>(Obra.class).listaTodos();
		}
		return orderByNome;
	}
	
//	public List<Obra> getObras() {
//		if (obras == null) {
//			System.out.println("Carregando Obra em Ordem Alfabética...");
//			obras = new DAO<Obra>(Obra.class).getAllOrder("nome");
//		}
//		return obras;
//	}
	
	public void grava() {
		for (Obra obras : this.getObras()) {
			if(obras.getNome().equals(this.getOb().getNome())) {
				this.cadastro = false;
				break;
			}
		}
		if(this.cadastro == true){
			if(this.ob.getNome() == null){
				Msg.addMsgError("Preencha Corretamento o Campo número da medição");
			} else {
				if (ob.getId() == null) {
					Msg.addMsgInfo("Obra Cadastrada Com Sucesso");
					this.getOb().setSituacao("Em Execução");
					dao.adiciona(ob);
					this.ob = new Obra();
				} else {
					Msg.addMsgInfo("Obra Recebida");
					dao.atualiza(ob);
					this.ob = new Obra();
				}
			}
		} else {
			Msg.addMsgError("Este número de medição já existe em outra obra");
		}
		obras = dao.listaTodos();
		this.cadastro = true;
	}
	
	public void altera() {
		if (ob.getId() != null) {
			Msg.addMsgInfo("Obra Alterada Com Sucesso");
			dao.atualiza(ob);
			this.ob = new Obra();
		}
	}

	public void onEdit(RowEditEvent event) {
		dao.atualiza(ob);
		FacesMessage msg = new FacesMessage("Car Edited", ("Situação da obra: " + getOb().getNome() + " foi modificado"));  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Car Cancelled", ("Operação cancelada"));  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    
	public void concluirObra() {
		if (ob.getId()==null) {
			Msg.addMsgError("Escolha uma obra para mudar a situação");
		} else {
			Msg.addMsgInfo("Situação modificada");
			dao.atualiza(ob);
			this.ob = new Obra();
		}
	}
	
	public void gravaPrazo() {
		if(ob.getNome() == null){
			Msg.addMsgError("Preencha Corretamento o Campo Obra");
		} else {
			if (ob.getId() != null && ob.getAditivo1() != null && ob.getAditivo2() == null && ob.getAditivo3() == null) {
				Msg.addMsgInfo("Novo Prazo de Entrega Estabelecido Para Obra " + getOb().getNome());
				dao.atualiza(ob);
				this.ob = new Obra();
			}
			if (ob.getId() != null && ob.getAditivo1() != null && ob.getAditivo2() != null && ob.getAditivo3() == null) {
				Msg.addMsgInfo("Novo Prazo de Entrega Estabelecido Para Obra " + getOb().getNome());
				dao.atualiza(ob);
				this.ob = new Obra();
			}
			if (ob.getId() != null && ob.getAditivo1() != null && ob.getAditivo2() != null && ob.getAditivo3() != null) {
				Msg.addMsgInfo("Novo Prazo de Entrega Estabelecido Para Obra " + getOb().getNome());
				dao.atualiza(ob);
				this.ob = new Obra();
			}
		}
	obras = dao.listaTodos();
	this.cadastro = true;
}
	

	public List<Obra> getObras() {
		if (obras == null) {
			System.out.println("Carregando Obras...");
			obras = new DAO<Obra>(Obra.class).listaTodos();
		}
		return obras;
	}
	
	// Lista de todos os fornecedores
		public List<Obra> getListaObras() {
			obrasD = new ArrayList<Obra>();
			List<Obra> obs = new ArrayList<Obra>();
			obs = this.getObras();
			for (int i = 0; i < obs.size(); i++) {
				if (obs.get(i).getId() != null) {
					obrasD.add(obs.get(i));
					
				}
			}
			return obrasD;
		}
		
			
	/** Getters and Setters **/

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	public DAO<Obra> getDao() {
		return dao;
	}

	public void setDao(DAO<Obra> dao) {
		this.dao = dao;
	}

	public Long getObraId() {
		return obraId;
	}

	public void setObraId(Long obraId) {
		this.obraId = obraId;
	}

	public Boolean getCadastro() {
		return cadastro;
	}

	public void setCadastro(Boolean cadastro) {
		this.cadastro = cadastro;
	}

	public Obra getOb() {
		return ob;
	}

	public void setOb(Obra ob) {
		this.ob = ob;
	}

	public void setOrderByNome(List<Obra> orderByNome) {
		this.orderByNome = orderByNome;
	}

	public List<Obra> getObrasD() {
		return obrasD;
	}

	public void setObrasD(List<Obra> obrasD) {
		this.obrasD = obrasD;
	}

	
}
