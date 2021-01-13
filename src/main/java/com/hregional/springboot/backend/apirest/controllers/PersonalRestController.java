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

import com.hregional.springboot.backend.apirest.models.entity.Categoria;
import com.hregional.springboot.backend.apirest.models.entity.Especialidad;
import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.services.IPersonalHosService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PersonalRestController {

	@Autowired
	private IPersonalHosService personalService;
	
	@GetMapping("/personal")
	public List<Personal> index(){
		
		return personalService.findAllActiveEstado();
	}
	
	@GetMapping("/personal/page/{page}")
	public Page<Personal> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return personalService.findAll(pageable);
	}
	
	@GetMapping("/personal/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		
		Personal personal = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			personal = personalService.findById(id);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//el estado cuando se presente el error, al ser un error en la base de datos seria de codigo 500
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(personal == null) {
			/*si es null entonces podemos crear y retornar el ResponseEntity con el mensaje de error
			 * pero para eso se tiene q contar con una MAP de java, un tipo map, que almacene 
			 * objetos y valores asociados a un nombre y le asignamos el mensaje de error*/
			response.put("mensaje", "El personal ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			//el estado cuando se presente el erroe de not_found sera el, 404
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Personal>(personal, HttpStatus.OK);
	}
	
	@PostMapping("/personal")
	public ResponseEntity<?> create(@Valid @RequestBody Personal personal, BindingResult result){
		
		Personal personalNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "EL campo "+ err.getField()+"' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			/*bad_request, es un error para validacion, de codigo 400*/
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			
		}
		
		try {
			personalNew = personalService.save(personal);
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El personal ha sido creado con éxito");
		response.put("personal", personalNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/personal/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Personal personal, BindingResult result, @PathVariable Long id){
		
		Personal personalActual = personalService.findById(id);
		Personal personalUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "EL campo "+ err.getField()+"' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(personalActual == null) {
			response.put("mensaje", "Error: no se puede editar, el Personal ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			personalActual.setPer_nombre(personal.getPer_nombre());
			personalActual.setPer_ape_paterno(personal.getPer_ape_paterno());
			personalActual.setPer_ape_materno(personal.getPer_ape_materno());
			personalActual.setPer_dni(personal.getPer_dni());
			personalActual.setPer_direccion(personal.getPer_direccion());
			personalActual.setPer_telefono(personal.getPer_telefono());
			personalActual.setPer_rne(personal.getPer_rne());
			personalActual.setPer_fec_ingreso(personal.getPer_fec_ingreso());
			personalActual.setPer_fec_salida(personal.getPer_fec_salida());
			personalActual.setUsername(personal.getUsername());
			personalActual.setPassword(personal.getPassword());
			personalActual.setCategoria(personal.getCategoria());
			personalActual.setEspecialidad(personal.getEspecialidad());
			personalUpdated = personalService.save(personalActual);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Personal ha sido actualizado con éxito");
		response.put("historia", personalUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/personal/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
		
			personalService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al eliminar al personal en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","-El personal ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/personal/categoria")
	public List<Categoria> listarCategoria(){
		return personalService.findAllCategoria();
	}
	
	@GetMapping("/personal/especialidad")
	public List<Especialidad> listarEspecialidad(){
		return personalService.findAllEspecialidad();
	}
	
}
