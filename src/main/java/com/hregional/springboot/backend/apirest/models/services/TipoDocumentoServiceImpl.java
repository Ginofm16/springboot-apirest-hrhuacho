package com.hregional.springboot.backend.apirest.models.services;

import com.hregional.springboot.backend.apirest.models.dao.ITipoDocumentoDao;
import com.hregional.springboot.backend.apirest.models.entity.TipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService{

    @Autowired
    private ITipoDocumentoDao tipoDocumentoDao;

    @Override
    @Transactional(readOnly = true)
    public List<TipoDocumento> findAll() {
        return tipoDocumentoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoDocumento findById(Long id) {
        return tipoDocumentoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TipoDocumento save(TipoDocumento tipoDocumento) {
        return tipoDocumentoDao.save(tipoDocumento);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tipoDocumentoDao.deleteById(id);
    }
}
