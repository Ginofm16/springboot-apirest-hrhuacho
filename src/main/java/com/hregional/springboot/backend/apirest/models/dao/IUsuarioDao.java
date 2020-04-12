package com.hregional.springboot.backend.apirest.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.entity.Role;
import com.hregional.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	@Query("from Usuario u where u.usu_estado = true")
	public Page<Usuario> findAllActive(Pageable pageable);
	
	@Query("SELECT u from Usuario u where u.personal.username = ?1")
	public Usuario findByUsername(String username);
	
	@Query("SELECT p from Usuario u JOIN u.personal p where u.usu_codigo = ?1")
	public Personal findByUsuario(Long cod);
	
	@Query("SELECT r from Usuario u JOIN u.role r where u.usu_codigo = ?1")
	public Role findByRol(Long cod);
}
