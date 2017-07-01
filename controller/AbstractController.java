package com.techmahindra.smartparking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techmahindra.smartparking.constant.IOrganizationType;
import com.techmahindra.smartparking.constant.IUserRole;
import com.techmahindra.smartparking.dao.jpa.organization.ISpOrganizationTypeCRUDRepository;
import com.techmahindra.smartparking.pojo.dbentity.common.GenericDBResult;
import com.techmahindra.smartparking.pojo.dbentity.organization.SpOrganization;
import com.techmahindra.smartparking.pojo.dbentity.zone.SpZone;
import com.techmahindra.smartparking.pojo.dto.request.user.GenericSearchRequest;
import com.techmahindra.smartparking.pojo.dto.response.organization.SpOrganizationDto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneDto;
import com.techmahindra.smartparking.security.SecurityUser;

public abstract class AbstractController {
	
	private static final String SOLUTION_PROVIDER = "SolutionProvider";
	private static final String SERVICE_PROVIDER = "ServiceProvider";
	private static final String PARKING_OWNER = "ParkingOwner";
	
	@Autowired
	private ISpOrganizationTypeCRUDRepository orgTypeRepo;
	

	private void populateUserRoleAndParkingZones(GenericSearchRequest requestBody, SecurityUser loggedInUser) {
		String role = loggedInUser.getRole();
		if ("SuperAdmin".equals(role)) {
			requestBody.setRole(IUserRole.SUPERADMIN);
		} else if ("Solution_User_Access".equals(role)) {
			requestBody.setRole(IUserRole.SOLUTION_USER_ACCESS);
		} else if ("Solution_Organization_Access".equals(role)) {
			requestBody.setRole(IUserRole.SOLUTION_ORGANIZATION_ACCESS);
		}
		else if ("Device_Access".equals(role)) {
			requestBody.setRole(IUserRole.DEVICE_ACCESS);
		}
		else if ("ServiceAdmin".equals(role)) {
			requestBody.setRole(IUserRole.SERVICEADMIN);
		}
		else if ("ServiceRevenue".equals(role)) {
			requestBody.setRole(IUserRole.SERVICEREVENUE);
		}
		else if ("ServiceReport".equals(role)) {
			requestBody.setRole(IUserRole.SERVICEREPORT);
		}
		else if ("Service_User_Access".equals(role)) {
			requestBody.setRole(IUserRole.SERVICE_USER_ACCESS);
		}
		else if ("ParkingAdmin".equals(role)) {
			requestBody.setRole(IUserRole.PARKINGADMIN);
		}
		else if ("ParkingRevenue".equals(role)) {
			requestBody.setRole(IUserRole.PARKINGREVENUE);
		}
		else if ("ParkingReport".equals(role)) {
			requestBody.setRole(IUserRole.PARKINGREPORT);
		}
		else if ("Parking_User_Access".equals(role)) {
			requestBody.setRole(IUserRole.PARKING_USER_ACCESS);
		}
		
		requestBody.setAuthorizedParkingZones(loggedInUser.getAuthorizedParkingZones());
		requestBody.setAuthorizedOrganizationIds(loggedInUser.getAuthorizedOrganizationIds());

		
		
	}

	private void populateUserType(GenericSearchRequest requestBody,
			SecurityUser loggedInUser) {
		String userType = loggedInUser.getUserType();
		if (userType.equals(SOLUTION_PROVIDER)) {
			requestBody.setUserType(IOrganizationType.SOLUTION_OWNER);
		} if (userType.equals(SERVICE_PROVIDER)) {
			requestBody.setUserType(IOrganizationType.SERVICE_PROVIDER);
		}
		if (userType.equals(PARKING_OWNER)) {
			requestBody.setUserType(IOrganizationType.PARKING_OWNER);
		}
	}

	 protected void populateAuthorizedParkingZones(GenericSearchRequest requestBody,
			SecurityUser loggedInUser, boolean hasSufficientRole) {
		if (hasSufficientRole) {
			List<SpZoneDto> authorizedSiteIds = loggedInUser.getAuthorizedParkingZones();
			requestBody.setAuthorizedParkingZones(authorizedSiteIds);
		}
	}

	protected void populateDeletablePermission(GenericDBResult responseBody,
			boolean hasSufficientRole) {
		responseBody.setDeletable(hasSufficientRole);
	}

	protected void populateEditablePermission(GenericDBResult responseBody,
			boolean hasSufficientRole) {
		responseBody.setEditable(hasSufficientRole);
	}

	private void populateOrganization(GenericSearchRequest requestBody,
			SecurityUser loggedInUser) {
		
			List<SpOrganizationDto> orgList = loggedInUser.getAuthorizedOrganizationIds();
			requestBody.setAuthorizedOrganizationIds(orgList);

		
	}

	public void populateSessionData(GenericSearchRequest request,
			SecurityUser loggedInUser) {
		populateUserType(request, loggedInUser);
		populateUserRoleAndParkingZones(request, loggedInUser);
		populateOrganization(request,loggedInUser);
		
	}
}
