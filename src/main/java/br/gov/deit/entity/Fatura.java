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
public class Fatura implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fatu_id")
	private Long id;
	
	@Column(name="fatu_numero")
	private Integer numero;
	
	@Column(name="fatu_atestado")
	private String atestado;
	
	@Column(name="fatu_nota")
	private Integer nota;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fatu_data")
	private Date data;
	
	//Valor da Fatura
	@Column(name="fatu_valor_fatura")
	private Double valor_fatura;
	
	//Valor Acumulado
	@Column(name="fatu_valor_acumulado")
	private Double valor_acumulado;
	
	@ManyToOne
	@JoinColumn(name="fk_empenho")
	private Empenho empenho;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fatu_data_medicao_inicio")
	private Date medicao_inicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fatu_data_medicao_final")
	private Date medicao_final;
	
	@ManyToOne
	@JoinColumn(name="fk_obra")
	private Obra obra;
	
	/** Getters And Setters **/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAtestado() {
		return atestado;
	}

	public void setAtestado(String atestado) {
		this.atestado = atestado;
	}

	public Empenho getEmpenho() {
		return empenho;
	}

	public void setEmpenho(Empenho empenho) {
		this.empenho = empenho;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor_fatura() {
		return valor_fatura;
	}

	public void setValor_fatura(Double valor_fatura) {
		this.valor_fatura = valor_fatura;
	}

	public Double getValor_acumulado() {
		return valor_acumulado;
	}

	public void setValor_acumulado(Double valor_acumulado) {
		this.valor_acumulado = valor_acumulado;
	}
	
	public Double getSaldo() {
		if (getObra().getValor() != null  && valor_acumulado != null)
			return getObra().getValor() - valor_acumulado;
		else
			return null;
	}
	
	public Double getFinanceiro() {
		if (getObra().getValor() != null  && valor_acumulado != null)
			return valor_acumulado / getObra().getValor() * 100;
		else
			return null;
	}
	
	public Double getSaldoFaturar() {
		if (getSaldo() != null  && valor_acumulado != null)
			return getSaldo() - valor_acumulado;
		else
			return null;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getMedicao_inicio() {
		return medicao_inicio;
	}

	public void setMedicao_inicio(Date medicao_inicio) {
		this.medicao_inicio = medicao_inicio;
	}

	public Date getMedicao_final() {
		return medicao_final;
	}

	public void setMedicao_final(Date medicao_final) {
		this.medicao_final = medicao_final;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}
	
	

}