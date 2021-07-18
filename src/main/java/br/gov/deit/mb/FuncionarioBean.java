package br.gov.deit.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.gov.deit.dao.DAO;
import br.gov.deit.entity.Funcionario;
import br.gov.deit.util.Msg;

@ViewScoped
@ManagedBean
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LoginBean login;
	
	private Funcionario funcionario = new Funcionario();
//	private List<Funcionario> funcionarios;
	DAO<Funcionario> dao = new DAO<Funcionario>(Funcionario.class);
	public Boolean cadastro = true;
	
	//Método para Edição do funcionário na troca de perfil;
	public void edita() {
		dao.atualiza(funcionario);
		Msg.addMsgInfo("Perfil Alterado Com Sucesso");
	}
	
	//Alteração na criação de um novo método para solicitação de cadastro
//	public void grava() {
//		for (Funcionario funcionarioLista : this.getFuncionarios()) {
//			if(funcionarioLista.getMatricula().equalsIgnoreCase(this.funcionario.getMatricula())){
//				this.cadastro = false;
//				break;
//			}
//		}
//		if(this.cadastro == true){
//			if (this.getFuncionario().getSenha().equals(this.getFuncionario().getRep_senha()) ) {
//				this.getFuncionario().setStatus("Em Espera");
//				this.getFuncionario().setPerfil("Usuário");
//				dao.adiciona(funcionario);
//				Msg.addMsgInfo("Funcionário Cadastrado Com Sucesso!");
//				this.funcionario = new Funcionario();
//					
//			} else {
//				Msg.addMsgError("Senha diferente do Repetir Senha");
//			}
//		} else {
//			Msg.addMsgError("Já existe um funcionário cadastrado com esta matrícula.");
//		}
//		 this.funcionarios = dao.listaTodos();
//		 this.cadastro = true;
//	}
	
	// Método para ativar funcionário, permitindo assim o seu login
	public void ativaFuncionario(){
		if(this.getFuncionario().getStatus().equalsIgnoreCase("Em Espera") || this.getFuncionario().getStatus().equalsIgnoreCase("Desativado")){
			this.getFuncionario().setStatus("Ativado");
			dao.atualiza(funcionario);
			System.out.println("------------------------------------------------------OK!!!!!!!!!!!");
		} else {
			System.out.println("-------------------------------------------------------Algum erro ocorreu e não foi possível ativar o funcionário. Por Favor, contate a administração.");
		}
	}
	
	// Método para desativar funcionário, cessando sua atilidade.
	public void desativaFuncionario(){
		if(this.getFuncionario().getStatus().equalsIgnoreCase("Em Espera") || this.getFuncionario().getStatus().equalsIgnoreCase("Ativado")){
			this.getFuncionario().setStatus("Desativado");
			dao.atualiza(funcionario);
			System.out.println("------------------------------------------------------OK!!!!!!!!!!!");
		} else {
			System.out.println("-------------------------------------------------------Algum erro ocorreu e não foi possível ativar o funcionário. Por Favor, contate a administração.");
		}
	}
	
	
	/** Getters and Setters **/
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public DAO<Funcionario> getDao() {
		return dao;
	}

	public void setDao(DAO<Funcionario> dao) {
		this.dao = dao;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}


}
