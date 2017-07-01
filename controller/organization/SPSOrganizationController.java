package com.techmahindra.smartparking.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.constant.SPSConstants;
import com.techmahindra.smartparking.pojo.dto.request.organization.OrganizationMapRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.organization.OrganizationRequestDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.SPSCommonResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.organization.OrganizationListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.organization.OrganizationOwnerDependentResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.organization.OrganizationResponseDTO;
import com.techmahindra.smartparking.service.organization.SPSOrganizationService;

@RestController
@ApiIgnore
@RequestMapping(value = "/organization")
public class SPSOrganizationController {

	@Autowired
	private SPSOrganizationService spsOrganizationService;

	@RequestMapping(value = "/parkingowner", method = RequestMethod.GET)
	public ModelAndView viewParkingOwner() {

		return new ModelAndView("parking_owner");
	}

	@RequestMapping(value = "/serviceprovider", method = RequestMethod.GET)
	public ModelAndView viewServiceProviders() {

		return new ModelAndView("service_provider");
	}

	@RequestMapping(value = "/newparkingowner", method = RequestMethod.GET)
	public ModelAndView newParkingOwner() {

		return new ModelAndView("add_parking_owner");
	}

	@RequestMapping(value = "/newserviceprovider", method = RequestMethod.GET)
	public ModelAndView newServiceProviders() {

		return new ModelAndView("add_service_provider");
	}

	@RequestMapping(value = "/parkingowner/list", method = RequestMethod.GET)
	public OrganizationListResponseDTO getProviderList() {
		OrganizationListResponseDTO list = getProviderList1();
		return list;
	}

	@RequestMapping(value = "/serviceowner/list", method = RequestMethod.GET)
	public OrganizationListResponseDTO getServiceOwnerList() {
		OrganizationListResponseDTO list = getServiceOwnerList1();
		return list;
	}

	@RequestMapping(value = "/actor/list", method = RequestMethod.GET)
	public OrganizationListResponseDTO getServiceList() {
		OrganizationListResponseDTO list = spsOrganizationService.getServiceList();
		return list;
	}

	@RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
	public SPSCommonResponseDto addOrganization(@RequestBody OrganizationRequestDTO orgdto) {

		try {
			return new SPSCommonResponseDto(spsOrganizationService.addOrganization(orgdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(e.getMessage());
		}
	}

	@RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
	public SPSCommonResponseDto updateOrganization(@RequestBody OrganizationRequestDTO orgdto) {

		try {
			return new SPSCommonResponseDto(spsOrganizationService.updateOrganization(orgdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(e.getMessage());
		}
	}

	@RequestMapping(value = "/deregisterOrganization", method = RequestMethod.POST)
	public SPSCommonResponseDto deregisterOrganization(@RequestBody OrganizationMapRequestDTO orgdto) {

		try {
			return new SPSCommonResponseDto(spsOrganizationService.deregisterOrganization(orgdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(SPSConstants.DEREGISTER_ORGANIZATION_FAIL);
		}
	}

	@RequestMapping(value = "/registerOrganization", method = RequestMethod.POST)
	public SPSCommonResponseDto registerOrganization(@RequestBody OrganizationMapRequestDTO orgdto) {

		try {
			return new SPSCommonResponseDto(spsOrganizationService.registerParkingOwner(orgdto));
		} catch (GenericDBException e) {
			return new SPSCommonResponseDto(SPSConstants.REGISTER_ORGANIZATION_FAIL);
		}
	}

	@RequestMapping(value = "orgdetail/{orgId}", method = RequestMethod.GET)
    public ModelAndView viewOrgDetail(@PathVariable("orgId") int id) {
        return new ModelAndView("service_provider_detail");
    }
    
	@RequestMapping(value = "regparkingowner/{orgId}", method = RequestMethod.GET)
    public ModelAndView getRegisterParkingRegistrationScreen(@PathVariable("orgId") int id) {
        return new ModelAndView("register_parking_owner");
    }
    
	@RequestMapping(value = "deregparkingowner/{orgId}", method = RequestMethod.GET)
    public ModelAndView getDeregisterParkingRegistrationScreen(@PathVariable("orgId") int id) {
        return new ModelAndView("deregister_parking_owner");
    }
    
	@RequestMapping(value = "orgdetail1/{orgId}", method = RequestMethod.GET)
    public ModelAndView viewOrgDetail1(@PathVariable("orgId") int id) {
        return new ModelAndView("parking_owner_detail");
    }
    
	@RequestMapping(value = "/{orgId}", method = RequestMethod.GET)
    public OrganizationResponseDTO getOrgDetail(@PathVariable("orgId") int id) {
        return spsOrganizationService.getOrganization(id);
    }
	
	@RequestMapping(value = "regdetail/{orgId}", method = RequestMethod.GET)
    public OrganizationOwnerDependentResponseDTO getOrgDetailForRegistration(@PathVariable("orgId") int id) {
        return spsOrganizationService.getOrgDetailForRegistration(id);
    }
    
	private OrganizationListResponseDTO getServiceOwnerList1() {
		return spsOrganizationService.getServiceOwnerList();
	}

	private OrganizationListResponseDTO getProviderList1() {
		return spsOrganizationService.getProviderList();
	}

}
