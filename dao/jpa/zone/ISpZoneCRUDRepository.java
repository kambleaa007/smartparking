package com.techmahindra.smartparking.dao.jpa.zone;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.common.SpCountry;
import com.techmahindra.smartparking.pojo.dbentity.common.SpState;
import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganization;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZone;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpZoneCRUDRepository extends CrudRepository<SpZone, Integer> {

	@Modifying
	@Transactional
	@Query("delete from SpZone z WHERE z.zoneId = :id")
	void deleteZone(@Param("id") int id);

	List<SpZone> findSpZoneByOwner(SpOrganization owner);


	@Modifying
	@Transactional
	@Query("update SpZone z SET z.status= :status WHERE z.zoneId = :id")
	void updateSpZoneStatus(@Param("id") int id,@Param("status") boolean b);

	@Modifying
	@Transactional
	@Query("update SpZone z SET z.minDuration= :minDuration WHERE z.zoneId = :id")
	void updateSpZoneMinDuration(@Param("id") int id,@Param("minDuration") Double minDuration);

	@Modifying
	@Transactional
	@Query("update SpZone z SET z.contactName= :contactName WHERE z.zoneId = :id")
	void updateSpZoneContactName(@Param("id") int id,@Param("contactName") String contactName);
	
	@Modifying
	@Transactional
	@Query("update SpZone z SET z.minimumPrice= :minimumPrice WHERE z.zoneId = :id")
	void updateSpZoneMinimumPrice(@Param("id") int id,@Param("minimumPrice") BigDecimal minimumPrice);


	@Query("FROM SpZone zone join fetch zone.spAddress SpAddress join fetch zone.support SpAddress WHERE zone.zoneId = :id")
	SpZone findZone(@Param("id") int id);
    

}
