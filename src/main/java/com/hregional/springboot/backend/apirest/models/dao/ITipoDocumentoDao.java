package com.hregional.springboot.backend.apirest.models.dao;

import com.hregional.springboot.backend.apirest.models.entity.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoDocumentoDao extends JpaRepository<TipoDocumento, Long> {
}
