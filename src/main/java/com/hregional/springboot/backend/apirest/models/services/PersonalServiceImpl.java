package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import com.hregional.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.hregional.springboot.backend.apirest.models.entity.Role;
import com.hregional.springboot.backend.apirest.models.entity.TipoDocumento;
import com.hregional.springboot.backend.apirest.models.entity.Usuario;
import com.hregional.springboot.backend.apirest.models.entity.UsuarioRegistro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.IPersonalHosDao;
import com.hregional.springboot.backend.apirest.models.entity.Categoria;
import com.hregional.springboot.backend.apirest.models.entity.Especialidad;
import com.hregional.springboot.backend.apirest.models.entity.Personal;

@Service
public class PersonalServiceImpl implements IPersonalHosService{

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonalServiceImpl.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IPersonalHosDao personalDao;

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Personal> findAll() {
		// TODO Auto-generated method stub
		return (List<Personal>) personalDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Personal> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return personalDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Personal> findAllActive(Pageable pageable) {
		// TODO Auto-generated method stub
		return personalDao.findAllActive(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Personal findById(Long id) {
		// TODO Auto-generated method stub
		return personalDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Personal save(Personal personal) {
		// TODO Auto-generated method stub
		LOGGER.info("personal::::: {}", personal);
		Usuario usuario = new Usuario();
		Role role = new Role();
		Personal personalSaved;
		if (personal.getPassword() != null){
			personal.setPassword(passwordEncoder.encode(personal.getPassword()));
		}

		personalSaved  = personalDao.save(personal);
		LOGGER.info("personalSaved::::: {}", personalSaved);
		usuario.setPersonal(personalSaved);
		switch (personalSaved.getCategoria().getCat_nombre()) {
			case "administrador":
				role.setRol_codigo(1L);
				break;
			case "profesional tecnico" :
				role.setRol_codigo(3L);
				break;
			case "enfermero":
				role.setRol_codigo(4L);
				break;
			case "medico":
				role.setRol_codigo(5L);
				break;

		}
		usuario.setRole(role);
		usuario.setUsu_estado(true);
		LOGGER.info("Usuario Previo::::: {}", usuario);
		Usuario usuarioSaved = usuarioDao.save(usuario);
		LOGGER.info("Usuario Despues::::: {}", usuarioSaved);

		LOGGER.info(":::::Implement create::::::");
		LOGGER.info(personal.toString());
		return personalSaved;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		personalDao.deleteById(id);;
		
	}

	@Override
	public List<Categoria> findAllCategoria() {
		// TODO Auto-generated method stub
		return personalDao.findAllCategoria();
	}

	@Override
	public List<Especialidad> findAllEspecialidad() {
		// TODO Auto-generated method stub
		return personalDao.findAllEspecialidad();
	}

	@Override
	public List<TipoDocumento> findAllTipoDocumento() {
		return personalDao.findAllTipoDocumento();
	}

	@Override
	public Boolean findByCorreo(String correo) {
		if (personalDao.findByCorreo(correo) != null){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean findByUsuarioPersonal(String username) {
		if (personalDao.findByUsuarioPersonal(username) != null){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public UsuarioRegistro updatePersonalCredenciales(UsuarioRegistro usuarioRegistro) {
		LOGGER.info(":::::::::method updatePersonalCredenciales::::::::");
		Personal personal = personalDao.findByCorreo(usuarioRegistro.getCorreo());

		if (usuarioRegistro != null){
			personal.setUsername(usuarioRegistro.getUsuario());
			personal.setPassword(passwordEncoder.encode(usuarioRegistro.getPassword()));
		}
		LOGGER.info(personal.toString());
		personalDao.save(personal);
		usuarioRegistro.setConfirm_password("*******");
		usuarioRegistro.setPassword("*******");
		return usuarioRegistro;
	}

	@Override
	public List<Personal> findAllActiveEstado() {
		// TODO Auto-generated method stub
		return personalDao.findAllActiveEstado();
	}



}
