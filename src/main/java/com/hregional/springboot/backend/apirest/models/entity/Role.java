package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tb_rol")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rol_codigo;
	
	@NotEmpty(message = "no puede estar vacio")
	private String rol_nombre;
	
	private Boolean rol_estado;

	public Long getRol_codigo() {
		return rol_codigo;
	}

	public void setRol_codigo(Long rol_codigo) {
		this.rol_codigo = rol_codigo;
	}

	public String getRol_nombre() {
		return rol_nombre;
	}

	public void setRol_nombre(String rol_nombre) {
		this.rol_nombre = rol_nombre;
	}

	public Boolean getRol_estado() {
		return rol_estado;
	}

	public void setRol_estado(Boolean rol_estado) {
		this.rol_estado = rol_estado;
	}
	
	

}
