package com.hregional.springboot.backend.apirest.models.services;

import com.hregional.springboot.backend.apirest.models.entity.Personal;

public interface IPersonalService {

	public Personal findByUsername(String username);
	

	
}
