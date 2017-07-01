package com.techmahindra.smartparking.dao.jpa.common;



import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.techmahindra.smartparking.pojo.dbentity.parking.SpSlotType;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpSlotTypeCRUDRepository extends CrudRepository<SpSlotType, Integer> {
	
	List<SpSlotType> findSpSlotTypeByStatus(@Param("status") boolean b);
}
