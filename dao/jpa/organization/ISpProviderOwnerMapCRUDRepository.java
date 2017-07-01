package com.techmahindra.smartparking.dao.jpa.organization;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganization;
import com.techmahindra.smartparking.pojo.dbentity.organization.SpOwnerProviderMap;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpProviderOwnerMapCRUDRepository extends CrudRepository<SpOwnerProviderMap, Integer> {
	
	@Query("SELECT map FROM SpOwnerProviderMap map"+ " WHERE map.owner = :owner")
	List<SpOwnerProviderMap> findSpOwnerProviderMapByOwner(@Param("owner") SpOrganization owner);
	
	List<SpOwnerProviderMap> findSpOwnerProviderMapByDependent(SpOrganization dependent);
	
	@Query("SELECT map FROM SpOwnerProviderMap map"+ " WHERE map.dependent = :dependent " + " and map.owner = :owner")
	List<SpOwnerProviderMap> findSpOwnerProviderMapByOwnerAndDependent(@Param("owner") SpOrganization owner,@Param("dependent") SpOrganization dependent);
	
	@Modifying
	@Transactional
	@Query("UPDATE SpOwnerProviderMap m SET m.toDate= :todate WHERE m.id = :id")
    int updateToDate(@Param("id") int id, @Param("todate") Date todate);
	
	
}
