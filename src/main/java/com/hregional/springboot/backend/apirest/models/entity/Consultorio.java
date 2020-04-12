package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_consultorio")
public class Consultorio implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long con_codigo;
	
	@NotEmpty(message = "no puede estar vacio")
	private String con_nombre;
	
	@NotEmpty(message = "no puede estar vacio")
	private Double con_precio;

	public Long getCon_codigo() {
		return con_codigo;
	}

	public void setCon_codigo(Long con_codigo) {
		this.con_codigo = con_codigo;
	}

	public String getCon_nombre() {
		return con_nombre;
	}

	public void setCon_nombre(String con_nombre) {
		this.con_nombre = con_nombre;
	}

	public Double getCon_precio() {
		return con_precio;
	}

	public void setCon_precio(Double con_precio) {
		this.con_precio = con_precio;
	}
	
	
	

}
