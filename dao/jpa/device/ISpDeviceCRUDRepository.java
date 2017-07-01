package com.techmahindra.smartparking.dao.jpa.device;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.device.SpDevice;
import com.techmahindra.smartparking.pojo.dbentity.device.SpDeviceType;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZone;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpDeviceCRUDRepository extends CrudRepository<SpDevice, Integer> {
	
	List<SpDevice> findSpDeviceByStatus(@Param("status") boolean b);
	
	List<SpDevice> findSpDeviceByZone(@Param("zone") SpZone zone);
	
	@Modifying
	@Transactional
	@Query("UPDATE SpDevice a SET a.device_name= :device_name,a.mfgName= :mfgName,a.serialNumber= :serialNumber,a.uri= :uri,a.modal= :modal,a.deviceType= :deviceType,a.latitude= :latitude,a.longitude= :longitude,a.address= :address,a.status= :status WHERE a.deviceId = :id")
    int updateDevice(@Param("id") int id, @Param("device_name") String device_name, @Param("mfgName") String mfgName, @Param("serialNumber") String serialNumber, @Param("uri") String uri, @Param("modal") String modal, @Param("deviceType") SpDeviceType deviceType, @Param("latitude") BigDecimal latitude, @Param("longitude") BigDecimal longitude, @Param("address") String address,  @Param("status") boolean status);

	List<SpDevice> findSpDeviceByZoneIn(Iterable<SpZone> zones);
	
	
	
}
