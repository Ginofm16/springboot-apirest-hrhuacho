package com.hregional.springboot.backend.apirest.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tb_especialidad")
public class Especialidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long esp_codigo;
	
	@NotEmpty(message = "no puede estar vacio")
	private String esp_nombre;
	
	private Boolean esp_estado;

	public Long getEsp_codigo() {
		return esp_codigo;
	}

	public void setEsp_codigo(Long esp_codigo) {
		this.esp_codigo = esp_codigo;
	}

	public String getEsp_nombre() {
		return esp_nombre;
	}

	public void setEsp_nombre(String esp_nombre) {
		this.esp_nombre = esp_nombre;
	}

	public Boolean getEsp_estado() {
		return esp_estado;
	}

	public void setEsp_estado(Boolean esp_estado) {
		this.esp_estado = esp_estado;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Especialidad{");
		sb.append("esp_codigo=").append(esp_codigo);
		sb.append(", esp_nombre='").append(esp_nombre).append('\'');
		sb.append(", esp_estado=").append(esp_estado);
		sb.append('}');
		return sb.toString();
	}
}
