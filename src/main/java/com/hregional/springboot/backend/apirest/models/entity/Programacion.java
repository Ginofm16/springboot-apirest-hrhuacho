package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_programacion")
public class Programacion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pro_codigo;
	
	@NotNull(message = "no puede estar vacio")
	@Temporal(TemporalType.DATE)
	private Date pro_fecha;
	
	private String pro_hora_inicio;

	private Integer pro_num_turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="con_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Consultorio consultorio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usu_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario usuario;
	
	private Boolean pro_estado;

	public Long getPro_codigo() {
		return pro_codigo;
	}

	public void setPro_codigo(Long pro_codigo) {
		this.pro_codigo = pro_codigo;
	}

	public Date getPro_fecha() {
		return pro_fecha;
	}

	public void setPro_fecha(Date pro_fecha) {
		this.pro_fecha = pro_fecha;
	}

	public String getPro_hora_inicio() {
		return pro_hora_inicio;
	}

	public void setPro_hora_inicio(String pro_hora_inicio) {
		this.pro_hora_inicio = pro_hora_inicio;
	}

	public Integer getPro_num_turno() {
		return pro_num_turno;
	}

	public void setPro_num_turno(Integer pro_num_turno) {
		this.pro_num_turno = pro_num_turno;
	}

	public Consultorio getConsultorio() {
		return consultorio;
	}

	public void setConsultorio(Consultorio consultorio) {
		this.consultorio = consultorio;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getPro_estado() {
		return pro_estado;
	}

	public void setPro_estado(Boolean pro_estado) {
		this.pro_estado = pro_estado;
	}

	
	
	
}
