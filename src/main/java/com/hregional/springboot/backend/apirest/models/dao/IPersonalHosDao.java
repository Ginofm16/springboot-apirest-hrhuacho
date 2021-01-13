package com.hregional.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.Categoria;
import com.hregional.springboot.backend.apirest.models.entity.Especialidad;
import com.hregional.springboot.backend.apirest.models.entity.Personal;

public interface IPersonalHosDao extends JpaRepository<Personal, Long> {

	@Query("from Personal p where p.per_estado = true")
	public Page<Personal> findAllActive(Pageable pageable);
	
	@Query("from Personal p where p.per_estado = true")
	public List<Personal> findAllActiveEstado();
	
	@Query("from Categoria")
	public List<Categoria> findAllCategoria();
	
	@Query("from Especialidad")
	public List<Especialidad> findAllEspecialidad();
	
}
