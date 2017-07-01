package com.techmahindra.smartparking.dao.jpa.zone;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.zone.SpZoneAvailibility;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZoneavailibilityPK;


/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpZoneAvailibilityCRUDRepository extends CrudRepository<SpZoneAvailibility, Integer> {
	
	SpZoneAvailibility findSpZoneAvailibilityById(@Param("id") SpZoneavailibilityPK id);
	
	@Modifying
	@Transactional
	@Query("update SpZoneAvailibility a SET a.available= :available, a.opening_time= :opening_time, a.closing_time= :closing_time WHERE a.id = :id")
	void updateSpZoneAvailibility(@Param("id") SpZoneavailibilityPK id,@Param("available") boolean available,@Param("opening_time") String opening_time,@Param("closing_time") String closing_time);
	
}
