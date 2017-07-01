package com.techmahindra.smartparking.dao.jpa.device;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.device.SpDevice;
import com.techmahindra.smartparking.pojo.dbentity.device.SpSlotToDeviceMap;
import com.techmahindra.smartparking.pojo.dbentity.parking.SpParking;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpSlotToDeviceMapCRUDRepository extends CrudRepository<SpSlotToDeviceMap, Integer> {
	
	List<SpSlotToDeviceMap> findSpDeviceBySpsParking(@Param("spsParking") SpParking spsParking);
	
	@Modifying
	@Transactional
	@Query("UPDATE SpSlotToDeviceMap a SET a.todate= :todate WHERE a.mapid = :id")
    int updateSpSlotToDeviceMap(@Param("id") int id, @Param("todate") Date todate);
	
	List<SpSlotToDeviceMap> findSpDeviceByDevice(@Param("device") SpDevice device);
	
}
