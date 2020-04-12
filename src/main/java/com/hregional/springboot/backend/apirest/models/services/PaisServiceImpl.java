package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.IPaisDao;
import com.hregional.springboot.backend.apirest.models.entity.Pais;

@Service
public class PaisServiceImpl implements IPaisService {

	@Autowired
	/*inyectar el paisDao*/
	private IPaisDao paisDao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Pais> findAll() {
		
		return (List<Pais>) paisDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Pais> findAll(Pageable pageable) {
		
		return paisDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Pais findById(Long id) {
		// TODO Auto-generated method stub
		return paisDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pais save(Pais Pais) {
		// TODO Auto-generated method stub
		return paisDao.save(Pais);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		paisDao.deleteById(id);
		
	}

}
