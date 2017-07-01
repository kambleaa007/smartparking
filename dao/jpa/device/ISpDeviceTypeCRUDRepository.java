package com.techmahindra.smartparking.dao.jpa.device;



import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.techmahindra.smartparking.pojo.dbentity.device.SpDeviceType;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpDeviceTypeCRUDRepository extends CrudRepository<SpDeviceType, Integer> {
	
	List<SpDeviceType> findSpDeviceTypeByStatus(@Param("status") boolean b);
}
