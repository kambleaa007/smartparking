package com.techmahindra.smartparking.controller.zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.techmahindra.smartparking.pojo.dto.request.zone.SpZoneEditRequestDto;
import com.techmahindra.smartparking.pojo.dto.request.zone.SpZoneLevelRequestDto;
import com.techmahindra.smartparking.pojo.dto.request.zone.SpZoneRequestDto;
import com.techmahindra.smartparking.pojo.dto.response.common.SPSCommonResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneLevelResponseListDto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneLevelSlotMapDto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneLevelSlotMapListDto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneNameResponseListdto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneResponseDetailListdto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneResponseListdto;
import com.techmahindra.smartparking.service.zone.SPSZoneService;

@RestController
@ApiIgnore
@RequestMapping(value = "/zone")
public class SPSZoneController {

    @Autowired
    private SPSZoneService spsZoneService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewMyZones() {

        return new ModelAndView("myzones");
    }

    @RequestMapping(value = "/newzone", method = RequestMethod.GET)
    public ModelAndView addNewZone() {
        return new ModelAndView("add_zone");
    }

    @RequestMapping(value = "/zonedetail/{id}", method = RequestMethod.GET)
    public ModelAndView viewZoneDetails(@PathVariable("id") int zoneId) {
        return new ModelAndView("zone_detail");
    }

    @RequestMapping(value = "levels/{id}", method = RequestMethod.GET)
    public SpZoneLevelResponseListDto getLevel(@PathVariable("id") Integer id) {
        return spsZoneService.getLevelByZoneId(id);
    }

    @RequestMapping(value = "/price/list/{id}", method = RequestMethod.GET)
    public SpZoneLevelResponseListDto getZoneParkingPrices(@PathVariable("id") Integer id) {
        return spsZoneService.getLevelPricesByZoneId(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public SpZoneResponseDetailListdto getZoneList() {
    	SpZoneResponseDetailListdto list = spsZoneService.getZoneList();
        return list;
    }

    @RequestMapping(value = "/parkinglist/{id}", method = RequestMethod.GET)
    public ModelAndView viewParkingList(@PathVariable("id") int zoneId) {
        return new ModelAndView("parkinglevel");
    }

    @RequestMapping(value = "/ratecard/{id}", method = RequestMethod.GET)
    public ModelAndView viewRateCard(@PathVariable("id") int zoneId) {
        return new ModelAndView("rate_card");
    }

    @RequestMapping(value = "/parking/list/{id}", method = RequestMethod.GET)
    public SpZoneLevelSlotMapDto getZoneParkingList(@PathVariable("id") Integer id) {
    	SpZoneLevelSlotMapDto list = spsZoneService.getParkingList(id);
        return list;
    }

    @RequestMapping(value = "/parking/occupancy/{id}", method = RequestMethod.GET)
    public SpZoneLevelSlotMapDto getZoneParkingOccupancy(@PathVariable("id") Integer id) {
    			return spsZoneService.getParkingOccupancy(id);
	}
    
    @RequestMapping(value = "/parking/totaloccupancy/{zoneIds}", method = RequestMethod.GET)
    public SpZoneLevelSlotMapListDto getconsolidatedZoneParkingOccupancy(@PathVariable("zoneIds") String zoneIds) {
    			return spsZoneService.getConsolidatedParkingOccupancy(zoneIds);
	}


    @RequestMapping(value = "/owner/list/{owner}", method = RequestMethod.GET)
    public SpZoneResponseListdto getZoneListByOwner(
            @PathVariable("owner") Integer ownerId) {
        SpZoneResponseListdto list = spsZoneService.getZoneListByOwner(ownerId);
        return list;
    }

    @RequestMapping(value = "/auth/list", method = RequestMethod.GET)
    public SpZoneNameResponseListdto getAuthorizedZoneList() {
    	SpZoneNameResponseListdto list = spsZoneService.getAuthorizedZoneList();
        return list;
    }

    @RequestMapping(value = "/addzone", method = RequestMethod.POST)
    public SPSCommonResponseDto addZones(@RequestBody SpZoneRequestDto zone) {

        return new SPSCommonResponseDto(spsZoneService.addZone(zone));
    }

    @RequestMapping(value = "/deletezone/{id}", method = RequestMethod.DELETE)
    public SPSCommonResponseDto deleteZones(@PathVariable("id") Integer id) {

        return new SPSCommonResponseDto(spsZoneService.deleteZone(id));
    }

    @RequestMapping(value = "/deletelevel/{id}", method = RequestMethod.DELETE)
    public SPSCommonResponseDto deleteLevel(@PathVariable("id") Integer id) {

        return new SPSCommonResponseDto(spsZoneService.deleteLevel(id));
    }
    
    @RequestMapping(value = "/updatestatus/{id}/{status}", method = RequestMethod.POST)
    public SPSCommonResponseDto setStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {

        return new SPSCommonResponseDto(spsZoneService.updateStatus(id,status));
    }

    @RequestMapping(value = "/parking/updateslottype/{id}/{stype}", method = RequestMethod.POST)
    public SPSCommonResponseDto setSlotType(@PathVariable("id") Integer id,@PathVariable("stype") Integer stype) {

        return new SPSCommonResponseDto(spsZoneService.updateSlotType(id,stype));
    }

    @RequestMapping(value = "/parking/updateDevice/{id}/{deviceId}", method = RequestMethod.POST)
    public SPSCommonResponseDto mapSlotDevice(@PathVariable("id") Integer id,@PathVariable("deviceId") Integer deviceId) {

        return new SPSCommonResponseDto(spsZoneService.mapSlotDevice(id,deviceId));
    }

    @RequestMapping(value = "/parking/updateprice/{level}/{stype}/{vtype}/{price}", method = RequestMethod.POST)
    public SPSCommonResponseDto setSlotPrice(@PathVariable("level") Integer level,@PathVariable("stype") Integer stype,@PathVariable("vtype") Integer vtype,@PathVariable("price") String price) {

        return new SPSCommonResponseDto(spsZoneService.updateSlotPrice(level,stype,vtype,price));
    }

    @RequestMapping(value = "/parking/updatestatus/{id}/{status}", method = RequestMethod.POST)
    public SPSCommonResponseDto setSlotStatus(@PathVariable("id") Integer id,@PathVariable("status") Integer status) {

        return new SPSCommonResponseDto(spsZoneService.updateSlotStatus(id,status));
    }

    @RequestMapping(value = "/updatezone", method = RequestMethod.POST)
    public SPSCommonResponseDto editZones(@RequestBody SpZoneEditRequestDto zone) {

        return new SPSCommonResponseDto(spsZoneService.editZone(zone));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SpZoneResponseDto getZone(@PathVariable("id") Integer id) {
        return spsZoneService.getZoneById(id);
    }

    @RequestMapping(value = "/addSlot", method = RequestMethod.POST)
    public SPSCommonResponseDto addSlots(@RequestBody SpZoneLevelRequestDto level) {

        return new SPSCommonResponseDto(spsZoneService.addLevel(level));
    }

   

    
    

}
