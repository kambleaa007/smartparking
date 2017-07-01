package com.techmahindra.smartparking.dao.jpa.user;

import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import com.techmahindra.smartparking.pojo.dbentity.user.SpRole;
import com.techmahindra.smartparking.pojo.dbentity.user.SpRoleToPermissionsMap;

/**
 * IAddressTypeCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpRoleToPermissionMapRepository extends CrudRepository<SpRoleToPermissionsMap, Integer> {

	
	List<SpRoleToPermissionsMap> findSpRoleToPermissionsMapBySpsRole(SpRole spsRole);
}
