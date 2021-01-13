package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.IPersonalHosDao;
import com.hregional.springboot.backend.apirest.models.entity.Categoria;
import com.hregional.springboot.backend.apirest.models.entity.Especialidad;
import com.hregional.springboot.backend.apirest.models.entity.Personal;

@Service
public class PersonalServiceImpl implements IPersonalHosService{

	@Autowired
	private IPersonalHosDao personalDao;

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
	@Transactional(readOnly = true)
	public Personal save(Personal personal) {
		// TODO Auto-generated method stub
		return personalDao.save(personal);
	}

	@Override
	@Transactional(readOnly = true)
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
	public List<Personal> findAllActiveEstado() {
		// TODO Auto-generated method stub
		return personalDao.findAllActiveEstado();
	}



}
