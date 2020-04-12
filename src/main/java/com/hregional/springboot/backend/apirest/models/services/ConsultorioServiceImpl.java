package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.IConsultorioDao;
import com.hregional.springboot.backend.apirest.models.entity.Consultorio;

@Service
public class ConsultorioServiceImpl implements IConsultorioService {

	@Autowired
	private IConsultorioDao consulDao;
	
	@Override
	@Transactional
	public List<Consultorio> findAll() {
		// TODO Auto-generated method stub
		return (List<Consultorio>) consulDao.findAll();
	}

	@Override
	@Transactional
	public Consultorio findById(Long id) {
		// TODO Auto-generated method stub
		return consulDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Consultorio save(Consultorio consultorio) {
		// TODO Auto-generated method stub
		return consulDao.save(consultorio);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		consulDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public List<Consultorio> findByConsultorio(String term) {
		// TODO Auto-generated method stub
		return consulDao.findByConsultorio(term);
	}

	
}
