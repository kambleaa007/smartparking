package com.techmahindra.smartparking.dao.jpa.organization;



import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.common.SpAddress;
import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganization;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpOrganizationCRUDRepository extends CrudRepository<SpOrganization, Integer> {
	
	SpOrganization findSpOrganizationByName(String name);

	SpOrganization findSpServiceProviderByName(String name);
	
	@Modifying
	@Transactional
	@Query("UPDATE SpOrganization o SET o.name= :name,o.contact= :contact,o.spAddress= :spAddress,o.status= :status WHERE o.orgid = :id")
    int updateOrganization(@Param("id") int id, @Param("name") String name, @Param("contact") String contact, @Param("spAddress") SpAddress spAddress, @Param("status") boolean status);
	
	@Query(value = "SELECT o.orgid, o.name, o.contact_name, o.status, t.type FROM `smartparking`.`sps_organization` o join `smartparking`.`sps_orgtypemap` t on t.organization = o.orgid", nativeQuery = true)
	List<Object[]> getAllOrganizations();

	@Query(value = "SELECT o.orgid, o.name, o.contact_name, o.status, t.type FROM `smartparking`.`sps_organization` o  JOIN `smartparking`.`sps_ownerprovidermap` m on m.dependent = o.orgid join `smartparking`.`sps_orgtypemap` t on t.organization = o.orgid where m.owner=:id and m.todate is null", nativeQuery = true)
	List<Object[]> getAllOrganizationsByOwner(@Param("id") int id);

}
