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
@Table(name="TB_HISTORIA")
public class Historia implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long his_codigo;
	
	@NotEmpty(message = "no puede estar vacio")
	private String his_nombre;
	
	@NotEmpty(message = "no puede estar vacio")
	private String his_ape_paterno;
	
	@NotEmpty(message = "no puede estar vacio")
	private String his_ape_materno;
	
	@NotEmpty(message = "no puede estar vacio")
	private String his_documento;
	
	@NotEmpty(message = "no puede estar vacio")
	private String his_direccion;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name="his_fec_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date his_fec_nacimiento;
	
	@NotEmpty(message = "no puede estar vacio")
	private String his_seguro;
	
	@NotEmpty(message = "no puede estar vacio")
	private String his_genero;
	
	private Boolean his_estado;
	
	@NotNull(message="el pais no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pais_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Pais pais;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="est_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Estudio estudio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doc_codigo")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private TipoDocumento tipo_documento;

	public Long getHis_codigo() {
		return his_codigo;
	}

	public void setHis_codigo(Long his_codigo) {
		this.his_codigo = his_codigo;
	}

	public String getHis_nombre() {
		return his_nombre;
	}

	public void setHis_nombre(String his_nombre) {
		this.his_nombre = his_nombre;
	}

	public String getHis_ape_paterno() {
		return his_ape_paterno;
	}

	public void setHis_ape_paterno(String his_ape_paterno) {
		this.his_ape_paterno = his_ape_paterno;
	}

	public String getHis_ape_materno() {
		return his_ape_materno;
	}

	public void setHis_ape_materno(String his_ape_materno) {
		this.his_ape_materno = his_ape_materno;
	}

	public String getHis_documento() {
		return his_documento;
	}

	public void setHis_documento(String his_documento) {
		this.his_documento = his_documento;
	}

	public String getHis_direccion() {
		return his_direccion;
	}

	public void setHis_direccion(String his_direccion) {
		this.his_direccion = his_direccion;
	}

	public Date getHis_fec_nacimiento() {
		return his_fec_nacimiento;
	}

	public void setHis_fec_nacimiento(Date his_fec_nacimiento) {
		this.his_fec_nacimiento = his_fec_nacimiento;
	}

	public String getHis_seguro() {
		return his_seguro;
	}

	public void setHis_seguro(String his_seguro) {
		this.his_seguro = his_seguro;
	}

	public String getHis_genero() {
		return his_genero;
	}

	public void setHis_genero(String his_genero) {
		this.his_genero = his_genero;
	}

	public Boolean getHis_estado() {
		return his_estado;
	}

	public void setHis_estado(Boolean his_estado) {
		this.his_estado = his_estado;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	public TipoDocumento getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(TipoDocumento tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Historia{");
		sb.append("his_codigo=").append(his_codigo);
		sb.append(", his_nombre='").append(his_nombre).append('\'');
		sb.append(", his_ape_paterno='").append(his_ape_paterno).append('\'');
		sb.append(", his_ape_materno='").append(his_ape_materno).append('\'');
		sb.append(", his_documento='").append(his_documento).append('\'');
		sb.append(", his_direccion='").append(his_direccion).append('\'');
		sb.append(", his_fec_nacimiento=").append(his_fec_nacimiento);
		sb.append(", his_seguro='").append(his_seguro).append('\'');
		sb.append(", his_genero='").append(his_genero).append('\'');
		sb.append(", his_estado=").append(his_estado);
		sb.append(", pais=").append(pais);
		sb.append(", estudio=").append(estudio);
		sb.append(", tipo_documento=").append(tipo_documento);
		sb.append('}');
		return sb.toString();
	}
}
