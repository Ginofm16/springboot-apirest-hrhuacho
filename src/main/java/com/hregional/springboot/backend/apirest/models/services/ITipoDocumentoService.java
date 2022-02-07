package com.hregional.springboot.backend.apirest.models.services;

import com.hregional.springboot.backend.apirest.models.entity.Consultorio;
import com.hregional.springboot.backend.apirest.models.entity.TipoDocumento;

import java.util.List;

public interface ITipoDocumentoService {

    public List<TipoDocumento> findAll();

    public TipoDocumento findById(Long id);

    public TipoDocumento save(TipoDocumento tipoDocumento);

    public void delete(Long id);
}
