package com.techmahindra.smartparking.dao.jpa.booking;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.techmahindra.smartparking.constant.BookingStatus;
import com.techmahindra.smartparking.pojo.dbentity.transaction.SpBooking;

/**
 * ISpBookingCRUDRepository.java
 * 
 * @version version 1.0
 * @author Tech Mahindra Limited
 */

@EnableJpaRepositories
public interface ISpBookingCRUDRepository extends
        CrudRepository<SpBooking, Integer> {

   
    @Query(value = "SELECT address.addressline1,address.addressline2,locations.zoneId , locations.zone_name ,locations.latitude ,locations.longitude, ( 3959 * acos( cos( radians(:latitude) ) * cos( radians( locations.latitude ) )* cos( radians(locations.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin( radians(locations.latitude)))) AS distance,locations.timezone,locations.minimum_price, currencys.currency as currencyName FROM  sps_zones locations ,sps_address  address,sps_currency currencys  where address.addressId=locations.address and locations.status=1 and currencys.currencyId=locations.currency  HAVING distance <:distance ORDER BY distance", nativeQuery = true)
    List<Object[]> findSpZoneByDistance(@Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude , @Param("distance") BigDecimal distance);

    @Query(value = "SELECT p.payment_amount,z.zone_name,p.payment_time, p.paymentInfo, z.zoneId, c.userName, c.mdnNumber,z.timezone,c.countryCode,b.bookingId FROM `smartparking`.`sps_bookingpaymentmap` m join `smartparking`.`sps_booking` b on b.bookingId=m.bookingId join `smartparking`.`sps_payment` p on p.paymentId = m.paymentId join `smartparking`.`sps_zonelevel` l on b.levelId = l.levelId join `smartparking`.`sps_zones` z on z.zoneId = l.zoneId join `smartparking`.`sps_customer` c on c.customerId = b.customerId where z.zoneId = :zoneId and p.payment_status=1 and (b.status=1 or b.status=6) and p.payment_time>= :fromTime and p.payment_time< :toTime order by p.payment_time desc", nativeQuery = true)
    List<Object[]> getPaymentReport(@Param("zoneId") int zoneId, @Param("fromTime") String fromTime, @Param("toTime") String toTime);

    
    @Query(value = "Select b.bookingId,c.customerId,c.userName, b.fromtime, b.totime, c.mdnNumber, l.floor, v.type, s.slot_type,z.zoneId,z.zone_name, p.payment_time as bookingTime,b.status,z.timezone,c.countryCode from `smartparking`.`sps_booking` b join `smartparking`.`sps_customer` c on c.customerId = b.customerId join `smartparking`.`sps_zonelevel` l on b.levelId = l.levelId join `smartparking`.`sps_vehicletype` v on b.vehicleTypeId = v.vid join `smartparking`.`sps_slottype` s on b.slotTypeId = s.id join `smartparking`.`sps_zones` z on l.zoneId = z.zoneId join `smartparking`.`sps_bookingpaymentmap` m on m.bookingId = b.bookingId join `smartparking`.`sps_payment` p on m.paymentId = p.paymentId  where z.zoneId = :zoneId and p.payment_time>= :fromTime and p.payment_time< :toTime and b.status = :status order by p.payment_time desc", nativeQuery = true)
    List<Object[]> getBookingReport(@Param("zoneId") int zoneId, @Param("fromTime") String fromTime, @Param("toTime") String toTime, @Param("status") int status);

    @Query(value = "Select b.bookingId,c.customerId,c.userName, b.fromtime, b.totime, c.mdnNumber, l.floor, v.type, s.slot_type,z.zoneId,z.zone_name, p.payment_time as bookingTime,b.status,z.timezone,c.countryCode from `smartparking`.`sps_booking` b join `smartparking`.`sps_customer` c on c.customerId = b.customerId join `smartparking`.`sps_zonelevel` l on b.levelId = l.levelId join `smartparking`.`sps_vehicletype` v on b.vehicleTypeId = v.vid join `smartparking`.`sps_slottype` s on b.slotTypeId = s.id join `smartparking`.`sps_zones` z on l.zoneId = z.zoneId join `smartparking`.`sps_bookingpaymentmap` m on m.bookingId = b.bookingId join `smartparking`.`sps_payment` p on m.paymentId = p.paymentId  where z.zoneId = :zoneId and p.payment_time>= :fromTime and p.payment_time< :toTime order by p.payment_time desc", nativeQuery = true)
    List<Object[]> getAllBookingRecords(@Param("zoneId") int zoneId, @Param("fromTime") String fromTime, @Param("toTime") String toTime);

    @Query(value = "Select count(*) as booked, l.floor from `smartparking`.`sps_booking` b join `smartparking`.`sps_customer` c on c.customerId = b.customerId join `smartparking`.`sps_zonelevel` l on b.levelId = l.levelId join `smartparking`.`sps_vehicletype` v on b.vehicleTypeId = v.vid join `smartparking`.`sps_slottype` s on b.slotTypeId = s.id join `smartparking`.`sps_zones` z on l.zoneId = z.zoneId join `smartparking`.`sps_bookingpaymentmap` m on m.bookingId = b.bookingId join `smartparking`.`sps_payment` p on m.paymentId = p.paymentId  where z.zoneId = :zoneId and p.payment_time>= :fromTime and p.payment_time< :toTime group by l.floor order by p.payment_time desc", nativeQuery = true)
    List<Object[]> getCurrentBookingReport(@Param("zoneId") int zoneId, @Param("fromTime") String fromTime, @Param("toTime") String toTime);

    @Query(value = "Select count(*) as booked, l.floor, z.zoneId from `smartparking`.`sps_booking` b join `smartparking`.`sps_customer` c on c.customerId = b.customerId join `smartparking`.`sps_zonelevel` l on b.levelId = l.levelId join `smartparking`.`sps_vehicletype` v on b.vehicleTypeId = v.vid join `smartparking`.`sps_slottype` s on b.slotTypeId = s.id join `smartparking`.`sps_zones` z on l.zoneId = z.zoneId join `smartparking`.`sps_bookingpaymentmap` m on m.bookingId = b.bookingId join `smartparking`.`sps_payment` p on m.paymentId = p.paymentId  where z.zoneId IN (:zoneIds) and p.payment_time>= :fromTime and p.payment_time< :toTime group by l.floor order by p.payment_time desc", nativeQuery = true)
    List<Object[]> getCurrentConsolidatedBookingReport(@Param("zoneIds") String zoneId, @Param("fromTime") String fromTime, @Param("toTime") String toTime);

    
    @Query(value = "Select b.bookingId,c.customerId,c.userName, b.fromtime, b.totime, c.mdnNumber, l.floor, v.type, s.slot_type,z.zoneId,z.zone_name, p.payment_time as bookingTime, vm.price, p.payment_amount,b.status,z.timezone,c.countryCode from `smartparking`.`sps_booking` b join `smartparking`.`sps_customer` c on c.customerId = b.customerId join `smartparking`.`sps_zonelevel` l on b.levelId = l.levelId join `smartparking`.`sps_vehicletype` v on b.vehicleTypeId = v.vid join `smartparking`.`sps_slottype` s on b.slotTypeId = s.id join `smartparking`.`sps_zones` z on l.zoneId = z.zoneId join `smartparking`.`sps_bookingpaymentmap` m on m.bookingId = b.bookingId join `smartparking`.`sps_payment` p on m.paymentId = p.paymentId join `smartparking`.`sps_levelvehiclemap` vm on (l.levelId= vm.levelId and vm.vehicle = b.vehicleTypeId and b.slotTypeId = vm.slot)  where b.bookingId = :bookingId", nativeQuery = true)
    Object[] getBookingDetail(@Param("bookingId") int bookingId);

    @Query(value = "select temp.levelId, temp.floor, temp.vehicleTypeId , temp.slotTypeId,REPLACE(FORMAT(sum(temp.capacity),0),',','') as capacity, REPLACE(FORMAT(sum(temp.capacityUsed),0),',','') as capacityUsed ,REPLACE(FORMAT(sum(temp.longhourDuration),0),',','') as longhourDuration,sum(temp.longhourPrice) as longhourPrice,sum(temp.overnightPrice) as overnightPrice,sum(temp.price) as price from(SELECT levels.levelId,levels.floor, map.vehicle as vehicleTypeId ,map.slot as slotTypeId,map.capacity as capacity,0 as capacityUsed, map.longhour_duration as longhourDuration,map.longhour_price as longhourPrice,map.overnight_price as overnightPrice,map.price FROM sps_levelvehiclemap map,sps_zonelevel levels where map.levelId=levels.levelId and levels.zoneId=:zoneId and map.vehicle=:vehicleType and map.slot=:slotType group by levelId,levels.floor,vehicle,slot,longhourDuration,longhourPrice,overnightPrice,price union all SELECT booking.levelId,levels.floor,booking.vehicleTypeId,booking.slotTypeId,0 as capacity,count(*) as capacityUsed,0 as longhourDuration,0 as longhourPrice,0 as overnightPrice,0 as price FROM sps_booking  booking,sps_zonelevel levels where booking.levelId=levels.levelId  and booking.vehicleTypeId =:vehicleType and booking.slotTypeId =:slotType and ((booking.status in ("+BookingStatus.BLOCKED +","+BookingStatus.BOOKED+","+ BookingStatus.OCCUPIED +") and (booking.fromtime < :fromtime or (booking.fromtime BETWEEN :fromtime and :totime)) and(booking.totime > :totime or(booking.totime BETWEEN :fromtime and :totime))) or (booking.status="+BookingStatus.OCCUPIED+" and booking.extendedtime is not null and booking.extendedtime<=:fromtime )) and levels.zoneId=:zoneId group by booking.levelId,levels.floor,booking.vehicleTypeId,booking.slotTypeId,longhourDuration,longhourPrice,overnightPrice,price)temp group by temp.levelId,temp.floor, temp.vehicleTypeId , temp.slotTypeId", nativeQuery = true)
    List<Object[]> findSpZoneLevelDetails(@Param("zoneId") int zoneId,
            @Param("vehicleType") int vehicleType,
            @Param("slotType") int slotType,
            @Param("fromtime") String fromtime, @Param("totime") String totime);

    @Modifying
    @Transactional
    @Query(value = " INSERT INTO sps_booking (`customerId`,`fromtime`,`totime`,`status`,`qr_code`,`levelId`,`vehicleTypeId`,`slotTypeId`,`createdBy`,`createdOn`) select :customerId,:fromtime,:totime,:status,'abcd',:levelId,:vehicleType,:slotType,:customerId,:createdOn from dual   WHERE  EXISTS(select * from (select temp.levelId, temp.vehicleTypeId , temp.slotTypeId,REPLACE(FORMAT(sum(temp.capacity),0),',','') as capacity, REPLACE(FORMAT(sum(temp.capacityUsed),0),',','') as capacityUsed ,REPLACE(FORMAT(sum(temp.longhourDuration),0),',','') as longhourDuration,sum(temp.longhourPrice) as longhourPrice,sum(temp.overnightPrice) as overnightPrice,sum(temp.price) as price from(SELECT map.levelId, map.vehicle as vehicleTypeId ,map.slot as slotTypeId,map.capacity as capacity,0 as capacityUsed, map.longhour_duration as longhourDuration,map.longhour_price as longhourPrice,map.overnight_price as overnightPrice,map.price FROM sps_levelvehiclemap map where map.levelId=:levelId and map.vehicle=:vehicleType and map.slot=:slotType group by levelId,vehicle,slot,longhourDuration,longhourPrice,overnightPrice,price union all SELECT booking.levelId,booking.vehicleTypeId,booking.slotTypeId,0 as capacity,count(*) as capacityUsed,0 as longhourDuration,0 as longhourPrice,0 as overnightPrice,0 as price FROM sps_booking  booking where booking.levelId=:levelId  and booking.vehicleTypeId =:vehicleType and booking.slotTypeId =:slotType and ((booking.status in ("+BookingStatus.BLOCKED +","+BookingStatus.BOOKED+","+ BookingStatus.OCCUPIED +") and (booking.fromtime < :fromtime or (booking.fromtime BETWEEN :fromtime and :totime))  and(booking.totime > :totime or(booking.totime BETWEEN :fromtime and :totime))) or (booking.status="+BookingStatus.OCCUPIED+" and booking.extendedtime is not null and booking.extendedtime<=:fromtime ))  group by booking.levelId,booking.vehicleTypeId,booking.slotTypeId,longhourDuration,longhourPrice,overnightPrice,price)temp group by temp.levelId, temp.vehicleTypeId , temp.slotTypeId)outerTemp where (CAST(outerTemp.capacity as UNSIGNED)) > (CAST(outerTemp.capacityUsed as UNSIGNED)))", nativeQuery = true)
    int blockParking(@Param("customerId") int customerId,
            @Param("levelId") int levelId,
            @Param("vehicleType") int vehicleType,
            @Param("slotType") int slotType,@Param("status") int status,
            @Param("fromtime") String fromtime, @Param("totime") String totime,
            @Param("createdOn") String createdOn);

/*    @Query("SELECT booking FROM SpBooking booking"
            + " WHERE booking.spsCustomer.customerId = :customerId "
            + " And booking.createdOn = :createdOn")
    SpBooking findSpBookingByCustomerIdAndCreatedOn(
            @Param("customerId") int customerId,
            @Param("createdOn") String createdOn);
*/
  
    @Query(value = " select bookingId from sps_booking where customerId=:customerId and createdOn=:createdOn ", nativeQuery = true)
    int findSpBookingByCustomerIdAndCreatedOn(@Param("customerId") int customerId,
            @Param("createdOn") String createdOn);
    

    @Query(value = " select booking FROM SpBooking booking where booking.status=:status and booking.createdOn <=:createdOn")
    List<SpBooking> findUnUsedBlockParkings(@Param("status") int status,
            @Param("createdOn") Date createdOn);

    @Query(value = "SELECT mapid,price  from sps_levelvehiclemap map where map.vehicle=:vehicleType and map.slot=:slotType and levelId=:levelId", nativeQuery = true)
    List<Object[]> findLevelPriceDetails(@Param("levelId") int levelId,
            @Param("vehicleType") int vehicleType,
            @Param("slotType") int slotType);
    
    @Query(value = "SELECT mapid,price  from sps_levelvehiclemap map where map.vehicle=:vehicleType and map.slot=:slotType and levelId=:levelId", nativeQuery = true)
    List<Object[]> confirmParking(@Param("levelId") int levelId,
            @Param("vehicleType") int vehicleType,
            @Param("slotType") int slotType); 

    @Transactional
    SpBooking findSpBookingByBookingId(int bookingId);    
    
    
    @Query(value = "SELECT booking.bookingId,booking.status,zone.zone_name,zone.timezone,booking.fromtime ,booking.totime,sum(payment_amount),zone.latitude,zone.longitude FROM smartparking.sps_booking booking, smartparking.sps_bookingpaymentmap map , smartparking.sps_payment payment, sps_zonelevel levels, sps_zones zone where booking.bookingId=map.bookingId and map.paymentId=payment.paymentId and payment.payment_status=1 and booking.customerId=:customerId and booking.levelId=levels.levelId and levels.zoneId=zone.zoneId  group by booking.bookingId,zone.zone_name,booking.fromtime , booking.totime,zone.timezone,booking.status,zone.latitude,zone.longitude order by booking.fromtime", nativeQuery = true)
    List<Object[]> bookingHistory(@Param("customerId") int customerId); 
    
    @Query(value = "select temp.levelId, temp.vehicleTypeId , temp.slotTypeId,capacity,futureBooking,ongoingBooking,zone.timezone,zone.zone_name from (select temp.levelId, temp.vehicleTypeId , temp.slotTypeId,REPLACE(FORMAT(sum(temp.capacity),0),',','') as capacity, REPLACE(FORMAT(sum(temp.futureBooking),0),',','') as futureBooking ,REPLACE(FORMAT(sum(temp.ongoingBooking),0),',','') as ongoingBooking from(SELECT map.levelId, map.vehicle as vehicleTypeId ,map.slot as slotTypeId,map.capacity as capacity,0 as futureBooking, 0 as ongoingBooking FROM sps_levelvehiclemap map group by levelId,vehicle,slot union SELECT booking.levelId,booking.vehicleTypeId,booking.slotTypeId ,0 as capacity,0 as futureBooking, count(*) as ongoingBooking from sps_booking booking where booking.fromtime < :jobStartTime and (booking.totime >= :jobStartTime or booking.status="+BookingStatus.OCCUPIED+")and (booking.status="+BookingStatus.OCCUPIED+" or "+BookingStatus.BOOKED+") group by booking.levelId,booking.vehicleTypeId,booking.slotTypeId union SELECT booking.levelId,booking.vehicleTypeId,booking.slotTypeId ,0 as capacity,count(*) as futureBooking, 0 as ongoingBooking from sps_booking booking where booking.fromtime BETWEEN :jobStartTime and :jobStartTimeBuffer and booking.status="+BookingStatus.BOOKED+" group by booking.levelId,booking.vehicleTypeId,booking.slotTypeId) temp group by temp.levelId, temp.vehicleTypeId , temp.slotTypeId having futureBooking+ongoingBooking>capacity) temp,sps_zonelevel levels, sps_zones zone where temp.levelId=levels.levelId and levels.zoneId=zone.zoneId ", nativeQuery = true)
    List<Object[]> findFutureBookingsFromLevelToCancel(@Param("jobStartTime") String jobStartTime,@Param("jobStartTimeBuffer") String jobStartTimeBuffer); 

    @Query(value="SELECT booking.bookingId, booking.customerId ,booking.fromtime,booking.totime,booking.levelId,booking.vehicleTypeId,booking.slotTypeId from sps_booking booking where (booking.fromtime BETWEEN :jobStartTime and :jobStartTimeBuffer) and booking.levelId=:levelId and booking.vehicleTypeId=:vehicleTypeId and booking.slotTypeId=:slotTypeId ORDER BY createdOn DESC limit :limit", nativeQuery = true)
    List<Object[]> findCanceledBookings(@Param("jobStartTime") String jobStartTime,@Param("jobStartTimeBuffer") String jobStartTimeBuffer,@Param("levelId") int levelId,
            @Param("vehicleTypeId") int vehicleTypeId,@Param("slotTypeId") int slotTypeId,@Param("limit") int limit);
    
    @Query(value = "UPDATE sps_booking set status=:status,updatedBy=:updatedBy,updatedOn=:updatedOn Where bookingId IN :cancelIds", nativeQuery = true)
    @Modifying
    @Transactional
    int cancelFutureBookedParkings(@Param("status") int status,@Param("updatedBy") String updatedBy,
    		@Param("updatedOn") String updatedOn,@Param("cancelIds") List<Integer> cancelIds);
    
    @Query(value = "UPDATE sps_booking set status=:occupiedStatus,updatedBy=:updatedBy,updatedOn=:updatedOn,intime=:inTime Where bookingId=:bookingId and customerId=:customerId and (:currentTime BETWEEN fromtime and totime) and status=:bookedStatus", nativeQuery = true)
    @Modifying
    @Transactional
    int entryBookedParking(@Param("bookingId") int bookingId,@Param("customerId") int customerId,
    		@Param("occupiedStatus") int occupiedStatus,@Param("bookedStatus") int bookedStatus,@Param("updatedBy") String updatedBy,
    		@Param("updatedOn") String updatedOn,@Param("inTime") String inTime,@Param("currentTime") String currentTime);
    
    @Query(value = "UPDATE sps_booking set status=:status,updatedBy=:updatedBy,updatedOn=:updatedOn,outtime=:outTime Where bookingId=:bookingId and customerId=:customerId and status=:occupiedStatus ", nativeQuery = true)
    @Modifying
    @Transactional
    int exitOccupiedParking(@Param("status") int status,@Param("updatedBy") String updatedBy,
    		@Param("updatedOn") String updatedOn,@Param("outTime") String outTime,@Param("bookingId") int bookingId,@Param("customerId") int customerId,@Param("occupiedStatus") int occupiedStatus);
    
    @Query(value = "UPDATE sps_booking set status=:status,updatedBy=:updatedBy,updatedOn=:updatedOn Where bookingId=:bookingId and customerId=:customerId and (:currentTime<fromtime) and status!=:status", nativeQuery = true)
    @Modifying
    @Transactional
    int cancelFutureBookedParking(@Param("status") int status,@Param("updatedBy") String updatedBy,
    		@Param("updatedOn") String updatedOn,@Param("currentTime") String currentTime,@Param("bookingId") int bookingId,@Param("customerId") int customerId);
    
    @Query(value = "UPDATE sps_booking set status=:status,updatedBy=:updatedBy,updatedOn=:updatedOn Where status=:bookedStatus and (:currentTime>totime)", nativeQuery = true)
    @Modifying
    @Transactional
    int updateExpiredBookedParking(@Param("status") int status,@Param("updatedBy") String updatedBy,
    		@Param("updatedOn") String updatedOn,@Param("bookedStatus") int bookedStatus,@Param("currentTime") String currentTime);
    
    /*@Query(value="select customer.email,customer.country,customer.countryCode,customer.mdnNumber,booking.bookingId,booking.fromtime,booking.totime,zone.zone_name from sps_customer customer,sps_booking booking,sps_zonelevel levels,sps_zones zone where customer.customerId=booking.customerId and booking.status=:occupiedStatus and (booking.totime between :currentTimeLess15Min and :currentTime) and booking.levelId=levels.levelId and levels.zoneId=zone.zoneId ORDER BY booking.totime", nativeQuery = true)
    List<Object[]> findBookingsToBeExpiredInNearFuture(@Param("occupiedStatus") int occupiedStatus,@Param("currentTime") String currentTime,
    		@Param("currentTimeLess15Min") String currentTimeLess15Min);*/
    
    @Query(value="select customer.email,customer.country,customer.countryCode,customer.mdnNumber,booking.bookingId,booking.fromtime,booking.totime,zone.zoneName,zone.timezone from SpCustomerProfile customer,SpBooking booking,SpZoneLevel levels,SpZone zone where customer.customerId=booking.spsCustomer.customerId and booking.status=:occupiedStatus and (booking.totime between :currentTimeLess15Min and :currentTime) and booking.zoneLevel.levelid=levels.levelid and levels.zone.zoneId=zone.zoneId ORDER BY booking.totime")
    List<Object[]> findBookingsToBeExpiredInNearFuture(@Param("occupiedStatus") int occupiedStatus,@Param("currentTime") Date currentTime,
    		@Param("currentTimeLess15Min") Date currentTimeLess15Min);
    
    @Query(value="select customer.email,customer.country,customer.countryCode,customer.mdnNumber,booking.bookingId,booking.fromtime,booking.totime,zone.zone_name,zone.timezone from sps_customer customer,sps_booking booking,sps_zonelevel levels,sps_zones zone where customer.customerId=booking.customerId and booking.status=:occupiedStatus and (booking.totime=:currentTime or AddTime(booking.toTime, :minutesToAdd) >:currentTime) and booking.levelId=levels.levelId and levels.zoneId=zone.zoneId ORDER BY booking.totime",nativeQuery=true)
    List<Object[]> findOccupiedExpiredBooking(@Param("occupiedStatus") int occupiedStatus,@Param("currentTime") String currentTime,
    		@Param("minutesToAdd") String minutesToAdd);
    
    @Query(value="select customer.email,customer.country,customer.countryCode,customer.mdnNumber,booking.bookingId,booking.fromtime,booking.totime,zone.zoneName,zone.timezone from SpCustomerProfile customer,SpBooking booking,SpZoneLevel levels,SpZone zone where customer.customerId=booking.spsCustomer.customerId and booking.status=:occupiedStatus and (booking.totime between :currentTimeLess30Min and :currentTimeLess15Min) and booking.zoneLevel.levelid=levels.levelid and levels.zone.zoneId=zone.zoneId ORDER BY booking.totime")
    List<Object[]> findOccupiedExpiredBookingToBeExtended(@Param("occupiedStatus") int occupiedStatus,@Param("currentTimeLess15Min") Date currentTimeLess15Min,
    		@Param("currentTimeLess30Min") Date currentTimeLess30Min);
    
    @Query(value = "UPDATE sps_booking set extendedtime=AddTime(totime, :timeToAdd),updatedBy=:updatedBy,updatedOn=:updatedOn Where status=:occupiedStatus and extendedtime is null and(totime between :currentTimeLess30Min and :currentTimeLess15Min)", nativeQuery = true)
    @Modifying
    @Transactional
    int extendedOccupiedParking(@Param("occupiedStatus") int occupiedStatus,@Param("updatedBy") String updatedBy,
    		@Param("updatedOn") String updatedOn,@Param("timeToAdd") String timeToAdd,
    		@Param("currentTimeLess15Min") String currentTimeLess15Min,@Param("currentTimeLess30Min") String currentTimeLess30Min);
    
    @Query(value = "UPDATE sps_booking set extendedtime=AddTime(extendedtime, :timeToAdd),updatedBy=:updatedBy,updatedOn=:updatedOn Where status=:occupiedStatus and (extendedtime is not null and AddTime(extendedtime, :bufferTimeToAdd)<=:currentTime)", nativeQuery = true)
    @Modifying
    @Transactional
    int extendedOccupiedParkingAgain(@Param("occupiedStatus") int occupiedStatus,@Param("updatedBy") String updatedBy,
    		@Param("updatedOn") String updatedOn,@Param("timeToAdd") String timeToAdd,@Param("bufferTimeToAdd") String bufferTimeToAdd,@Param("currentTime") String currentTime);
}
