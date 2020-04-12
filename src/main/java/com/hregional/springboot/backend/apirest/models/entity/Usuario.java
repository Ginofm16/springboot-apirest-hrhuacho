package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="TB_USUARIO")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usu_codigo;
	

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date usu_fec_registro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="rol_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Role role;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="per_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Personal personal;
	
	private Boolean usu_estado;
	
	@PrePersist
	public void prePersist() {
		this.usu_fec_registro = new Date();
	}

	public Long getUsu_codigo() {
		return usu_codigo;
	}

	public void setUsu_codigo(Long usu_codigo) {
		this.usu_codigo = usu_codigo;
	}

	public Date getUsu_fec_registro() {
		return usu_fec_registro;
	}

	public void setUsu_fec_registro(Date usu_fec_registro) {
		this.usu_fec_registro = usu_fec_registro;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Boolean getUsu_estado() {
		return usu_estado;
	}

	public void setUsu_estado(Boolean usu_estado) {
		this.usu_estado = usu_estado;
	}
	
	

}
