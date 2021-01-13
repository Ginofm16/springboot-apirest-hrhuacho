package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import com.hregional.springboot.backend.apirest.models.entity.Estudio;

public interface IEstudioService {
	
	public List<Estudio> findAll();
	
	public Estudio findById(Long id);
	
	/*va recibir el Historia que se va almacenar y va retornar el Historia guardado y va retonar el 
	 * Historia guardado que ya va contener un ID*/
	public Estudio save(Estudio estudio);
	
	public void delete(Long id);

	public List<Estudio> findAllActive();
	
	
	
}
