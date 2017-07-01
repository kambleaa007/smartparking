package com.techmahindra.smartparking.dao.jpa.sensor;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techmahindra.smartparking.constant.ResourceMapping;
import com.techmahindra.smartparking.pojo.dbentity.device.SpSensor;

public interface ISpSensorCRUDRepository extends CassandraRepository<SpSensor> {
	
	@Query("select * from sensor_data where device_id = :deviceId and timestamp >= :startDate and timestamp < :endDate order by timestamp desc ALLOW FILTERING")
	Iterable<SpSensor> findByDeviceIdAndTimeDuration(@Param("deviceId") String deviceId, @Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query("select * from sensor_data where device_id = :deviceId ALLOW FILTERING")
	Iterable<SpSensor> findByDeviceId(@Param("deviceId") String deviceId);
	
	@Query("select * from sensor_data where device_id in(:deviceIds) and resource_path='"+ResourceMapping.OCCUPANCY+"' ALLOW FILTERING")
	Iterable<SpSensor> findOccupancyByDeviceIds(@Param("deviceIds") List<String> deviceIdList);

}