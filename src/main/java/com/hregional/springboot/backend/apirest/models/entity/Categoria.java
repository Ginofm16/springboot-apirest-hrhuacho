package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tb_categoria")
public class Categoria  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cat_codigo;
	
	@NotEmpty(message = "no puede estar vacio")
	private String cat_nombre;
	
	private Boolean cat_estado;

	public Long getCat_codigo() {
		return cat_codigo;
	}

	public void setCat_codigo(Long cat_codigo) {
		this.cat_codigo = cat_codigo;
	}

	public String getCat_nombre() {
		return cat_nombre;
	}

	public void setCat_nombre(String cat_nombre) {
		this.cat_nombre = cat_nombre;
	}

	public Boolean getCat_estado() {
		return cat_estado;
	}

	public void setCat_estado(Boolean cat_estado) {
		this.cat_estado = cat_estado;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Categoria{");
		sb.append("cat_codigo=").append(cat_codigo);
		sb.append(", cat_nombre='").append(cat_nombre).append('\'');
		sb.append(", cat_estado=").append(cat_estado);
		sb.append('}');
		return sb.toString();
	}
}
