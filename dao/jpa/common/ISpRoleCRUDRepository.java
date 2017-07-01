package com.techmahindra.smartparking.dao.jpa.common;



import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganizationType;
import com.techmahindra.smartparking.pojo.dbentity.user.SpRole;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpRoleCRUDRepository extends CrudRepository<SpRole, Integer> {
	
	List<SpRole> findSpRoleBySpsOrganizationtype(SpOrganizationType type);
}
