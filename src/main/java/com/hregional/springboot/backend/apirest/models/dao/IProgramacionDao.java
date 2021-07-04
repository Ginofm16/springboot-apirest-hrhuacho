package com.hregional.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.Consultorio;
import com.hregional.springboot.backend.apirest.models.entity.Historia;
import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.entity.Programacion;

public interface IProgramacionDao extends JpaRepository<Programacion, Long> {

	@Query("from Personal")
	public List<Personal> findAllPersonal();
	
	@Query("from Consultorio")
	public List<Consultorio> findAllConsultorio();
	
	@Query("from Programacion p where p.pro_estado = true")
	public Page<Programacion> findAllActivePageable(Pageable pageable);
	
	@Query("from Programacion p where p.pro_estado = true")
	public List<Programacion> findAllActive();
	
	//@Query("SELECT p from Usuario u JOIN u.personal p where u.usu_codigo = ?1")
	@Query("SELECT p from Programacion p JOIN p.consultorio c where c.con_codigo = ?2")
	public List<Programacion> findAllProgramacionByConsultorio(Pageable pageable, String codigo);
	
	@Query("SELECT p from Programacion p JOIN p.consultorio c where c.con_codigo = ?1")
	public List<Programacion> findAllProgramacionByConsul(Long codigo);
	
	@Query("select p from Programacion p JOIN p.consultorio c where c.con_nombre like ?1%")
	public List<Programacion> findByProgramacion(String term);

	
}
