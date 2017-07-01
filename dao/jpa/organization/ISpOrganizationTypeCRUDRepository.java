package com.techmahindra.smartparking.dao.jpa.organization;



import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrgTypeMap;
import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganization;
import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganizationType;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpOrganizationTypeCRUDRepository extends CrudRepository<SpOrganizationType, Integer> {
	
	SpOrganizationType findSpOrganizationTypeByTypeName(String name);
	 
}
