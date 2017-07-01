package com.techmahindra.smartparking.dao.jpa.common;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.techmahindra.smartparking.pojo.dbentity.common.SpCurrency;
import com.techmahindra.smartparking.pojo.dbentity.customer.SpCustomerProfile;
import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganization;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpCurrencyCRUDRepository extends CrudRepository<SpCurrency, Integer> {
	
List<SpCurrency> findSpCurrencyByStatus(@Param("status") boolean b);
}
