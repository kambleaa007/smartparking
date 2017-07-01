package com.techmahindra.smartparking.dao.jpa.user;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.user.SpSecurityQuestion;
import com.techmahindra.smartparking.pojo.dbentity.user.SpUser;
import com.techmahindra.smartparking.pojo.dbentity.user.SpUserAssistance;

/**
 * IAddressTypeCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpUserAssistanceRepository extends CrudRepository<SpUserAssistance, Integer> {

	
	List<SpUserAssistance> findSpUserAssistanceByUser(SpUser user);
	
	@Modifying
	@Transactional
	@Query("UPDATE SpUserAssistance u SET u.spsSecurityquestion= :spsSecurityquestion,u.securityAnswer= :securityAnswer WHERE u.userId = :id")
    int updateSecurityQuestion(@Param("id") int id,@Param("spsSecurityquestion") SpSecurityQuestion spsSecurityquestion,@Param("securityAnswer") String securityAnswer);
}
