package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.IProgramacionDao;
import com.hregional.springboot.backend.apirest.models.entity.Consultorio;
import com.hregional.springboot.backend.apirest.models.entity.Personal;
import com.hregional.springboot.backend.apirest.models.entity.Programacion;

@Service
public class ProgramacionServiceImpl implements IProgramacionService {

	@Autowired
	private IProgramacionDao programacionDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Programacion> findAll() {
		// TODO Auto-generated method stub
		return (List<Programacion>) programacionDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Programacion> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return programacionDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Programacion> findAllActivePageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return programacionDao.findAllActivePageable(pageable);
	}

	@Override
	public Programacion findById(Long id) {
		// TODO Auto-generated method stub
		return programacionDao.findById(id).orElse(null);
	}

	@Override
	public Programacion save(Programacion historia) {
		// TODO Auto-generated method stub
		return programacionDao.save(historia);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		programacionDao.deleteById(id);
	}

	@Override
	public List<Personal> findAllPersonal() {
		// TODO Auto-generated method stub
		return programacionDao.findAllPersonal();
	}

	@Override
	public List<Consultorio> findAllConsultorio() {
		// TODO Auto-generated method stub
		return programacionDao.findAllConsultorio();
	}

	@Override
	public List<Programacion> findAllProgramacionByConsultorio(Pageable pageable, String codigo) {
		// TODO Auto-generated method stub
		return programacionDao.findAllProgramacionByConsultorio(pageable, codigo);
	}
	
	@Override
	public List<Programacion> findAllProgramacionByConsul(Long codigo) {
		// TODO Auto-generated method stub
		return programacionDao.findAllProgramacionByConsul(codigo);
	}

	@Override
	public List<Programacion> findAllActive() {
		// TODO Auto-generated method stub
		return programacionDao.findAllActive();
	}

	@Override
	public List<Programacion> findProgramacionByConsul(String consultorio) {
		// TODO Auto-generated method stub
		return programacionDao.findByProgramacion(consultorio);
	}

}
