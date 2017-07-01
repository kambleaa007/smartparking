package com.techmahindra.smartparking.dao.jpa.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganization;
import com.techmahindra.smartparking.pojo.dbentity.user.SpRole;
import com.techmahindra.smartparking.pojo.dbentity.user.SpUser;

/**
 * IAddressTypeCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpUserCRUDRepository extends CrudRepository<SpUser, Integer> {

	
	SpUser findSpUserByUserId(Integer userId);
	
	@Modifying
	@Transactional
	@Query("UPDATE SpUser u SET u.firstname= :firstname,u.lastname= :lastname,u.password= :password,u.status= :status,u.spsRole= :role,u.organization= :organization WHERE u.userId = :id")
    int updateUser(@Param("id") int id, @Param("firstname") String firstname, @Param("lastname") String lastname, @Param("password") String password, @Param("status") boolean status, @Param("role") SpRole role, @Param("organization") SpOrganization organization);
	 
	@Modifying
	@Transactional
	@Query("UPDATE SpUser u SET u.password= :password WHERE u.userId = :id")
    int updatePassword(@Param("id") int id,@Param("password") String password);
	 
	@Modifying
	@Transactional
	@Query("DELETE FROM SpUser u WHERE u.userId = :id")
    int deleteUser(@Param("id") int id);
	
	SpUser findSpUserByUsername(String name);

	SpUser findSpUserByUsernameAndPassword(String username, String myPassword);
}

