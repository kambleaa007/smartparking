package com.techmahindra.smartparking.dao.jpa.booking;



import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.pojo.dbentity.transaction.SpPaymentGateway;
import com.techmahindra.smartparking.pojo.dbentity.user.SpUser;

/**
 * ISpServiceProviderCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpPaymentGatewayCRUDRepository extends CrudRepository<SpPaymentGateway, Integer> {

    @Transactional
    SpPaymentGateway findSpPaymentGatewayByGatewayId(Integer gatewayId);
}
