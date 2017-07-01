CREATE SCHEMA IF NOT EXISTS `smartparking` ;

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_country` (

  `countryId` INT NOT NULL AUTO_INCREMENT ,

  `country` VARCHAR(45) NOT NULL UNIQUE ,

  `status` TINYINT NOT NULL DEFAULT 1 ,

  PRIMARY KEY (`countryId`) );

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_state` (

  `stateId` INT NOT NULL AUTO_INCREMENT ,

  `state` VARCHAR(45) NOT NULL ,

  `countryId` INT NOT NULL ,

  `status` TINYINT NOT NULL DEFAULT 1 ,

  PRIMARY KEY (`stateId`) ,

  INDEX `sc_country` (`countryId` ASC) ,

  CONSTRAINT `sc_country`

    FOREIGN KEY (`countryId` )

    REFERENCES `smartparking`.`sps_country` (`countryId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
    
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_addresstype` (

  `typeId` INT NOT NULL AUTO_INCREMENT ,

  `type` VARCHAR(45) NULL ,

  `description` VARCHAR(100) NULL ,
  
    
  PRIMARY KEY (`typeId`) );

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_address` (

  `addressId` INT NOT NULL AUTO_INCREMENT ,

  `type` INT NOT NULL ,

  `addressline1` VARCHAR(100) NOT NULL ,

  `addressline2` VARCHAR(100) NULL ,

  `city` VARCHAR(45) NOT NULL ,

  `state` INT NOT NULL ,

  `country` INT NOT NULL ,

  `zipcode` VARCHAR(10) NULL ,

  `primaryNumber` VARCHAR(16) NOT NULL ,

  `secondaryNumber` VARCHAR(16) NOT NULL ,
  
  `email` VARCHAR(100) NOT NULL,

  `createdby` VARCHAR(45) NULL ,

  `created_on` DATETIME NULL ,

  `updatedby` VARCHAR(45) NULL ,

  `updated_on` DATETIME NULL ,

  PRIMARY KEY (`addressId`) ,

  INDEX `as_type` (`type` ASC) ,

  INDEX `as_country` (`country` ASC) ,

  INDEX `as_state` (`state` ASC) ,

  CONSTRAINT `as_type`

    FOREIGN KEY (`type` )

    REFERENCES `smartparking`.`sps_addresstype` (`typeId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `as_country`

    FOREIGN KEY (`country` )

    REFERENCES `smartparking`.`sps_country` (`countryId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `as_state`

    FOREIGN KEY (`state` )

    REFERENCES `smartparking`.`sps_state` (`stateId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
        
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_organization` (

  `orgid` INT NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(100) NOT NULL ,
  
  `contact_name` VARCHAR(100) NOT NULL ,

  `address` INT NOT NULL ,

  `status` TINYINT(1)  NULL DEFAULT true ,
  
  `createdby` VARCHAR(100) NULL ,
  
  `created_on` datetime DEFAULT NULL,
  
  `updatedby` VARCHAR(100) NULL ,

  `updated_on` datetime DEFAULT NULL,

  PRIMARY KEY (`orgid`) ,

  INDEX `so_address` (`address` ASC) ,

  CONSTRAINT `so_address`

    FOREIGN KEY (`address` )

    REFERENCES `smartparking`.`sps_address` (`addressId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_organizationtype` (

  `typeid` INT NOT NULL AUTO_INCREMENT ,

  `type_name` VARCHAR(45) NOT NULL ,

  `description` VARCHAR(45) NULL ,

  PRIMARY KEY (`typeid`) );

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_role` (

  `roleId` INT NOT NULL AUTO_INCREMENT ,

  `role_name` VARCHAR(45) NOT NULL ,

  `description` VARCHAR(100) NULL ,
  
  `type` INT NOT NULL ,

  PRIMARY KEY (`roleId`) ,

  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC),
  
  INDEX `tr_orgtype` (`type` ASC) ,
  
  CONSTRAINT `tr_orgtype`

    FOREIGN KEY (`type` )

    REFERENCES `smartparking`.`sps_organizationtype` (`typeid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
    
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_permissions` (

  `permissionId` INT NOT NULL AUTO_INCREMENT ,

  `permission` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`permissionId`) );

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_roletopermissionmap` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `role` INT NOT NULL ,

  `permission` INT NOT NULL ,

  PRIMARY KEY (`id`) ,

  INDEX `rp_role` (`role` ASC) ,

  INDEX `rp_permission` (`permission` ASC) ,

  CONSTRAINT `rp_role`

    FOREIGN KEY (`role` )

    REFERENCES `smartparking`.`sps_role` (`roleId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `rp_permission`

    FOREIGN KEY (`permission` )

    REFERENCES `smartparking`.`sps_permissions` (`permissionId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_user` (

  `userId` INT NOT NULL AUTO_INCREMENT ,

  `username` VARCHAR(50) NOT NULL UNIQUE ,

  `firstname` VARCHAR(45) NULL ,

  `lastname` VARCHAR(45) NULL ,

  `password` VARCHAR(100) NULL ,
  
  `organization` INT NOT NULL ,
  
  `role` INT NOT NULL ,

  `status` TINYINT NOT NULL DEFAULT 1 ,
  
  `createdby` VARCHAR(100) NULL ,
  
  `created_on` datetime DEFAULT NULL,
  
  `updatedby` VARCHAR(100) NULL ,

  `updated_on` datetime DEFAULT NULL,

  PRIMARY KEY (`userId`),
  
  INDEX `uo.organization` (`organization` ASC) ,
  
  INDEX `ur.role` (`role` ASC) ,
  
  CONSTRAINT `uo.organization`

    FOREIGN KEY (`organization` )

    REFERENCES `smartparking`.`sps_organization` (`orgid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,
    
    CONSTRAINT `ur.role`

    FOREIGN KEY (`role` )

    REFERENCES `smartparking`.`sps_role` (`roleId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_securityquestions` (

  `questionId` INT NOT NULL AUTO_INCREMENT ,

  `question` VARCHAR(100) NOT NULL ,

  `status` INT NOT NULL DEFAULT 1 ,

  PRIMARY KEY (`questionId`) );

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_userassistance` (

  `userId` INT NOT NULL ,

  `security_question` INT NULL ,

  `security_answer` VARCHAR(100) NULL ,

  PRIMARY KEY (`userId`) ,

  INDEX `ua_id` (`userId` ASC) ,

  INDEX `ua_secques` (`security_question` ASC) ,

  CONSTRAINT `ua_id`

    FOREIGN KEY (`userId` )

    REFERENCES `smartparking`.`sps_user` (`userId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

   CONSTRAINT `ua_secques`

    FOREIGN KEY (`security_question` )

    REFERENCES `smartparking`.`sps_securityquestions` (`questionId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_slottype` (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `slot_type` VARCHAR(45) NOT NULL ,
  
  `status` TINYINT NOT NULL DEFAULT 1,

  PRIMARY KEY (`id`) );
  
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_orgtypemap` (

  `mapid` INT NOT NULL AUTO_INCREMENT ,

  `organization` INT NOT NULL ,

  `type` INT NOT NULL ,

  PRIMARY KEY (`mapid`) ,

  INDEX `ot.org` (`organization` ASC) ,

  INDEX `ot.type` (`type` ASC) ,

  CONSTRAINT `ot.org`

    FOREIGN KEY (`organization` )

    REFERENCES `smartparking`.`sps_organization` (`orgid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `ot.type`

    FOREIGN KEY (`type` )

    REFERENCES `smartparking`.`sps_organizationtype` (`typeid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
    
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_vehicletype` (

  `vid` INT NOT NULL AUTO_INCREMENT ,

  `type` VARCHAR(50) NOT NULL ,

  `description` VARCHAR(50) NULL ,
  
  `status` TINYINT NOT NULL DEFAULT 1 ,
 
  PRIMARY KEY (`vid`) );
  
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_devicetype` (

  `deviceId` INT NOT NULL AUTO_INCREMENT ,

  `type` VARCHAR(50) NOT NULL ,

  `description` VARCHAR(50) NULL ,
  
  `status` TINYINT NOT NULL DEFAULT 1 ,
 
  PRIMARY KEY (`deviceId`) );
  
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_zonetype` (

  `typeId` INT NOT NULL AUTO_INCREMENT ,

  `type` VARCHAR(45) NOT NULL ,

  `description` VARCHAR(45) NULL ,
  
  `status` TINYINT NOT NULL DEFAULT 1 ,

  PRIMARY KEY (`typeId`) );

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_paymentgateway` (

  `gatewayId` INT NOT NULL AUTO_INCREMENT ,

  `gateway` VARCHAR(45) NOT NULL ,

  `status` TINYINT NOT NULL DEFAULT 0 ,

  PRIMARY KEY (`gatewayId`) );

 
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_ownerprovidermap` (

  `mapid` INT NOT NULL AUTO_INCREMENT ,

  `owner` INT NOT NULL ,

  `dependent` INT NOT NULL ,

  `fromdate` DATETIME NOT NULL ,

  `todate` DATETIME NULL ,

  PRIMARY KEY (`mapid`) ,

  INDEX `m_org1` (`owner` ASC) ,

  INDEX `m_org2` (`dependent` ASC) ,

  CONSTRAINT `m_org1`

    FOREIGN KEY (`owner` )

    REFERENCES `smartparking`.`sps_organization` (`orgid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `m_org2`

    FOREIGN KEY (`dependent` )

    REFERENCES `smartparking`.`sps_organization` (`orgid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
   
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_facility` (

  `facilityId` INT NOT NULL AUTO_INCREMENT ,

  `facility`  VARCHAR(50) NOT NULL,

  `description`  VARCHAR(50) NULL,
  
  `status` TINYINT NOT NULL DEFAULT 1 ,

  PRIMARY KEY (`facilityId`) );
  
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_currency` (

  `currencyId` INT NOT NULL AUTO_INCREMENT ,

  `currency` VARCHAR(45) NOT NULL ,

  `status` TINYINT NOT NULL DEFAULT 1 ,

  PRIMARY KEY (`currencyId`) );
  
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_terms` (

  `termId` INT NOT NULL AUTO_INCREMENT ,

  `terms_and_conditions` LONGTEXT NOT NULL ,

  PRIMARY KEY (`termId`) );
  
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_zones` (

  `zoneId` INT NOT NULL AUTO_INCREMENT ,

  `zone_name` VARCHAR(45) NOT NULL ,
  
  `contact_name` VARCHAR(100) NOT NULL ,

  `address` INT NOT NULL ,

  `latitude` DECIMAL(9,6) NOT NULL ,

  `longitude` DECIMAL(9,6) NOT NULL ,

  `timezone` VARCHAR(45) NULL ,

  `currency` INT NOT NULL ,

  `minduration` DECIMAL(5,2) NULL ,

  `terms` INT NOT NULL ,

  `support` INT NOT NULL ,

  `owner` INT NOT NULL ,

  `zone_type` INT NOT NULL ,
  
  `minimum_price` DECIMAL(5,2),
  
  `status` TINYINT NOT NULL DEFAULT 1 ,
  
  `createdby` VARCHAR(100) NULL ,
  
  `created_on` datetime DEFAULT NULL,
  
  `updatedby` VARCHAR(100) NULL ,

  `updated_on` datetime DEFAULT NULL,

  PRIMARY KEY (`zoneId`) ,

  INDEX `z_currency` (`currency` ASC) ,

  INDEX `z_terms` (`terms` ASC) ,

  INDEX `z_address` (`address` ASC) ,

  INDEX `z_support` (`support` ASC) ,

  INDEX `z_owner` (`owner` ASC) ,

  INDEX `z_type` (`zone_type` ASC) ,

  CONSTRAINT `z_currency`

    FOREIGN KEY (`currency` )

    REFERENCES `smartparking`.`sps_currency` (`currencyId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

    CONSTRAINT `z_terms`

    FOREIGN KEY (`terms` )

    REFERENCES `smartparking`.`sps_terms` (`termId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `z_support`

    FOREIGN KEY (`support` )

    REFERENCES `smartparking`.`sps_address` (`addressId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,
    
      CONSTRAINT `z_address`

    FOREIGN KEY (`address` )

    REFERENCES `smartparking`.`sps_address` (`addressId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,



  CONSTRAINT `z_owner`

    FOREIGN KEY (`owner` )

    REFERENCES `smartparking`.`sps_organization` (`orgid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `z_type`

    FOREIGN KEY (`zone_type` )

    REFERENCES `smartparking`.`sps_zonetype` (`typeId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
 
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_zoneavailibility` (

  `zoneId` INT NOT NULL ,

  `days` VARCHAR(10) NOT NULL ,

  `available` TINYINT(1)  NULL DEFAULT false ,

  `opening_time` TIME NULL ,

  `closing_time` TIME NULL ,

  PRIMARY KEY (`zoneId`, `days`) ,

  INDEX `da_zone` (`zoneId` ASC) ,

  CONSTRAINT `da_zone`

    FOREIGN KEY (`zoneId` )

    REFERENCES `smartparking`.`sps_zones` (`zoneId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_holidaylist` (

  `hid` INT NOT NULL AUTO_INCREMENT ,

  `zoneId` INT NOT NULL ,

  `from_date` DATE NOT NULL ,

  `to_date` DATE NOT NULL ,

  `description` VARCHAR(50) NOT NULL ,
  
  `createdby` VARCHAR(100) NULL ,
  
  `created_on` datetime DEFAULT NULL,
  
  `updatedby` VARCHAR(100) NULL ,

  `updated_on` datetime DEFAULT NULL,

  PRIMARY KEY (`hid`) ,

  INDEX `h_zone` (`zoneId` ASC) ,

  CONSTRAINT `h_zone`

    FOREIGN KEY (`zoneId` )

    REFERENCES `smartparking`.`sps_zones` (`zoneId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_zonefacilitymap` (

  `mapid` INT NOT NULL AUTO_INCREMENT ,

  `zoneId` INT NOT NULL ,

  `facilityId` INT NOT NULL ,

  PRIMARY KEY (`mapid`) ,

  INDEX `fz_zone` (`zoneId` ASC) ,

  INDEX `fz_facility` (`facilityId` ASC) ,

  CONSTRAINT `fz_zone`

    FOREIGN KEY (`zoneId` )

    REFERENCES `smartparking`.`sps_zones` (`zoneId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fz_facility`

    FOREIGN KEY (`facilityId` )

    REFERENCES `smartparking`.`sps_facility` (`facilityId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_zonetouser` (

  `mapid` INT NOT NULL AUTO_INCREMENT ,

  `zone` INT NOT NULL ,

  `user` INT NOT NULL ,

  PRIMARY KEY (`mapid`) ,

  INDEX `uz.zone` (`zone` ASC) ,

  INDEX `uz.user` (`user` ASC) ,

  CONSTRAINT `uz.zone`

    FOREIGN KEY (`zone` )

    REFERENCES `smartparking`.`sps_zones` (`zoneId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `uz.user`

    FOREIGN KEY (`user` )

    REFERENCES `smartparking`.`sps_user` (`userId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_zonelevel` (

  `levelId` INT NOT NULL AUTO_INCREMENT ,

  `zoneId` INT NOT NULL ,

  `floor` INT NOT NULL ,
  
  `status` TINYINT NOT NULL DEFAULT 1,

  PRIMARY KEY (`levelId`) ,

  INDEX `l.zone` (`zoneId` ASC) ,

  CONSTRAINT `l.zone`

    FOREIGN KEY (`zoneId` )

    REFERENCES `smartparking`.`sps_zones` (`zoneId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
    
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_levelvehiclemap` (

`mapid` INT NOT NULL AUTO_INCREMENT ,

  `levelId` INT NOT NULL ,

  `vehicle` INT NOT NULL ,

  `slot` INT NOT NULL ,

  `price` DECIMAL(5,2) NOT NULL ,

  `overnight_price` DECIMAL(5,2) NOT NULL ,

  `longhour_price` DECIMAL(5,2) NOT NULL ,

  `longhour_duration` INT NOT NULL ,

  `capacity` INT NOT NULL ,
  
  `total_capacity` INT NOT NULL ,

  PRIMARY KEY (`mapid`) ,
  
  INDEX `vl.level` (`levelId` ASC) ,

  INDEX `sl_vehicle` (`vehicle` ASC) ,

  INDEX `sl_slot` (`slot` ASC) ,

  CONSTRAINT `vl.level`

    FOREIGN KEY (`levelId` )

    REFERENCES `smartparking`.`sps_zonelevel` (`levelId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,
    
    CONSTRAINT `sl_vehicle`

    FOREIGN KEY (`vehicle` )

    REFERENCES `smartparking`.`sps_vehicletype` (`vid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `sl_slot`

    FOREIGN KEY (`slot` )

    REFERENCES `smartparking`.`sps_slottype` (`id` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);    

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_device` (

  `deviceId` INT NOT NULL ,

  `device_name` VARCHAR(45) NOT NULL ,

  `type` INT NOT NULL ,

  `mfg_name` VARCHAR(45) NOT NULL ,

  `serial_number` VARCHAR(20) NULL ,

  `uri` VARCHAR(45) NULL,

  `modal` VARCHAR(45) NOT NULL ,
  
  `latitude` DECIMAL(9,6) NULL ,
  
  `longitude` DECIMAL(9,6) NULL ,
  
  `address` VARCHAR(100) NULL ,
  
  `status` TINYINT NOT NULL DEFAULT 0 ,
  
  PRIMARY KEY (`deviceId`) ,

  INDEX `d_type` (`type` ASC) ,

  CONSTRAINT `d_type`

    FOREIGN KEY (`type` )

    REFERENCES `smartparking`.`sps_devicetype` (`deviceId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

ALTER TABLE `smartparking`.`sps_device` ADD COLUMN `zone` INT NULL  AFTER `status` , 

  ADD CONSTRAINT `d_zone`

  FOREIGN KEY (`zone` )

  REFERENCES `smartparking`.`sps_zones` (`zoneId` )

  ON DELETE NO ACTION

  ON UPDATE NO ACTION

, ADD INDEX `d_zone` (`zone` ASC) ;

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_resource` (

  `resourceId` INT NOT NULL AUTO_INCREMENT ,

  `resource` VARCHAR(100) NOT NULL ,

  `status` TINYINT NULL DEFAULT 1 ,

  PRIMARY KEY (`resourceId`) );

CREATE  TABLE `smartparking`.`sps_resourcemapping` (

  `mapId` INT NOT NULL AUTO_INCREMENT ,

  `deviceId` INT NOT NULL ,

  `resource` INT NOT NULL ,
  
  `subscriptionId` VARCHAR(45) NULL,
  
  `isSubscribed` TINYINT NULL DEFAULT 0 ,
 
  PRIMARY KEY (`mapId`) ,

  INDEX `dr_device` (`deviceId` ASC) ,
  INDEX `re_resource` (`resource` ASC) ,

  CONSTRAINT `dr_device`

    FOREIGN KEY (`deviceId` )

    REFERENCES `smartparking`.`sps_device` (`deviceId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,
	
	CONSTRAINT `re_resource`

    FOREIGN KEY (`resource` )

	REFERENCES `smartparking`.`sps_resource` (`resourceId` ) 

	ON DELETE NO ACTION   
	
	ON UPDATE NO ACTION
	
	);
  
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_parking` (

  `parkingId` INT NOT NULL AUTO_INCREMENT ,

  `level` INT NOT NULL ,

  `vehicle_type` INT NOT NULL ,

  `slot_type` INT NULL ,

   `status` TINYINT NOT NULL DEFAULT 1,
   
   `occupancy_status` TINYINT(4) NOT NULL DEFAULT 0,
   
   `slot_name` INT NOT NULL,
   
   `createdby` VARCHAR(100) NULL,
  
  `created_on` datetime DEFAULT NULL,
  
  `updatedby` VARCHAR(100) NULL,

  `updated_on` datetime DEFAULT NULL,

  PRIMARY KEY (`parkingId`) ,

  INDEX `p_location` (`level` ASC) ,

  INDEX `p_vtype` (`vehicle_type` ASC) ,

  INDEX `p_stype` (`slot_type` ASC) ,

  CONSTRAINT `p_location`

    FOREIGN KEY (`level` )

    REFERENCES `smartparking`.`sps_zonelevel` (`levelId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

    CONSTRAINT `p_vtype`

    FOREIGN KEY (`vehicle_type` )

    REFERENCES `smartparking`.`sps_vehicletype` (`vid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `p_stype`

    FOREIGN KEY (`slot_type` )

    REFERENCES `smartparking`.`sps_slottype` (`id` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_slottodevicemap` (

  `mapid` INT NOT NULL AUTO_INCREMENT ,

  `device` INT NOT NULL ,

  `slot` INT NOT NULL ,
  
  `fromdate` DATETIME NOT NULL ,

  `todate` DATETIME NULL ,


  PRIMARY KEY (`mapid`) ,

  INDEX `sd_slot` (`slot` ASC) ,

  INDEX `sd_device` (`device` ASC) ,

  CONSTRAINT `sd_slot`

    FOREIGN KEY (`slot` )

    REFERENCES `smartparking`.`sps_parking` (`parkingId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `sd_device`

    FOREIGN KEY (`device` )

    REFERENCES `smartparking`.`sps_device` (`deviceId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_customer` (

  `customerId` INT NOT NULL AUTO_INCREMENT,
  `created_on` datetime DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `login_attempts` int(11) DEFAULT NULL,
  `login_failure_on` datetime DEFAULT NULL,
  `mdnNumber` varchar(16) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status_id` tinyint(4) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `updated_on` datetime DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `country` varchar(50) NOT NULL,
  `countryCode` varchar(8) NOT NULL,
   
   
   PRIMARY KEY (`customerId`),
   UNIQUE INDEX `email_UNIQUE` (`email` ASC),
   UNIQUE INDEX `mdnNumber_UNIQUE` (`mdnNumber` ASC)
 );

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_token` (

  `app_nonce_id` int(11) NOT NULL AUTO_INCREMENT,
  
  `app_nonce` VARCHAR(20) NOT NULL ,

  `createdOn` datetime NOT NULL ,
  
  `expiryTime` datetime NOT NULL ,

  `customerId` INT NOT NULL ,
  
  `nonce_type` VARCHAR(1) NOT NULL ,
  
  `valid` tinyint(1) DEFAULT NULL,

  PRIMARY KEY (`app_nonce_id`) ,

  INDEX `t_customer` (`customerId` ASC) ,

  CONSTRAINT `t_customer`

    FOREIGN KEY (`customerId` )

    REFERENCES `smartparking`.`sps_customer` (`customerId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
    
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_payment` (

  `paymentId` INT NOT NULL AUTO_INCREMENT ,

  `transactionId` VARCHAR(45) NOT NULL ,

  `payment_gateway` INT NOT NULL ,

  `payment_status` TINYINT NOT NULL ,

  `payment_time` DATETIME NOT NULL ,

  `bookingfrom` DATETIME NOT NULL ,

  `bookingto` DATETIME NOT NULL ,

  `paymentInfo` LONGTEXT NULL ,
  
   `payment_amount` DECIMAL(10,2) NOT NULL ,

  PRIMARY KEY (`paymentId`),
  
  INDEX `t_gateway` (`payment_gateway` ASC) ,

  CONSTRAINT `t_gateway`

    FOREIGN KEY (`payment_gateway` )

    REFERENCES `smartparking`.`sps_paymentgateway` (`gatewayId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_booking` (

  `bookingId` INT NOT NULL AUTO_INCREMENT ,

  `customerId` INT NOT NULL ,

  `fromtime` DATETIME NOT NULL ,

  `totime` DATETIME NOT NULL ,
  
  `intime` DATETIME NULL,
  
  `outtime` DATETIME NULL,
  
  `parkingslot` INT NULL ,
  
  `status` INT NOT NULL DEFAULT 2 COMMENT '1: Booked; 2: Blocked; 3:Cancelled; 4:Occupied; 5:Consumed; 6:Expired' ,
  
  `qr_code` VARCHAR(100) NULL ,
  
   `levelId` INT NOT NULL ,
   
   `vehicleTypeId` INT NOT NULL ,
    
   `slotTypeId` INT NOT NULL ,
    
   `createdBy` VARCHAR(100) NULL ,
  
    `createdOn` DATETIME NULL,
    
   `updatedBy` VARCHAR(100) NULL ,
  
    `updatedOn` DATETIME NULL,
    
    `extendedtime` DATETIME NULL,

  PRIMARY KEY (`bookingId`) ,

  INDEX `b_customer` (`customerId` ASC) ,

  INDEX `b_slot` (`parkingslot` ASC) ,
  
  INDEX `b_level` (`levelId` ASC) ,
   
  INDEX `b_vehicleType` (`vehicleTypeId` ASC) ,
    
  INDEX `b_slotType` (`slotTypeId` ASC) ,
  
     CONSTRAINT `b_customer`

    FOREIGN KEY (`customerId` )

    REFERENCES `smartparking`.`sps_customer` (`customerId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

    CONSTRAINT `b_slot`

    FOREIGN KEY (`parkingslot` )

    REFERENCES `smartparking`.`sps_parking` (`parkingId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,
    
    CONSTRAINT `b_level`

    FOREIGN KEY (`levelId` )

    REFERENCES `smartparking`.`sps_zonelevel` (`levelId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,
    
    CONSTRAINT `b_vehicleType`

    FOREIGN KEY (`vehicleTypeId` )

    REFERENCES `smartparking`.`sps_vehicletype` (`vid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,
    
    CONSTRAINT `b_slotType`

    FOREIGN KEY (`slotTypeId` )

    REFERENCES `smartparking`.`sps_slottype` (`id` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);

CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_bookingpaymentmap` (

  `mapid` INT NOT NULL AUTO_INCREMENT ,

  `paymentId` INT NOT NULL ,

  `bookingId` INT NOT NULL ,

  PRIMARY KEY (`mapid`) ,

  INDEX `bp_booking` (`bookingId` ASC) ,

  INDEX `bp_payment` (`paymentId` ASC) ,

  CONSTRAINT `bp_booking`

    FOREIGN KEY (`bookingId` )

    REFERENCES `smartparking`.`sps_booking` (`bookingId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `bp_payment`

    FOREIGN KEY (`paymentId` )

    REFERENCES `smartparking`.`sps_payment` (`paymentId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);
    
CREATE  TABLE IF NOT EXISTS `smartparking`.`sps_customersearch` (

  `searchId` INT NOT NULL AUTO_INCREMENT ,

  `customerId` INT NOT NULL ,

  `vehicle` INT NULL ,

  `address` VARCHAR(100) NULL ,

  `fromtime` DATETIME NULL ,

  `totime` DATETIME NULL ,

  `slot_type` INT NOT NULL ,

  PRIMARY KEY (`searchId`) ,

  INDEX `s_cust` (`customerId` ASC) ,

  INDEX `s_vehicle` (`vehicle` ASC) ,

  INDEX `s_slot` (`slot_type` ASC) ,

  CONSTRAINT `s_cust`

    FOREIGN KEY (`customerId` )

    REFERENCES `smartparking`.`sps_customer` (`customerId` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `s_vehicle`

    FOREIGN KEY (`vehicle` )

    REFERENCES `smartparking`.`sps_vehicletype` (`vid` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `s_slot`

    FOREIGN KEY (`slot_type` )

    REFERENCES `smartparking`.`sps_slottype` (`id` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION);