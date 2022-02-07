package com.hregional.springboot.backend.apirest.controllers;

import com.hregional.springboot.backend.apirest.models.entity.TipoDocumento;
import com.hregional.springboot.backend.apirest.models.services.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TipoDocumentoController {

    @Autowired
    private ITipoDocumentoService tipoDocumentoService;

    @Secured({"ROLE_ADMIN","ROLE_INFORMATICO","ROLE_TECNICO"})
    @GetMapping("/tipo-documento")
    public List<TipoDocumento> index(){
        return tipoDocumentoService.findAll();
    }

    @Secured({"ROLE_ADMIN","ROLE_INFORMATICO","ROLE_TECNICO"})
    @GetMapping("/tipo-documento/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){

        TipoDocumento tipoDocumento;

        Map<String, Object> response = new HashMap<>();

        try{
            tipoDocumento = tipoDocumentoService.findById(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al consultar la base de datos por id");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tipoDocumento == null){
            response.put("mensaje","El tipo de documento ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(tipoDocumento, HttpStatus.OK);
    }

}
