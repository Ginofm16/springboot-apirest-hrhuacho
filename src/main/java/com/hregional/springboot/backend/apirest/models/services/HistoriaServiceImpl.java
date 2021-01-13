package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.IHistoriaDao;
import com.hregional.springboot.backend.apirest.models.entity.Historia;
import com.hregional.springboot.backend.apirest.models.entity.Pais;

@Service
public class HistoriaServiceImpl implements IHistoriaService {

	//inyectando historiaDao*/
	@Autowired
	private IHistoriaDao historiaDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Historia> findAll() {
		
		return (List<Historia>) historiaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Historia> findAll(Pageable pageable) {
		
		return historiaDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Historia findById(Long id) {
		// TODO Auto-generated method stub
		return historiaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Historia save(Historia historia) {
		// TODO Auto-generated method stub
		return historiaDao.save(historia);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		historiaDao.deleteById(id);
		
	}

	@Override
	public List<Pais> findAllPais() {
		// TODO Auto-generated method stub
		return historiaDao.findAllPais();
	}


	@Override
	public List<Historia> findByPaciente(String term) {
		// TODO Auto-generated method stub
		return historiaDao.findByPaciente(term);
	}

	@Override
	public List<Historia> findAllActive() {
		// TODO Auto-generated method stub
		return historiaDao.findAllActive();
	}

	@Override
	public Page<Historia> findAllActivePageable(Pageable pageable, String n) {
		// TODO Auto-generated method stub
		return historiaDao.findAllActivePageable(pageable, "n");
	}

}
