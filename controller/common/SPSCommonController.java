package com.techmahindra.smartparking.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

import com.techmahindra.smartparking.pojo.dto.response.common.CountryListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.CurrencyListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.FacilityListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.RoleListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.RolePermissionListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.SecurityQuestionListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.SlotTypeListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.StateListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.VehicleTypeListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.ZoneTypeListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.device.ResourceListResponseDTO;
import com.techmahindra.smartparking.service.common.SPSCommonService;


@RestController
@ApiIgnore
@RequestMapping(value = "/common")
public class SPSCommonController {
	
	@Autowired
	private SPSCommonService spsCommonService;
		
	@RequestMapping(value = "/currency/list", method = RequestMethod.GET)
	public CurrencyListResponseDTO getCurrencyList() {
		CurrencyListResponseDTO list = spsCommonService.getCurrencyList();
		return list;
	}
	
	@RequestMapping(value = "/zonetype/list", method = RequestMethod.GET)
	public ZoneTypeListResponseDTO getZoneTypeList() {
		ZoneTypeListResponseDTO list = spsCommonService.getZoneTypeList();
		return list;
	}
	
	@RequestMapping(value = "/slottype/list", method = RequestMethod.GET)
	public SlotTypeListResponseDTO getSlotTypeList() {
		SlotTypeListResponseDTO list = spsCommonService.getSlotTypeList();
		return list;
	}
	
	@RequestMapping(value = "/vehicletype/list", method = RequestMethod.GET)
	public VehicleTypeListResponseDTO getVehicleTypeList() {
		VehicleTypeListResponseDTO list = spsCommonService.getVehicleTypeList();
		return list;
	}
	
	@RequestMapping(value = "/deviceresource/list", method = RequestMethod.GET)
	public ResourceListResponseDTO getResourceList() {
		ResourceListResponseDTO list = spsCommonService.getResourceList();
		return list;
	}
	
	@RequestMapping(value = "/facility/list", method = RequestMethod.GET)
	public FacilityListResponseDTO getFacilityList() {
		FacilityListResponseDTO list = spsCommonService.getFacilityList();
		return list;
	}
	
	@RequestMapping(value = "/orgrole/list/{org}", method = RequestMethod.GET)
	public RoleListResponseDTO getRoleListForOrgType(@PathVariable("org") int orgId) {
		RoleListResponseDTO list = spsCommonService.getRoleList(orgId);
		return list;
	}
	
	@RequestMapping(value = "/role/list/{role}", method = RequestMethod.GET)
	public RoleListResponseDTO getRoleList(@PathVariable("role") String role) {
		RoleListResponseDTO list = spsCommonService.getRoleListForUser(role);
		return list;
	}
	
	@RequestMapping(value = "/role/permlist/{role}", method = RequestMethod.GET)
	public RolePermissionListResponseDTO getRoleList(@PathVariable("role") int role) {
		RolePermissionListResponseDTO list = spsCommonService.getPermissionListForRole(role);
		return list;
	}
	
	@RequestMapping(value = "/country/list", method = RequestMethod.GET)
	public CountryListResponseDTO getCountryList() {
		CountryListResponseDTO list = spsCommonService.getCountryList();
		return list;
	}
	
	@RequestMapping(value = "/state/list/{country}", method = RequestMethod.GET)
	public StateListResponseDTO getCountryList(@PathVariable("country") int country) {
		StateListResponseDTO list = spsCommonService.getStateList(country);
		return list;
	}
	
	@RequestMapping(value = "/securityquestions/list", method = RequestMethod.GET)
	public SecurityQuestionListResponseDTO getSecurityQuestions() {
		SecurityQuestionListResponseDTO list = spsCommonService.getQuestionList();
		return list;
	}
	
}
