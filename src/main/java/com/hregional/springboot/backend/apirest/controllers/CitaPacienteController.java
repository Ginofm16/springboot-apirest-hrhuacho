package com.hregional.springboot.backend.apirest.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.hregional.springboot.backend.apirest.models.entity.CitaPaciente;
import com.hregional.springboot.backend.apirest.models.entity.CitaPaciente;
import com.hregional.springboot.backend.apirest.models.services.ICitaPacienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CitaPacienteController {

	private final static Logger LOGGER = LoggerFactory.getLogger(CitaPacienteController.class);

	@Autowired
	private ICitaPacienteService citaPacienteService;

	@GetMapping("/citaPaciente")
	public List<CitaPaciente> index(){
		
		return citaPacienteService.findAllActive();
	}

	
	@GetMapping("/citaPaciente/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		CitaPaciente citaPaciente = null;
		/*Map es la interface y HashMap la implementacion. Map, va estar asociado a nombres(String),
		 * con sus valores(Object)*/
		Map<String, Object> response = new HashMap<>();
		
		/*try, para manejar error de sql, acceso, conexion, cualquier problema que se genere
		 * en el servidor con la base de datos*/
		try {
			 citaPaciente = citaPacienteService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//el estado cuando se presente el error, al ser un error en la base de datos seria de codigo 500
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		if(citaPaciente == null) {
			/*si es null entonces podemos crear y retornar el ResponseEntity con el mensaje de error
			 * pero para eso se tiene q contar con una MAP de java, un tipo map, que almacene 
			 * objetos y valores asociados a un nombre y le asignamos el mensaje de error*/
			response.put("mensaje", "La citaPaciente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			//el estado cuando se presente el erroe de not_found sera el, 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		/*1er parametro sera el contenido que se va guardar en el cuerpo de la respuesta(ResponseBody).
		  2do param. es el estado de la respuesta http, seria un httpStatus, el estado seria el 200*/
		return new ResponseEntity<CitaPaciente>(citaPaciente, HttpStatus.OK);
	}

	@PostMapping("/citaPaciente")
	public ResponseEntity<?> create(@Valid @RequestBody CitaPaciente citaPaciente, BindingResult result) {

		LocalDateTime ahora= LocalDateTime.now();
		
		CitaPaciente citaPacienteNew = null;
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
			
			//citaPaciente.setCit_fec_registro(hourdateFormat.format(date).);
			citaPacienteNew=citaPacienteService.save(citaPaciente);
		} catch(DataAccessException e) {
		
		response.put("mensaje", "Error al realizar el insert en la base de datos");
		response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e){
			LOGGER.info(e.getMessage()+" - "+ e.getCause().getMessage());
		}
		
		response.put("mensaje","La citaPaciente ha sido creado con éxito");
		response.put("citaPaciente", citaPacienteNew);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/citaPaciente/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CitaPaciente citaPaciente, BindingResult result, @PathVariable Long id) {
			
		CitaPaciente citaPacienteActual = citaPacienteService.findById(id);
		
		CitaPaciente citaPacienteUpdated = null;
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
		
		
		if(citaPacienteActual == null) {
			response.put("mensaje", "Error: no se puede editar, la citaPaciente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
	
		citaPacienteActual.setCit_exoneracion(citaPaciente.getCit_exoneracion());
		citaPacienteActual.setCit_costo_total(citaPacienteActual.getCit_costo_total());
		//citaPacienteActual.setCit_fec_registro(citaPaciente.getCit_fec_registro());
		citaPacienteActual.setHistoria(citaPaciente.getHistoria());
		citaPacienteActual.setProgramacion(citaPaciente.getProgramacion());
		citaPacienteActual.setUsuario(citaPaciente.getUsuario());
		citaPacienteActual.setCit_estado(citaPaciente.getCit_estado());
		citaPacienteUpdated = citaPacienteService.save(citaPacienteActual);
		
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La cita ha sido actualizado con éxito");
		response.put("citaPaciente", citaPacienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/citaPaciente/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		
			citaPacienteService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar la cita en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La cita ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
}
