package com.hregional.springboot.backend.apirest.models.services;

import com.hregional.springboot.backend.apirest.models.dao.ICitaPacienteDao;
import com.hregional.springboot.backend.apirest.models.entity.CitaPaciente;
import com.hregional.springboot.backend.apirest.models.entity.CitaReporte;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReporteServiceImpl implements IReporteService{

    @Autowired
    ICitaPacienteDao citaPacienteDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReporteServiceImpl.class);

    @Override
    public byte[] generarReporte() {

        List<CitaPaciente> citaPacienteList = citaPacienteDao.findAllActive();

        CitaPaciente citaPaciente = citaPacienteList.stream()
                .max(Comparator.comparingInt(x -> Math.toIntExact(x.getCit_codigo())))
                .get();

        LOGGER.info(":::::CITAPACIENTE::: {}", citaPaciente);

        List<CitaReporte> citaReporteList = new ArrayList<>();
        CitaReporte citaReporte = new CitaReporte();
        citaReporte.setPaciente(citaPaciente.getHistoria().getHis_nombre().concat(" ").concat(citaPaciente.getHistoria().getHis_ape_paterno().concat(" ").concat(citaPaciente.getHistoria().getHis_ape_materno())));
        citaReporte.setConsultorio(citaPaciente.getProgramacion().getConsultorio().getCon_nombre());
        citaReporte.setMedico(citaPaciente.getProgramacion().getUsuario_medico().getPersonal().getPer_nombre().concat(" ")
                .concat(citaPaciente.getProgramacion().getUsuario_medico().getPersonal().getPer_ape_paterno().concat(" ")
                        .concat(citaPaciente.getProgramacion().getUsuario_medico().getPersonal().getPer_ape_materno())));
        citaReporte.setFecha(citaPaciente.getProgramacion().getPro_fecha().toString().concat(", Hora: ").concat(citaPaciente.getProgramacion().getPro_hora_inicio()));
        citaReporteList.add(citaReporte);

        LOGGER.info(":::::CITAREPORTE::: {}", citaReporte);

        byte[] data = null;

        Map<String, Object> parameters = new HashMap<>();
//        parametros.put("txt_titulo", "Prueba de titulo");

        try {
            File file = new ClassPathResource("/reports/ticket-cita.jasper").getFile();
            JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(citaReporteList));
            LOGGER.info(":::::JasperPrint:::");
            data = JasperExportManager.exportReportToPdf(print);
        }catch (Exception e){
            e.printStackTrace();
        }

        return data;

    }

}
