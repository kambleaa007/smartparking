package com.techmahindra.smartparking.dao.jpa.organization;



import java.util.ArrayList;

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
public interface ISpOrgTypeMapCRUDRepository extends CrudRepository<SpOrgTypeMap, Integer> {
	
	ArrayList<SpOrgTypeMap> findSpOrgTypeMapByType(SpOrganizationType name);
	ArrayList<SpOrgTypeMap> findSpOrgTypeMapByOrganization(SpOrganization name);
}
