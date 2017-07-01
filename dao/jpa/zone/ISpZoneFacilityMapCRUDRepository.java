package com.techmahindra.smartparking.dao.jpa.zone;



import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.zone.SpZone;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZoneFacilityMap;


/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpZoneFacilityMapCRUDRepository extends CrudRepository<SpZoneFacilityMap, Integer> {
	
	List<SpZoneFacilityMap> findSpZoneAvailibilityBySpsZone(@Param("zone") SpZone zone);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM SpZoneFacilityMap z WHERE z.mapid = :id")
    int deleteMap(@Param("id") int id);
	
}
