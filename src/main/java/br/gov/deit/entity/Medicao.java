package br.gov.deit.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.gov.deit.converter.BaseEntity;

@Entity
public class Medicao implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="medi_id")
	private Long id;
	
	@Column(name="medi_numero")
	private Integer numero;
		
	@Column(name="medi_termo_recebimento")
	private String numero_termo_recebimento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="medi_data_termo_recebimento")
	private Calendar data_termo = Calendar.getInstance();
	
	@Column(name="medi_componente")
	private String componente;
	
	/** Getters And Setters **/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getNumero_termo_recebimento() {
		return numero_termo_recebimento;
	}

	public void setNumero_termo_recebimento(String numero_termo_recebimento) {
		this.numero_termo_recebimento = numero_termo_recebimento;
	}

	public Calendar getData_termo() {
		return data_termo;
	}

	public void setData_termo(Calendar data_termo) {
		this.data_termo = data_termo;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente.toUpperCase();
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}