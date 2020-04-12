package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import com.hregional.springboot.backend.apirest.models.entity.Consultorio;

public interface IConsultorioService {
	
	public List<Consultorio> findAll();
	
	public Consultorio findById(Long id);
	
	/*va recibir el Historia que se va almacenar y va retornar el Historia guardado y va retonar el 
	 * Historia guardado que ya va contener un ID*/
	public Consultorio save(Consultorio consultorio);
	
	public void delete(Long id);

	public List<Consultorio> findByConsultorio(String term);
	
	
	
}
