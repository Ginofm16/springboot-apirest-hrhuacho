package com.hregional.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.CitaPaciente;
import com.hregional.springboot.backend.apirest.models.entity.Estudio;

public interface IEstudioDao extends JpaRepository<Estudio, Long>{

	@Query("from Estudio cp where cp.est_estado = true")
	public List<Estudio> findAllActive();
}
