package com.hregional.springboot.backend.apirest.models.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.Personal;

public interface IPersonalDao extends JpaRepository<Personal, Long>{

	public Personal findByUsername(String username);
	
	//consulta a traves de la anotacion
	/*?1, se va replanzar por el primer parametro del metodo(username). 
	 *and u.otro=?2; ?2, para el segundo parametro*/
	@Query("select p from Personal p where p.username=?1")
	public Personal findByUsername2(String username);
	
	@Query("from Usuario u where u.usu_estado = true")
	public Page<Personal> findAllActive(Pageable pageable);
	

	
}
