package com.techmahindra.smartparking.dao.jpa.zone;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZone;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZoneLevel;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpZoneLevelCRUDRepository extends CrudRepository<SpZoneLevel, Integer> {

	List<SpZoneLevel> findSpZoneLevelByZone(SpZone zone);

	@Modifying
	@Transactional
	@Query("delete from SpZoneLevel l WHERE l.levelid = :id")
	void deleteZoneLevel(@Param("id") int id);
	
	@Modifying
	@Transactional
	@Query("update SpZoneLevel z SET z.status= :status WHERE z.levelid = :id")
	void updateZoneLevelStatus(@Param("id") int id,@Param("status") boolean status);

	List<SpZoneLevel> findSpZoneLevelByZoneIn(Iterable<SpZone> zones);
	
}
