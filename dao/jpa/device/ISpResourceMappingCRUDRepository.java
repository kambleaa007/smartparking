package com.techmahindra.smartparking.dao.jpa.device;



import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.device.SpDevice;
import com.techmahindra.smartparking.pojo.dbentity.device.SpResourceMapping;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpResourceMappingCRUDRepository extends CrudRepository<SpResourceMapping, Integer> {
	
	List<SpResourceMapping> findSpResourceMappingByDevice(@Param("device") SpDevice device);
	List<SpResourceMapping> findSpResourceMappingBySubscriptionId(@Param("subscriptionId") String subscriptionId);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM SpResourceMapping r WHERE r.device = :device")
    int deleteResources(@Param("device") SpDevice device);
	
	@Modifying
	@Transactional
	@Query("UPDATE SpResourceMapping a SET a.subscriptionId= :subscriptionId, a.isSubscribed= :isSubscribed WHERE a.id = :id")
    int updateDeviceSubscription(@Param("id") int id, @Param("subscriptionId") String subscriptionId, @Param("isSubscribed") boolean isSubscribed);
	
}
