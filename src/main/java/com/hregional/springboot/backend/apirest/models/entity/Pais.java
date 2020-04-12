package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tb_pais")
public class Pais implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pais_codigo;
	
	@NotEmpty(message = "no puede estar vacio")
	private String pais_nombre;

	private Boolean pais_estado;
	
	public Long getPais_codigo() {
		return pais_codigo;
	}

	public void setPais_codigo(Long pais_codigo) {
		this.pais_codigo = pais_codigo;
	}

	public String getPais_nombre() {
		return pais_nombre;
	}

	public void setPais_nombre(String pais_nombre) {
		this.pais_nombre = pais_nombre;
	}

	public Boolean getPais_estado() {
		return pais_estado;
	}

	public void setPais_estado(Boolean pais_estado) {
		this.pais_estado = pais_estado;
	}
	
	
	
}
