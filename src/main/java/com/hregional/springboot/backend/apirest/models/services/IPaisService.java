package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hregional.springboot.backend.apirest.models.entity.Pais;

public interface IPaisService {
	
	public List<Pais> findAll();
	
	/*recibira objeto Pageable por argumento, que es una instancia de PageRequest que contiene en numero
	 * de pagina y la cantidad de pagina a mostrar, que retorna un Page<>, similar al list pero atraves de rangos*/
	public Page<Pais> findAll(Pageable pageable);
	
	public Pais findById(Long id);
	
	/*va recibir el Historia que se va almacenar y va retornar el Historia guardado y va retonar el 
	 * Historia guardado que ya va contener un ID*/
	public Pais save(Pais pais);
	
	public void delete(Long id);

}
