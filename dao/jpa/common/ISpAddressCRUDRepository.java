package com.techmahindra.smartparking.dao.jpa.common;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.common.SpAddress;
import com.techmahindra.smartparking.pojo.dbentity.common.SpCountry;
import com.techmahindra.smartparking.pojo.dbentity.common.SpState;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZone;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpAddressCRUDRepository extends CrudRepository<SpAddress, Integer> {
	
	@Modifying
	@Transactional
	@Query("UPDATE SpAddress a SET a.addressline1= :addressline1,a.addressline2= :addressline2,a.city= :city,a.state= :state,a.country= :country,a.zipcode= :zipcode,a.primaryNumber= :primaryNumber,a.secondaryNumber= :secondaryNumber,a.email= :email WHERE a.addressId = :id")
    int updateAddress(@Param("id") int id, @Param("addressline1") String addressline1, @Param("addressline2") String addressline2, @Param("city") String city, @Param("state") SpState state, @Param("country") SpCountry country, @Param("zipcode") String zipcode, @Param("primaryNumber") String primaryNumber, @Param("secondaryNumber") String secondaryNumber, @Param("email") String email);
	
	
}
