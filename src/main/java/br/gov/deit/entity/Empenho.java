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
public class Empenho implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="empe_id")
	private Long id;
	
	@Column(name="empe_numero")
	private String numero;
	
	@Column(name="empe_fonte_recurso")
	private String fonte_recurso;
	
	@Column(name="empe_processo")
	private String processo;
	
	@Column(name="empe_valor")
	private Double valor;
	
	@Temporal(TemporalType.DATE)
	@Column(name="empe_data")
	private Calendar data = Calendar.getInstance();
	
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFonte_recurso() {
		return fonte_recurso;
	}

	public void setFonte_recurso(String fonte_recurso) {
		this.fonte_recurso = fonte_recurso.toUpperCase();
	}


	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}	
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSaldo() {
		if (valor != null && getObra().getValor() != null)
			return getObra().getValor() - valor;
		else
			return null;
					
	}
	
}