package com.hregional.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.Consultorio;
import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.entity.Programacion;

public interface IProgramacionDao extends JpaRepository<Programacion, Long> {

	@Query("from Personal")
	public List<Personal> findAllPersonal();
	
	@Query("from Consultorio")
	public List<Consultorio> findAllConsultorio();
	
	//@Query("SELECT p from Usuario u JOIN u.personal p where u.usu_codigo = ?1")
	@Query("SELECT p from Programacion p JOIN p.consultorio c where c.con_codigo = ?1")
	public List<Programacion> findAllProgramacionByConsultorio(Long cod);
	
}
