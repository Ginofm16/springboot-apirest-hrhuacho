package com.hregional.springboot.backend.apirest.models.dao;

import java.util.List;

import com.hregional.springboot.backend.apirest.models.entity.TipoDocumento;
import com.hregional.springboot.backend.apirest.models.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.Categoria;
import com.hregional.springboot.backend.apirest.models.entity.Especialidad;
import com.hregional.springboot.backend.apirest.models.entity.Personal;

public interface IPersonalHosDao extends JpaRepository<Personal, Long> {

	@Query("from Personal p where p.per_estado = true")
	public Page<Personal> findAllActive(Pageable pageable);

	@Query("from Personal p where p.per_estado = true")
	public List<Personal> findAllActiveEstado();

	@Query("from Categoria")
	public List<Categoria> findAllCategoria();

	@Query("from Especialidad")
	public List<Especialidad> findAllEspecialidad();

	@Query("from TipoDocumento")
	public List<TipoDocumento> findAllTipoDocumento();

	@Query("SELECT u from Usuario u where u.personal.username = ?1")
	public Usuario findByUsername(String username);

	@Query("SELECT pe from Personal pe where pe.per_correo = ?1")
	public Personal findByCorreo(String correo);

	@Query("SELECT pe from Personal pe where pe.username = ?1")
	public Personal findByUsuarioPersonal(String correo);

}
