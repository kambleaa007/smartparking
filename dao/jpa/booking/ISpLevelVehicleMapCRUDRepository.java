package com.techmahindra.smartparking.dao.jpa.booking;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.parking.SpLevelVehicleMap;
import com.techmahindra.smartparking.pojo.dbentity.parking.SpSlotType;
import com.techmahindra.smartparking.pojo.dbentity.parking.SpVehicleType;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZoneLevel;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpLevelVehicleMapCRUDRepository extends CrudRepository<SpLevelVehicleMap, Integer> {

	@Modifying
	@Transactional
	@Query("update SpLevelVehicleMap z SET z.capacity= :capacity, z.totalCapacity = :totalCapacity WHERE z.mapid = :mapid")
	void updateSpLevelVehicleMap(@Param("mapid") int mapid,@Param("capacity") int capacity,@Param("totalCapacity") int totalCapacity);
	
	@Modifying
	@Transactional
	@Query("update SpLevelVehicleMap z SET z.capacity= :capacity WHERE z.mapid = :mapid")
	void updateSpLevelVehicleMapCapacity(@Param("mapid") int mapid,@Param("capacity") int capacity);
	
	
	@Modifying
	@Transactional
	@Query("update SpLevelVehicleMap z SET z.price= :price WHERE z.mapid = :mapid")
	void updateSpLevelVehicleMapPrice(@Param("mapid") int mapid,@Param("price") BigDecimal price);
	
	List<SpLevelVehicleMap> findSpLevelVehicleMapBySpsZonelevel(SpZoneLevel spZoneLevel);
	
}
