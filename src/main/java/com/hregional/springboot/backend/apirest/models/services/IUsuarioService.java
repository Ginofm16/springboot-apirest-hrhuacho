package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.entity.Role;
import com.hregional.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Page<Usuario> findAllActive(Pageable pageable);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
	
	public Usuario findByUsername(String username);
	
	public Personal findByUsuario(Long cod);
	
	public Role findByUsuarioRole(Long cod);
	
	
	
	
}
