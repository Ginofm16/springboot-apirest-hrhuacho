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

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class HistoriaRestController {
	
	@Autowired
	private IHistoriaService historiaService;
	
	@GetMapping("/historias")
	public List<Historia> index(){
		
		return historiaService.findAllActive();
	}

	@GetMapping("/historias/page/{page}/codigo/{codigo}")
	public Page<Historia> index(@PathVariable Integer page, @PathVariable String codigo){
		Pageable pageable = PageRequest.of(page, 4);
		return historiaService.findAllActivePageable(pageable,"n");
	}
	
	@Secured({"ROLE_ADMIN","ROLE_INFORMATICO","ROLE_TECNICO"})
	@GetMapping("/historias/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Historia historia = null;
		Map<String, Object> response = new HashMap<>();

		try {
			 historia = historiaService.findById(id);
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(historia == null) {
			response.put("mensaje", "La historia ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Historia>(historia, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/historias")
	public ResponseEntity<?> create(@Valid @RequestBody Historia historia, BindingResult result) {
		Historia historiaNew = null;
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
		
		historiaActual.setHis_nombre(historia.getHis_nombre());
		historiaActual.setHis_ape_paterno(historia.getHis_ape_paterno());
		historiaActual.setHis_ape_materno(historia.getHis_ape_materno());
		historiaActual.setHis_direccion(historia.getHis_direccion());
		historiaActual.setHis_documento(historia.getHis_documento());
		historiaActual.setHis_fec_nacimiento(historia.getHis_fec_nacimiento());
		historiaActual.setHis_seguro(historia.getHis_seguro());
		historiaActual.setHis_genero(historia.getHis_genero());
		historiaActual.setEstudio(historia.getEstudio());
		historiaActual.setPais(historia.getPais());
		historiaActual.setTipo_documento(historia.getTipo_documento());
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
