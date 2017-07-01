package com.techmahindra.smartparking.dao.jpa.sensor;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.techmahindra.smartparking.pojo.dbentity.device.SpSensorTransactionError;

public interface ISpSensorTransactionErrorCRUDRepository extends CassandraRepository<SpSensorTransactionError> {

}