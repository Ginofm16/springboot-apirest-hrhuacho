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
	
	@NotEmpty(message = "no puede estar vacio")
	private String pro_hora_inicio;
	
	@NotEmpty(message = "no puede estar vacio")
	private String pro_sigla;
	
	@NotEmpty(message = "no puede estar vacio")
	private Integer pro_num_turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="con_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Consultorio consultorio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="per_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Personal personal;

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

	public String getPro_sigla() {
		return pro_sigla;
	}

	public void setPro_sigla(String pro_sigla) {
		this.pro_sigla = pro_sigla;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	
}
