package br.gov.deit.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.gov.deit.dao.DAO;
import br.gov.deit.dao.UsuarioDAO;
import br.gov.deit.entity.Funcionario;
import br.gov.deit.util.Msg;

@SessionScoped
@ManagedBean
public class LoginBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Funcionario funcionarioL = new Funcionario();
	DAO<Funcionario> dao = new DAO<Funcionario>(Funcionario.class);

	public String efetuaLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		this.funcionarioL = dao.existe(this.funcionarioL);
		if (this.funcionarioL != null) {
			if(this.getFuncionarioL().getPerfil().equalsIgnoreCase("Administrador")){
				return "homeAdmin.xhtml?faces-redirect=true";
			} else {
				if(this.getFuncionarioL().getSenha().equalsIgnoreCase("123")){
					return "formAlterFuncionarioSenha.xhtml?faces-redirect=true";
				} else {
					return "home.xhtml?faces-redirect=true";
				}
			}
		} else {
			this.funcionarioL = new Funcionario();
			Msg.addMsgFatal("Senha ou Login Invalido. Se Persirtir Contate o Administrador");
			return null;
		}
	}
	
	// M�todo para redirecionar o funcion�rio para a tela correta
	public String rediciona(){
		if(this.getFuncionarioL().getPerfil().equalsIgnoreCase("Administrador")){
			return "homeAdmin.xhtml?faces-redirect=true";
		} else {
			return "home.xhtml?faces-redirect=true";
		}
	}
	
	public String editaProjeto(){
		if(this.getFuncionarioL().getPerfil().equalsIgnoreCase("Administrador")){
			return "formAlterDocumento.xhtml";
		} else {
			Msg.addMsgError("Voce Nao Tem Permissao Para Acessar esta Pagina");
			return null;
		}
	}

	
	public String esqueciSenha(){
		UsuarioDAO dao = new UsuarioDAO();
		DAO<Funcionario> dao2 = new DAO<Funcionario>(Funcionario.class);
		if(dao.esqueciSenha(this.funcionarioL) != null){
			this.setFuncionarioL(dao.esqueciSenha(this.getFuncionarioL()));
			this.funcionarioL.setSenha("123");
			dao2.atualiza(this.funcionarioL);
			Msg.addMsgWarn("Aperte o botão efetuar login para alterar sua senha");
			return "index.xhtml";
		} else {
			Msg.addMsgError("A Senha para este usuario nao pode ser modificada. Por favor, verifique os dados informados e tente novamente.");
			return "EsqueciMinhaSenha.xhtml";
		}
	}
	
	
	//M�tido para editar funcion�rio
	public String edita(){
		if (funcionarioL.getId() != null) {
			dao.atualiza(funcionarioL);
			Msg.addMsgInfo("Senha Alterada Com Sucesso");
			return "index.xhtml";
		} else {
			return null;
		}		
		
	}
	
	// M�todo para sair do sistema detonando a sess�o

			public String sair() {
				
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext .getExternalContext().getSession(false);
				session.invalidate();
				return "index.xhtml?faces-redirect=true";
				} 
	
	
	
	// Getters and Setters
	
	public Funcionario getFuncionarioL() {
		return funcionarioL;
	}

	public void setFuncionarioL(Funcionario funcionarioL) {
		this.funcionarioL = funcionarioL;
	}

	public Funcionario getFuncionario() {
		return funcionarioL;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionarioL = funcionario;
	}

	public boolean isLogado() {
		return funcionarioL.getMatricula() != null;
	}
}
