package com.techmahindra.smartparking.dao.jpa.device;



import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.techmahindra.smartparking.pojo.dbentity.device.SpResource;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpResourceCRUDRepository extends CrudRepository<SpResource, Integer> {
	
	List<SpResource> findSpResourceTypeByStatus(@Param("status") boolean b);
}
