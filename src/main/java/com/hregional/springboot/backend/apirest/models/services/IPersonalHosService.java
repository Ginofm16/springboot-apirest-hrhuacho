package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import com.hregional.springboot.backend.apirest.models.entity.TipoDocumento;
import com.hregional.springboot.backend.apirest.models.entity.UsuarioRegistro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hregional.springboot.backend.apirest.models.entity.Categoria;
import com.hregional.springboot.backend.apirest.models.entity.Especialidad;
import com.hregional.springboot.backend.apirest.models.entity.Personal;

public interface IPersonalHosService {

	public List<Personal> findAll();
	
	public Page<Personal> findAll(Pageable pageable);
	
	public Page<Personal> findAllActive(Pageable pageable);
	
	public List<Personal> findAllActiveEstado();
	
	public Personal findById(Long id);
	
	public Personal save(Personal personal);
	
	public void delete(Long id);
	
	public List<Categoria> findAllCategoria();
	
	public List<Especialidad> findAllEspecialidad();

	public List<TipoDocumento> findAllTipoDocumento();

	public Boolean findByCorreo(String correo);

	public Boolean findByUsuarioPersonal(String username);

	public UsuarioRegistro updatePersonalCredenciales(UsuarioRegistro usuarioRegistro);
}
