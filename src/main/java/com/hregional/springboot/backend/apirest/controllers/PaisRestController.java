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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hregional.springboot.backend.apirest.models.entity.Pais;
import com.hregional.springboot.backend.apirest.models.services.IPaisService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PaisRestController {
	
	@Autowired
	private IPaisService paisService;
	
	@GetMapping("/paises")
	public List<Pais> index(){
		
		return paisService.findAll();
	}
	
	@GetMapping("/paises/page/{page}")
	public Page<Pais> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return paisService.findAll(pageable);
	}
	
	@PostMapping("/paises")
	public ResponseEntity<?> create(@Valid @RequestBody Pais pais, BindingResult result) {
		
		Pais paisNew = null;
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
			paisNew=paisService.save(pais);
		} catch(DataAccessException e) {
		
		response.put("mensaje", "Error al realizar el insert en la base de datos");
		response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El Pais ha sido creado con éxito");
		response.put("pais", paisNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
