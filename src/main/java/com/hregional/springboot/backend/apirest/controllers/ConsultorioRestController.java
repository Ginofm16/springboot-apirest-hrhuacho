package com.hregional.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hregional.springboot.backend.apirest.models.entity.Consultorio;
import com.hregional.springboot.backend.apirest.models.entity.Historia;
import com.hregional.springboot.backend.apirest.models.services.IConsultorioService;
import com.hregional.springboot.backend.apirest.models.services.IHistoriaService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ConsultorioRestController {
	
	@Autowired
	private IConsultorioService consultorioService;
	
	@GetMapping("/consultorios")
	public List<Consultorio> index(){
		
		return consultorioService.findAll();
	}
	
	@GetMapping("/consultorios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Consultorio consultorio = null;
		/*Map es la interface y HashMap la implementacion. Map, va estar asociado a nombres(String),
		 * con sus valores(Object)*/
		Map<String, Object> response = new HashMap<>();
		
		/*try, para manejar error de sql, acceso, conexion, cualquier problema que se genere
		 * en el servidor con la base de datos*/
		try {
			 consultorio = consultorioService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//el estado cuando se presente el error, al ser un error en la base de datos seria de codigo 500
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		if(consultorio == null) {
			/*si es null entonces podemos crear y retornar el ResponseEntity con el mensaje de error
			 * pero para eso se tiene q contar con una MAP de java, un tipo map, que almacene 
			 * objetos y valores asociados a un nombre y le asignamos el mensaje de error*/
			response.put("mensaje", "La historia ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			//el estado cuando se presente el erroe de not_found sera el, 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		/*1er parametro sera el contenido que se va guardar en el cuerpo de la respuesta(ResponseBody).
		  2do param. es el estado de la respuesta http, seria un httpStatus, el estado seria el 200*/
		return new ResponseEntity<Consultorio>(consultorio, HttpStatus.OK);
	}
	
}
