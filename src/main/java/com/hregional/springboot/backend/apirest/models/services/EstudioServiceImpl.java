package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.IEstudioDao;
import com.hregional.springboot.backend.apirest.models.entity.Estudio;

@Service
public class EstudioServiceImpl implements IEstudioService {

	@Autowired
	private IEstudioDao estudioDao;
	
	@Override
	@Transactional
	public List<Estudio> findAll() {
		// TODO Auto-generated method stub
		return (List<Estudio>) estudioDao.findAll();
	}

	@Override
	@Transactional
	public Estudio findById(Long id) {
		// TODO Auto-generated method stub
		return estudioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Estudio save(Estudio estudio) {
		// TODO Auto-generated method stub
		return estudioDao.save(estudio);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		estudioDao.deleteById(id);
		
	}

	@Override
	public List<Estudio> findAllActive() {
		// TODO Auto-generated method stub
		return estudioDao.findAllActive();
	}


	
}
