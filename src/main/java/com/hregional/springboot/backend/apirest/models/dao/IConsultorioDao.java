package com.hregional.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.Consultorio;

public interface IConsultorioDao extends JpaRepository<Consultorio, Long>{

	@Query("select c from Consultorio c where c.con_nombre = ?1")
	public List<Consultorio> findByConsultorio(String term); 
}
