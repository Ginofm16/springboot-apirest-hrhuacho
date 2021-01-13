package com.hregional.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.CitaPaciente;

public interface ICitaPacienteDao extends JpaRepository<CitaPaciente, Long>{

	@Query("from CitaPaciente cp where cp.cit_estado = true")
	public List<CitaPaciente> findAllActive();
	
}
