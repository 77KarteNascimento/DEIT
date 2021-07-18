package br.gov.deit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.deit.converter.BaseEntity;

@Entity
public class Obra implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="obra_id")
	private Long id;
	
	@Column(name="obra_nome")
	private String nome;
	
	@Column(name="obra_tipo")
	private String tipo;
	
	@Column(name="obra_objeto")
	private String objeto;
	
	@Column(name="obra_tecnico")
	private String tecnico;
	
	@Column(name="obra_tecnico2")
	private String tecnico2;
	
	@Column(name="obra_valor")
	private Double valor;
	
	@Column(name="obra_os")
	private String os;
	
	@Column(name="obra_processo")
	private String processo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="obra_data_inicio")
	private Date data_inicio;
	
	@Column(name="obra_data_prazo")
	private Integer data_prazo;
	
	@Column(name="obra_situacao")
	private String situacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prazo_data_final")
	private Date data_final;

	@ManyToOne
	@JoinColumn(name="fk_municipio")
	private Municipio municipio;
	
	@ManyToOne
	@JoinColumn(name="fk_documento")
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(name="fk_empresa")
	private Empresa empresa;
	
	@Column(name="obra_representatate")
	private String representante;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prazo_data_aditivo1")
	private Date aditivo1;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prazo_data_aditivo2")
	private Date aditivo2;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prazo_data_aditivo3")
	private Date aditivo3;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	private Calendar dataHoje = Calendar.getInstance();

	
	/** Getters And Setters **/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto.toUpperCase();
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico.toUpperCase();
	}

	public String getTecnico2() {
		return tecnico2;
	}

	public void setTecnico2(String tecnico2) {
		this.tecnico2 = tecnico2.toUpperCase();
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os.toUpperCase();
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo.toUpperCase();
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_final() {
		return data_final;
	}

	public void setData_final(Date data_final) {
		this.data_final = data_final;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante.toUpperCase();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
		
	public Date getAditivo1() {
		return aditivo1;
	}

	public void setAditivo1(Date aditivo1) {
		this.aditivo1 = aditivo1;
	}

	public Date getAditivo2() {
		return aditivo2;
	}

	public void setAditivo2(Date aditivo2) {
		this.aditivo2 = aditivo2;
	}

	public Date getAditivo3() {
		return aditivo3;
	}

	public void setAditivo3(Date aditivo3) {
		this.aditivo3 = aditivo3;
	}
	
	public Integer getData_prazo() {
		return data_prazo;
	}

	public void setData_prazo(Integer data_prazo) {
		this.data_prazo = data_prazo;
	}

	public long getPrazo() {
		
		long diferenca = data_final.getTime() - data_inicio.getTime();
		
		int tempoDia = 1000 * 60 * 60 * 24;
		
		long diasDiferenca = diferenca / tempoDia;

		return diasDiferenca;
	}
	
//	public Date getAditivo10() {
//		int dias_a_avancar = (int) aditivo1.getTime(); // se quiser diminuir, basta por -2
//		Date nova_data = new Date(data_final.getTime()+((1000*24*60
//				*60)+dias_a_avancar));
//		return nova_data;
//	}
	
//	int dias_a_avancar = 2; // se quiser diminuir, basta por -2  
//    Date nova_data = new Date(hoje.getTime()+((1000*24*60*60)*dias_a_avancar));
	
	public long getDataFinal() {
		
		long diferenca = data_inicio.getTime();
		
		int tempoDia = 1000 * 60 * 60 * 24;
		
		long diasDiferenca = diferenca / tempoDia;
		
		long diasDiferenca2 = diasDiferenca + data_prazo;
		
		return diasDiferenca2;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	

	
//	Calendar dataInicio = Calendar.getInstance();
//    // Atribui a data de 10/FEV/2008
//    dataInicio.set(2008, Calendar.FEBRUARY, 10);
//
//    // Data de hoje
//    Calendar dataFinal = Calendar.getInstance();
//
//    // Calcula a diferen�a entre hoje e da data de inicio
//    long diferenca = dataFinal.getTimeInMillis() -
//                     dataInicio.getTimeInMillis();
//
//    // Quantidade de milissegundos em um dia
//    int tempoDia = 1000 * 60 * 60 * 24;
//
//    long diasDiferenca = diferenca / tempoDia;
//
//    System.out.println("Entre a data inicial e final s�o " +
//                        diasDiferenca + " dias de diferen�a.");

}
