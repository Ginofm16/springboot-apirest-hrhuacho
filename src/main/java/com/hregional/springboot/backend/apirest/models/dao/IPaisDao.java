package com.hregional.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hregional.springboot.backend.apirest.models.entity.Pais;

public interface IPaisDao extends JpaRepository<Pais, Long> {

}
