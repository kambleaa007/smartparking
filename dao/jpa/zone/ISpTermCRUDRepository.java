package com.techmahindra.smartparking.dao.jpa.zone;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpTerm;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpTermCRUDRepository extends CrudRepository<SpTerm, Integer> {
	
	@Modifying
	@Transactional
	@Query("UPDATE SpTerm t SET t.termsAndConditions= :termsAndConditions WHERE t.termid = :id")
    int updateSpTermById(@Param("id") int id, @Param("termsAndConditions") String termsAndConditions);
	
	@Modifying
	@Transactional
	@Query("delete from SpTerm t WHERE t.termid = :id")
	int deleteSpTermById(@Param("id") int id);
	
}
