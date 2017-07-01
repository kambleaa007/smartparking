package com.techmahindra.smartparking.controller.parking;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.constant.IResponse;
import com.techmahindra.smartparking.controller.base.AbstractBaseController;
import com.techmahindra.smartparking.paypal.Webhook;
import com.techmahindra.smartparking.pojo.dto.request.parking.ParkingEntryRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.parking.ParkingExitRequestDTO;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingExtendedCostResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.parking.ParkingEntryResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.parking.ParkingExitResponseDTO;
import com.techmahindra.smartparking.service.parking.SPSParkingService;
import com.techmahindra.smartparking.utility.CommonUtility;

@RestController
@RequestMapping(value = "/parking")
public class SPSParkingController extends AbstractBaseController {

    @Autowired
    private SPSParkingService spsParkingService;

    @ApiOperation(value = "", notes = "paypal", produces = "application/json")
    @RequestMapping(value = "/webhook", method = RequestMethod.POST)
    public BookingExtendedCostResponseDTO webhook(
            @RequestBody final Webhook requestBody) {

       
        return null;

    }

    @ApiOperation(value = "", notes = "Entry Parking", produces = "application/json")
    @RequestMapping(value = "/entry", method = RequestMethod.POST)
    public ParkingEntryResponseDTO entryParking(
            @RequestBody final ParkingEntryRequestDTO requestBody) {
       
        try{
        ParkingEntryResponseDTO parkingEntryResponseDTO=spsParkingService.entryParking(requestBody);
        
        if(parkingEntryResponseDTO.isValid()){
        	
        	parkingEntryResponseDTO.setAppHeader(CommonUtility
                      .getAppResponseHeader(IResponse.SUCCESS_CODE,
                              IResponse.SUCCESS_MESSAGE_PARKING_ENTRY));

              return parkingEntryResponseDTO;
        }
        
        parkingEntryResponseDTO.setAppHeader(CommonUtility
                .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                        IResponse.ERROR_MESSAGE_PARKING_ENTRY_ORCODE));
        
        return parkingEntryResponseDTO;

        }
        catch(GenericDBException e)
        {
        	ParkingEntryResponseDTO parkingEntryResponseDTO=new ParkingEntryResponseDTO();
        	  parkingEntryResponseDTO.setAppHeader(CommonUtility
                      .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                              IResponse.ERROR_MESSAGE_PARKING_ENTRY+""+e.getMessage()));
        	  parkingEntryResponseDTO.setValid(false);
        	  
        	  return parkingEntryResponseDTO;
        }
        catch(Exception ex)
        {
        	ParkingEntryResponseDTO parkingEntryResponseDTO=new ParkingEntryResponseDTO();
        	  parkingEntryResponseDTO.setAppHeader(CommonUtility
                      .getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
                              IResponse.ERROR_MESSAGE_PARKING_ENTRY+""+ex.getMessage()));
        	  parkingEntryResponseDTO.setValid(false);
        	  
        	  return parkingEntryResponseDTO;
        }
        
        
     
    }

    @ApiOperation(value = "", notes = "Exit Parking", produces = "application/json")
    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public ParkingExitResponseDTO exitParking(
            @RequestBody final ParkingExitRequestDTO requestBody) throws GenericDBException {
        
       return spsParkingService.exitParking(requestBody);
     

    }

}
