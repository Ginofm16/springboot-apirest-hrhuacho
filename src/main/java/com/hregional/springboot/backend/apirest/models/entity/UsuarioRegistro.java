package com.hregional.springboot.backend.apirest.models.entity;

import java.io.Serializable;

public class UsuarioRegistro implements Serializable {

    private static final long serialVersionUID = -3575636864518574234L;
    private String correo;
    private String usuario;
    private String password;
    private String confirm_password;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
