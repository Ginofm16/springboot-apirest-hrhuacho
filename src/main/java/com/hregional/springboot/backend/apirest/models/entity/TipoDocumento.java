package com.hregional.springboot.backend.apirest.models.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_tipo_documento")
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = -7514233602587997016L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doc_codigo;

    private String doc_nombre;
    private Boolean doc_estado;

    public Long getDoc_codigo() {
        return doc_codigo;
    }

    public void setDoc_codigo(Long doc_codigo) {
        this.doc_codigo = doc_codigo;
    }

    public String getDoc_nombre() {
        return doc_nombre;
    }

    public void setDoc_nombre(String doc_nombre) {
        this.doc_nombre = doc_nombre;
    }

    public Boolean getDoc_estado() {
        return doc_estado;
    }

    public void setDoc_estado(Boolean doc_estado) {
        this.doc_estado = doc_estado;
    }
}
