package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import com.hregional.springboot.backend.apirest.models.entity.CitaPaciente;
import com.hregional.springboot.backend.apirest.models.entity.Programacion;

public interface ICitaPacienteService {

	public List<CitaPaciente> findAll();
	
	public List<CitaPaciente> findAllActive();
	
	public CitaPaciente findById(Long id);
	
	public CitaPaciente save(CitaPaciente citaPaciente);
	
	public void delete(Long id);
	
}
