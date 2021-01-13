package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.ICitaPacienteDao;
import com.hregional.springboot.backend.apirest.models.entity.CitaPaciente;
import com.hregional.springboot.backend.apirest.models.entity.Programacion;

@Service
public class CitaPacienteImpl implements ICitaPacienteService {

	@Autowired
	private ICitaPacienteDao citaPacienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CitaPaciente> findAll() {
		// TODO Auto-generated method stub
		return (List<CitaPaciente>) citaPacienteDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CitaPaciente> findAllActive() {
		// TODO Auto-generated method stub
		return (List<CitaPaciente>) citaPacienteDao.findAllActive();
	}

	@Override
	@Transactional(readOnly = true)
	public CitaPaciente findById(Long id) {
		// TODO Auto-generated method stub
		return citaPacienteDao.findById(id).orElse(null);
	}

	@Override
	public CitaPaciente save(CitaPaciente citaPaciente) {
		// TODO Auto-generated method stub
		return citaPacienteDao.save(citaPaciente);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		citaPacienteDao.deleteById(id);
	}

}
