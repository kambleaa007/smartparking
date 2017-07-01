package com.techmahindra.smartparking.dao.jpa.customer;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.techmahindra.smartparking.pojo.dbentity.customer.SpCustomerProfile;
import com.techmahindra.smartparking.pojo.dbentity.nonce.SpToken;

/**
 * IAddressTypeCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpTokenCRUDRepository extends CrudRepository<SpToken, Integer> {

    
    @Query("SELECT token FROM SpToken token"
               + " WHERE token.spsCustomer = :customer " + " and token.nonceType = :type and token.isValid=:isValid and expiryTime>=now()")
   SpToken findSpTokenByCustomerAndType(@Param("customer") SpCustomerProfile customer,
      @Param("type") String type,@Param("isValid") Boolean isValid);
   
    @Query("SELECT token FROM SpToken token"
            + " WHERE token.spsCustomer = :customer " + " and token.nonceType = :type and token.isValid=:isValid and expiryTime>=now()")
    List<SpToken> checkExistingSpTokensByCustomerAndType(@Param("customer") SpCustomerProfile customer,
    	      @Param("type") String type,@Param("isValid") Boolean isValid);
	
	
}
