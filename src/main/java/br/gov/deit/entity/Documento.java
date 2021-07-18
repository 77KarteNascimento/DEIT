package br.gov.deit.entity;

import java.io.Serializable;
import java.util.Calendar;

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
public class Documento implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="doc_id")
	private Long id;
	
	@Column(name="doc_numero")
	private String numero;
	
	@Column(name="doc_ano")
	private Integer ano;
	
	@Temporal(TemporalType.DATE)
	@Column(name="doc_data")
	private Calendar data = Calendar.getInstance();
	
	@Column(name="doc_situacao")
	private String situacao;
	
	@Column(name="doc_valor_obra")
	private Double valor_obra;
	
	@Column(name="doc_prazo_execucao")
	private Integer prazo_execucao;
	
	@Column(name="doc_autor_projeto_basico")
	private String autor_projeto;
	
	@Column(name="doc_medicao")
	private String medicao;
	
	@Column(name="doc_tipo_obra")
	private String tipoObra;
	
	@Column(name="doc_andamento")
	private String andamento;
	
	@ManyToOne
	@JoinColumn(name="fk_municipio")
	private Municipio municipio;
	
	
	/** Getters And Setters **/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao.toUpperCase();
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	public Double getValor_obra() {
		return valor_obra;
	}

	public void setValor_obra(Double valor_obra) {
		this.valor_obra = valor_obra;
	}

	public Integer getPrazo_execucao() {
		return prazo_execucao;
	}

	public void setPrazo_execucao(Integer prazo_execucao) {
		this.prazo_execucao = prazo_execucao;
	}

	public String getAutor_projeto() {
		return autor_projeto;
	}

	public void setAutor_projeto(String autor_projeto) {
		this.autor_projeto = autor_projeto.toUpperCase();
	}

	public String getMedicao() {
		return medicao;
	}

	public void setMedicao(String medicao) {
		this.medicao = medicao;
	}

	public String getTipoObra() {
		return tipoObra;
	}

	public void setTipoObra(String tipoObra) {
		this.tipoObra = tipoObra;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getAndamento() {
		return andamento;
	}

	public void setAndamento(String andamento) {
		this.andamento = andamento;
	}
	
	

}