package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="TB_PERSONAL")
public class Personal implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long per_codigo;
	
	@NotEmpty(message = "no puede estar vacio")
	private String per_nombre;
	
	@NotEmpty(message = "no puede estar vacio")
	private String per_ape_paterno;
	
	@NotEmpty(message = "no puede estar vacio")
	private String per_ape_materno;
	
	@NotEmpty(message = "no puede estar vacio")
	private String per_dni;
	
	@NotEmpty(message = "no puede estar vacio")
	private String per_direccion;
	
	@NotEmpty(message = "no puede estar vacio")
	private String per_telefono;
	
	@NotEmpty(message = "no puede estar vacio")
	private String per_rne;
	
	@NotNull(message = "no puede estar vacio")
	@Temporal(TemporalType.DATE)
	private Date per_fec_ingreso;
	
	@NotNull(message = "no puede estar vacio")
	@Temporal(TemporalType.DATE)
	private Date per_fec_salida;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cat_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Categoria categoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="esp_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Especialidad especialidad;
	

	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="tb_usuario", joinColumns = @JoinColumn(name="per_codigo"), 
	inverseJoinColumns = @JoinColumn(name="rol_codigo"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"per_codigo","rol_codigo"})})
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private List<Role> roles;
	
	private Boolean per_estado;


	public Long getPer_codigo() {
		return per_codigo;
	}


	public void setPer_codigo(Long per_codigo) {
		this.per_codigo = per_codigo;
	}


	public String getPer_nombre() {
		return per_nombre;
	}


	public void setPer_nombre(String per_nombre) {
		this.per_nombre = per_nombre;
	}


	public String getPer_ape_paterno() {
		return per_ape_paterno;
	}


	public void setPer_ape_paterno(String per_ape_paterno) {
		this.per_ape_paterno = per_ape_paterno;
	}


	public String getPer_ape_materno() {
		return per_ape_materno;
	}


	public void setPer_ape_materno(String per_ape_materno) {
		this.per_ape_materno = per_ape_materno;
	}


	public String getPer_dni() {
		return per_dni;
	}


	public void setPer_dni(String per_dni) {
		this.per_dni = per_dni;
	}


	public String getPer_direccion() {
		return per_direccion;
	}


	public void setPer_direccion(String per_direccion) {
		this.per_direccion = per_direccion;
	}


	public String getPer_telefono() {
		return per_telefono;
	}


	public void setPer_telefono(String per_telefono) {
		this.per_telefono = per_telefono;
	}


	public String getPer_rne() {
		return per_rne;
	}


	public void setPer_rne(String per_rne) {
		this.per_rne = per_rne;
	}


	public Date getPer_fec_ingreso() {
		return per_fec_ingreso;
	}


	public void setPer_fec_ingreso(Date per_fec_ingreso) {
		this.per_fec_ingreso = per_fec_ingreso;
	}


	public Date getPer_fec_salida() {
		return per_fec_salida;
	}


	public void setPer_fec_salida(Date per_fec_salida) {
		this.per_fec_salida = per_fec_salida;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Especialidad getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	public Boolean getPer_estado() {
		return per_estado;
	}


	public void setPer_estado(Boolean per_estado) {
		this.per_estado = per_estado;
	}
	
	

}
