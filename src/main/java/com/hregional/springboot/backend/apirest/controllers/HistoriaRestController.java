package com.hregional.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hregional.springboot.backend.apirest.models.entity.Historia;
import com.hregional.springboot.backend.apirest.models.entity.Pais;
import com.hregional.springboot.backend.apirest.models.services.IHistoriaService;

//http://localhost:4200, dominio de angular
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class HistoriaRestController {
	
	@Autowired
	private IHistoriaService historiaService;
	
	@GetMapping("/historias")
	public List<Historia> index(){
		
		return historiaService.findAll();
	}

	@GetMapping("/historias/page/{page}")
	public Page<Historia> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return historiaService.findAll(pageable);
	}
	
	/*
	//mostrando lista de historia con estado activo
	@GetMapping("/historias/page/{page}")
	public Page<Historia> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return historiaService.findAllActive(pageable);
	}*/
	
	@Secured({"ROLE_ADMIN","ROLE_INFORMATICO","ROLE_TECNICO"})
	@GetMapping("/historias/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Historia historia = null;
		/*Map es la interface y HashMap la implementacion. Map, va estar asociado a nombres(String),
		 * con sus valores(Object)*/
		Map<String, Object> response = new HashMap<>();
		
		/*try, para manejar error de sql, acceso, conexion, cualquier problema que se genere
		 * en el servidor con la base de datos*/
		try {
			 historia = historiaService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//el estado cuando se presente el error, al ser un error en la base de datos seria de codigo 500
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		if(historia == null) {
			/*si es null entonces podemos crear y retornar el ResponseEntity con el mensaje de error
			 * pero para eso se tiene q contar con una MAP de java, un tipo map, que almacene 
			 * objetos y valores asociados a un nombre y le asignamos el mensaje de error*/
			response.put("mensaje", "La historia ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			//el estado cuando se presente el erroe de not_found sera el, 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		/*1er parametro sera el contenido que se va guardar en el cuerpo de la respuesta(ResponseBody).
		  2do param. es el estado de la respuesta http, seria un httpStatus, el estado seria el 200*/
		return new ResponseEntity<Historia>(historia, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/historias")
	/*Historia (del parametro), como viene en formato JSON dentro del cuerpo de la peticion del
	   request, entonces aca tenemos que indicar que es un @RequestBody.
	 * Se configura para que antes de entrar al metodo create se tiene que validar y lo hacemos atraves
	   de un interceptor de spring que intercepta el objeto Historia y valida cada valor, cada atributo,
	   del @RequestBody que esta enviando angular que esta enviando en una estructura json, la que se 
	   traduce a la clase Historia. Todo ello se indica con la anotacion @Valid,luego se tiene que inyectar 
	   al metodo el objeto que contiene el mensaje de error*/
	public ResponseEntity<?> create(@Valid @RequestBody Historia historia, BindingResult result) {
		
		Historia historiaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		/*evaluando si contiene errores*/
		if(result.hasErrors()) {
			
			/* para el manejo de errores de validacion*/
			List<String> errors = result.getFieldErrors()
					//convertir la lista de FieldErrors en un stream, apartir de este flujo, de stream....
					.stream()
					.map(err -> "EL campo "+ err.getField()+"' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			/*bad_request, es un error para validacion, de codigo 400*/
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			historiaNew=historiaService.save(historia);
		} catch(DataAccessException e) {
		
		response.put("mensaje", "Error al realizar el insert en la base de datos");
		response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El Historia ha sido creado con éxito");
		response.put("historia", historiaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({"ROLE_ADMIN","ROLE_INFORMATICO"})
	@PutMapping("/historias/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Historia historia, BindingResult result, @PathVariable Long id) {
		
		Historia historiaActual = historiaService.findById(id);
		Historia historiaUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		/*evaluando si contiene errores*/
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "EL campo "+ err.getField()+"' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		if(historiaActual == null) {
			response.put("mensaje", "Error: no se puede editar, el Historia ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		/*Historia, sera el Historia que contiene los datos que se quieren cambiar*/
		
		historiaActual.setHis_nombre(historia.getHis_nombre());
		historiaActual.setHis_ape_paterno(historia.getHis_ape_paterno());
		historiaActual.setHis_ape_materno(historia.getHis_ape_materno());
		historiaActual.setHis_direccion(historia.getHis_direccion());
		historiaActual.setHis_dni(historia.getHis_dni());
		historiaActual.setHis_fec_nacimiento(historia.getHis_fec_nacimiento());
		historiaActual.setHis_seguro(historia.getHis_seguro());
		historiaActual.setHis_genero(historia.getHis_genero());
		historiaActual.setHis_gra_estudio(historia.getHis_gra_estudio());
		historiaActual.setPais(historia.getPais());
		historiaActual.setHis_estado(historia.getHis_estado());
		historiaUpdated = historiaService.save(historiaActual);
		
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Historia ha sido actualizado con éxito");
		response.put("historia", historiaUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/historias/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		
			historiaService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la historia en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La historia ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/historias/paises")
	public List<Pais> listarPaises(){
		return historiaService.findAllPais();
	}
	
	@GetMapping("/historias/filtrar-historias/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<Historia> filtrarProductos(@PathVariable String term){
		return historiaService.findByPaciente(term);
	}
	
	
}
