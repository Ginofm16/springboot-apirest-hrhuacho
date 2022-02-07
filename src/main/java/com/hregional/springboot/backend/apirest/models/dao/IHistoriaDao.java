package com.hregional.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hregional.springboot.backend.apirest.models.entity.Historia;
import com.hregional.springboot.backend.apirest.models.entity.Pais;

/*JpaRepository a diferencia de CrudRepository nos proporciona unas funcionalidades adicionales como
* paginar y ordenamiento*/
public interface IHistoriaDao extends JpaRepository<Historia, Long>{

	@Query("from Pais")
	public List<Pais> findAllPais();
	
	@Query("from Historia h where h.his_estado = true")
	public Page<Historia> findAllActivePageable(Pageable pageable, String n);
	 
	@Query("from Historia h where h.his_estado = true")
	public List<Historia> findAllActive();
	
	@Query("select h from Historia h where concat(h.his_ape_paterno,' ',h.his_ape_materno) like ?1%")
	public List<Historia> findByPaciente(String term);
}
 