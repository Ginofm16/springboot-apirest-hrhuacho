package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hregional.springboot.backend.apirest.models.entity.Consultorio;
import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.entity.Programacion;

public interface IProgramacionService {
	
public List<Programacion> findAll();
	
	public Page<Programacion> findAll(Pageable pageable);
	
	public Page<Programacion> findAllActivePageable(Pageable pageable);
	
	public List<Programacion> findAllActive();
	
	public Programacion findById(Long id);
	
	public Programacion save(Programacion historia);
	
	public void delete(Long id);
	
	public List<Personal> findAllPersonal();
	
	public List<Consultorio> findAllConsultorio();
	
	public List<Programacion> findAllProgramacionByConsultorio(Pageable pageable, String codigo);
	
	public List<Programacion> findAllProgramacionByConsul(Long codigo);

}
