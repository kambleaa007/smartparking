package com.techmahindra.smartparking.dao.jpa.common;



import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import com.techmahindra.smartparking.pojo.dbentity.common.SpAddress;
import com.techmahindra.smartparking.pojo.dbentity.common.SpAddressType;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpAddressTypeCRUDRepository extends CrudRepository<SpAddressType, Integer> {
	
	
}
