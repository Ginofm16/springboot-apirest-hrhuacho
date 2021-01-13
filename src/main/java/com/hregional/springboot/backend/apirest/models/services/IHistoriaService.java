package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hregional.springboot.backend.apirest.models.entity.Historia;
import com.hregional.springboot.backend.apirest.models.entity.Pais;

public interface IHistoriaService {

public List<Historia> findAll();
	
	/*recibira objeto Pageable por argumento, que es una instancia de PageRequest que contiene en numero
	 * de pagina y la cantidad de pagina a mostrar, que retorna un Page<>, similar al list pero atraves de rangos*/
	public Page<Historia> findAll(Pageable pageable);
	
	public Page<Historia> findAllActivePageable(Pageable pageable, String n);

	public List<Historia> findAllActive();
	
	public Historia findById(Long id);
	
	/*va recibir el Historia que se va almacenar y va retornar el Historia guardado y va retonar el 
	 * Historia guardado que ya va contener un ID*/
	public Historia save(Historia historia);
	
	public void delete(Long id);
	
	public List<Pais> findAllPais();
	
	public List<Historia> findByPaciente(String term);
	
}
