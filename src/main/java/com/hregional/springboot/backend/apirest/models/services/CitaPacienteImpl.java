package com.hregional.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.Optional;

import com.hregional.springboot.backend.apirest.models.dao.IProgramacionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hregional.springboot.backend.apirest.models.dao.ICitaPacienteDao;
import com.hregional.springboot.backend.apirest.models.entity.CitaPaciente;
import com.hregional.springboot.backend.apirest.models.entity.Programacion;

@Service
public class CitaPacienteImpl implements ICitaPacienteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CitaPacienteImpl.class);

	@Autowired
	private ICitaPacienteDao citaPacienteDao;

	@Autowired
	private IProgramacionDao programacionDao;
	
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
		Programacion programacion;
		Optional<Programacion> programacionOptional = programacionDao.findById(citaPaciente.getProgramacion().getPro_codigo());
		if (programacionOptional.isPresent()){
			programacion = programacionOptional.get();
			programacion.setPro_num_turno(programacion.getPro_num_turno() -1);

		}else	{
			throw new RuntimeException("No exite la cita indicada");
		}
		Programacion programacionActualizado = programacionDao.save(programacion);
		LOGGER.info(programacionActualizado.toString());
		return citaPacienteDao.save(citaPaciente);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		citaPacienteDao.deleteById(id);
	}

}
