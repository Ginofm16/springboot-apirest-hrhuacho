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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hregional.springboot.backend.apirest.models.entity.Consultorio;
import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.entity.Programacion;
import com.hregional.springboot.backend.apirest.models.services.IProgramacionService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProgramacionRestController {
	
	@Autowired
	private IProgramacionService programacionService;
	
	@GetMapping("/programacion")
	public List<Programacion> index(){
		
		return programacionService.findAllActive();
	}
	
	@GetMapping("/programacion/page/{page}")
	public Page<Programacion> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return programacionService.findAll(pageable);
	}
	
	@GetMapping("/programacion/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Programacion programacion = null;
		/*Map es la interface y HashMap la implementacion. Map, va estar asociado a nombres(String),
		 * con sus valores(Object)*/
		Map<String, Object> response = new HashMap<>();
		
		/*try, para manejar error de sql, acceso, conexion, cualquier problema que se genere
		 * en el servidor con la base de datos*/
		try {
			 programacion = programacionService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//el estado cuando se presente el error, al ser un error en la base de datos seria de codigo 500
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		if(programacion == null) {
			/*si es null entonces podemos crear y retornar el ResponseEntity con el mensaje de error
			 * pero para eso se tiene q contar con una MAP de java, un tipo map, que almacene 
			 * objetos y valores asociados a un nombre y le asignamos el mensaje de error*/
			response.put("mensaje", "La programacion ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			//el estado cuando se presente el erroe de not_found sera el, 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		/*1er parametro sera el contenido que se va guardar en el cuerpo de la respuesta(ResponseBody).
		  2do param. es el estado de la respuesta http, seria un httpStatus, el estado seria el 200*/
		return new ResponseEntity<Programacion>(programacion, HttpStatus.OK);
	}

	@PostMapping("/programacion")
	public ResponseEntity<?> create(@Valid @RequestBody Programacion programacion, BindingResult result) {
		System.out.println("programacion::::::::::");
		Programacion programacionNew = null;
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
			
			programacionNew=programacionService.save(programacion);
		} catch(DataAccessException e) {
		
		response.put("mensaje", "Error al realizar el insert en la base de datos");
		response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","La programacion ha sido creado con éxito");
		response.put("programacion", programacionNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/programacion/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Programacion programacion, BindingResult result, @PathVariable Long id) {
		
		System.out.println("UPDATEEEEEEEEEEEEEEE");
		
		Programacion programacionActual = programacionService.findById(id);
		
		Programacion programacionUpdated = null;
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
		
		
		if(programacionActual == null) {
			response.put("mensaje", "Error: no se puede editar, la programacion ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
	
		programacionActual.setPro_fecha(programacion.getPro_fecha());
		programacionActual.setPro_hora_inicio(programacionActual.getPro_hora_inicio());
		programacionActual.setPro_num_turno(programacion.getPro_num_turno());
		programacionActual.setUsuario(programacion.getUsuario());
		programacionActual.setConsultorio(programacion.getConsultorio());
		programacionActual.setPro_estado(programacion.getPro_estado());
		programacionUpdated = programacionService.save(programacionActual);
		
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La programación ha sido actualizado con éxito");
		response.put("programacion", programacionUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/programacion/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		
			programacionService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la programacion en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La programacion ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/programacion/personal")
	public List<Personal> listarPersonal(){
		return programacionService.findAllPersonal();
	}
	
	@GetMapping("/programacion/consultorio")
	public List<Consultorio> listarConsultorio(){
		return programacionService.findAllConsultorio();
	}
	
	@GetMapping("/programacion/page/{page}/codigo/{codigo}")
	public List<Programacion> findAllProgramacionByConsultorio(@PathVariable Integer page, @PathVariable String codigo){
		Pageable pageable = PageRequest.of(page, 4);
		return programacionService.findAllProgramacionByConsultorio(pageable,codigo);
	}
	
	@GetMapping("/programacion/consultorio/{codigo}")
	public List<Programacion> findAllProgramacionByConsul(@PathVariable Long codigo){
		return programacionService.findAllProgramacionByConsul(codigo);
	}
	
	@GetMapping("/programacion/pro-consultorio/{consultorio}")
	public List<Programacion> findProgramacionByConsul(@PathVariable String consultorio){
		return programacionService.findProgramacionByConsul(consultorio);
	}
}
