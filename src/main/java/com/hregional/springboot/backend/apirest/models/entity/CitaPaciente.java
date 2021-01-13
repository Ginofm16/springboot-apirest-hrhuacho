package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_cita_paciente")
public class CitaPaciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cit_codigo;
	
	@NotNull(message = "no puede estar vacio")
	private Double cit_exoneracion;
	
	@NotNull(message = "no puede estar vacio")
	private Double cit_costo_total;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date cit_fec_registro;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="his_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Historia historia;

	private Boolean cit_estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pro_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Programacion programacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usu_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Usuario usuario;
	
	@PrePersist
	public void prePersist() {
		this.cit_fec_registro = new Date();
	}

	public Long getCit_codigo() {
		return cit_codigo;
	}

	public void setCit_codigo(Long cit_codigo) {
		this.cit_codigo = cit_codigo;
	}

	public Double getCit_exoneracion() {
		return cit_exoneracion;
	}

	public void setCit_exoneracion(Double cit_exoneracion) {
		this.cit_exoneracion = cit_exoneracion;
	}

	public Double getCit_costo_total() {
		return cit_costo_total;
	}

	public void setCit_costo_total(Double cit_costo_total) {
		this.cit_costo_total = cit_costo_total;
	}


	public Date getCit_fec_registro() {
		return cit_fec_registro;
	}

	public void setCit_fec_registro(Date cit_fec_registro) {
		this.cit_fec_registro = cit_fec_registro;
	}
	
	public Historia getHistoria() {
		return historia;
	}

	

	public void setHistoria(Historia historia) {
		this.historia = historia;
	}

	public Programacion getProgramacion() {
		return programacion;
	}

	public void setProgramacion(Programacion programacion) {
		this.programacion = programacion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getCit_estado() {
		return cit_estado;
	}

	public void setCit_estado(Boolean cit_estado) {
		this.cit_estado = cit_estado;
	}
	
	
}
