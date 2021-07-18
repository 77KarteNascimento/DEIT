package br.gov.deit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class Prazo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prazo_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="fk_obra")
	private Obra obra;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prazo_data_aditivo1")
	private Date aditivo1;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prazo_data_aditivo2")
	private Date aditivo2;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="prazo_data_aditivo3")
	private Date aditivo3;
	
	//Get e Set
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
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

}
