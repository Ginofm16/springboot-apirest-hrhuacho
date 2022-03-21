package com.hregional.springboot.backend.apirest.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CitaReporte {
    private String paciente;
    private String consultorio;
    private String fecha;
    private String medico;


}
