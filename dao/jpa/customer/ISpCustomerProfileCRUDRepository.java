package com.techmahindra.smartparking.dao.jpa.customer;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.customer.SpCustomerProfile;

/**
 * IAddressTypeCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpCustomerProfileCRUDRepository extends CrudRepository<SpCustomerProfile, Integer> {

	
	
	 @Query("SELECT custProfile FROM SpCustomerProfile custProfile"
	            + " WHERE custProfile.mdnNumber = :mdnNumber " + " OR custProfile.email = :email")
	List<SpCustomerProfile> findSpCustomerProfileByMdnNumberOrEmail(@Param("mdnNumber") String mdnNumber,
           @Param("email") String customerId);
	 
	SpCustomerProfile findSpCustomerProfileByEmail(String email);
	
	SpCustomerProfile findSpCustomerProfileByCustomerId(int customerId);
	
	 @Query("SELECT custProfile FROM SpCustomerProfile custProfile"
	            + " WHERE custProfile.mdnNumber = :mdnNumber " + " AND custProfile.customerId != :customerId")
	SpCustomerProfile findSpCustomerProfileByMdnNumberExist(@Param("mdnNumber")String mdnNumber,@Param("customerId")Integer customerId);
	
	
	@Modifying
        @Transactional
        @Query("UPDATE SpCustomerProfile custProfile SET custProfile.statusId= :statusId WHERE custProfile.email = :email")
        int updateUserStatus( @Param("statusId") boolean statusId,@Param("email") String email);
         
	@Modifying
        @Transactional
        @Query("UPDATE SpCustomerProfile custProfile SET custProfile.password= :password WHERE custProfile.customerId = :customerId")
        int updateUserPasswordByCustomerId( @Param("password") String password,@Param("customerId") int customerId);
	
	@Modifying
        @Transactional
        @Query("UPDATE SpCustomerProfile custProfile SET custProfile.password= :password WHERE custProfile.email = :email")
        int updateUserPasswordByEmail( @Param("password") String password,@Param("email") String email);
	
	
	@Modifying
        @Transactional
        @Query("UPDATE SpCustomerProfile custProfile SET custProfile.userName= :userName, custProfile.mdnNumber= :mdnNumber WHERE custProfile.customerId = :customerId")
        int updateUserByCustomerId( @Param("userName") String userName,@Param("mdnNumber") String mdnNumber,@Param("customerId") int customerId);
	 	
}
