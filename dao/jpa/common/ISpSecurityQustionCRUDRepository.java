package com.techmahindra.smartparking.dao.jpa.common;



import java.util.List;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.techmahindra.smartparking.pojo.dbentity.user.SpSecurityQuestion;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpSecurityQustionCRUDRepository extends CrudRepository<SpSecurityQuestion, Integer> {
	
	List<SpSecurityQuestion> findSpSecurityQuestionByStatus(@Param("status") boolean status);
}
