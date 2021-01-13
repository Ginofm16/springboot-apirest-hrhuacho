package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tb_estudio")
public class Estudio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer est_codigo;
	
	@NotEmpty(message = "no puede estar vacio")
	private String est_nombre;
	
	private Boolean est_estado;

	public Integer getEst_codigo() {
		return est_codigo;
	}

	public void setEst_codigo(Integer est_codigo) {
		this.est_codigo = est_codigo;
	}

	public String getEst_nombre() {
		return est_nombre;
	}

	public void setEst_nombre(String est_nombre) {
		this.est_nombre = est_nombre;
	}

	public Boolean getEst_estado() {
		return est_estado;
	}

	public void setEst_estado(Boolean est_estado) {
		this.est_estado = est_estado;
	}
	
	

}
