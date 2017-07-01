package com.techmahindra.smartparking.controller.device;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.techmahindra.smartparking.common.exception.application.InvalidResponseApplicationException;
import com.techmahindra.smartparking.constant.IConstant;
import com.techmahindra.smartparking.constant.IResponse;
import com.techmahindra.smartparking.controller.AbstractController;
import com.techmahindra.smartparking.pojo.dto.request.device.SpDeviceListRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.device.SpDeviceRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.device.SpDeviceRequestEditDTO;
import com.techmahindra.smartparking.pojo.dto.request.subscription.SpSubscriptionRequestDTO;
import com.techmahindra.smartparking.pojo.dto.response.common.SPSCommonResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.device.DeviceListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.device.DeviceTypeListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.device.ResourceListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.device.SpDeviceResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.device.SpSensorReportListResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.device.SubscriptionDeletionResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.device.SubscriptionResponseDTO;
import com.techmahindra.smartparking.service.device.SPSDeviceService;
import com.techmahindra.smartparking.service.device.SPSSensorService;
import com.techmahindra.smartparking.utility.CommonUtility;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
@RequestMapping(value = "/device")
public class SPSDeviceController  extends AbstractController {
	
	private final Logger logger = Logger.getLogger(IConstant.DEVICE_LOGGER + this.getClass());
	
	@Autowired
	private SPSDeviceService spsDeviceService;
	
	@Autowired
	private SPSSensorService sensorService;
	
	@RequestMapping(value = "/newdevice", method = RequestMethod.GET)
	public ModelAndView getNewDeviceScreen(HttpServletRequest request, HttpServletResponse response) {

			return new ModelAndView("add_device");
		
	}

	@RequestMapping(value = "/viewdevice", method = RequestMethod.GET)
	public ModelAndView getDeviceListScreen(HttpServletRequest request, HttpServletResponse response) {

			return new ModelAndView("device");
		
	}

	
	@RequestMapping(value = "/subscribedevice", method = RequestMethod.GET)
	public ModelAndView addSubscription(HttpServletRequest request, HttpServletResponse response) {

			return new ModelAndView("device_subscription");
		
	}
	
	@RequestMapping(value = "/devicedetail/{deviceId}", method = RequestMethod.GET)
	public ModelAndView getDeviceDetailScreen(HttpServletRequest request, HttpServletResponse response) {

			return new ModelAndView("device_detail");
		
	}

	@RequestMapping(value = "/slottype/list", method = RequestMethod.GET)
	public DeviceTypeListResponseDTO getDeviceTypeList() {
		DeviceTypeListResponseDTO list = spsDeviceService.getDeviceTypeList();
		return list;
	}
	
	@RequestMapping(value = "/device/list", method = RequestMethod.GET)
	public DeviceListResponseDTO getDeviceList() {
		DeviceListResponseDTO list = spsDeviceService.getAuthorizedDeviceList();
		return list;
	}
	
	@RequestMapping(value = "/list/{zoneId}", method = RequestMethod.GET)
	public DeviceListResponseDTO getDeviceListByZone(@PathVariable("zoneId") int id) {
		DeviceListResponseDTO list = spsDeviceService.getDeviceList(id);
		return list;
	}
	
	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	public SPSCommonResponseDto addDevice(@RequestBody SpDeviceRequestDTO dto) {
		return new SPSCommonResponseDto(spsDeviceService.addDevice(dto));
		
	}
	
	@RequestMapping(value = "/uploadDevices", method = RequestMethod.POST)
	public SPSCommonResponseDto uploadDevice(@RequestBody SpDeviceListRequestDTO dto) {
		return new SPSCommonResponseDto(spsDeviceService.uploadDevice(dto));
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SpDeviceResponseDTO getUser(@PathVariable("id") int id) {

		return spsDeviceService.getDevice(id);

	}
	
	@RequestMapping(value = "/updateDevice", method = RequestMethod.POST)
	public SPSCommonResponseDto updateDevice(@RequestBody SpDeviceRequestEditDTO dto) {
		return new SPSCommonResponseDto(spsDeviceService.editDevice(dto));
		
	}
	
	@RequestMapping(path="/sensor/readings", method=RequestMethod.GET, params="deviceIds")
	public SpSensorReportListResponseDTO getSensorDataByDeviceIdList(@RequestParam("deviceIds") String deviceIds) {
		SpSensorReportListResponseDTO sensorReportListResponseDTO = new SpSensorReportListResponseDTO();
		try {
			List<String> deviceIdList = new ArrayList<>();
			if (deviceIds != null && !deviceIds.isEmpty()) {
				deviceIdList = Arrays.asList(deviceIds.split(",")).stream().map(s ->s.trim())
						.collect(Collectors.toList());
			}
			
			logger.info("deviceIdList=" + deviceIdList);
			sensorReportListResponseDTO = sensorService.findOccupancyByDeviceIds(deviceIdList);
		} catch (Exception ex) {
			sensorReportListResponseDTO.setAppHeader(
					CommonUtility.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION, ex.getMessage()));
		}

		return sensorReportListResponseDTO;
	}
	
	@RequestMapping(value = "/subscriptionDetails", method = RequestMethod.POST)
	public SubscriptionResponseDTO subscribeDevice(@RequestBody SpSubscriptionRequestDTO dto) throws InvalidResponseApplicationException {
		return spsDeviceService.subscribeDevice(dto);
		
	}
	
	@RequestMapping(value = "/unsubscribe/{subscriptionId}", method = RequestMethod.GET)
	public SubscriptionDeletionResponseDTO unsubscribeDevice(@PathVariable("subscriptionId") String subscriptionId) throws InvalidResponseApplicationException {
		return spsDeviceService.unsubscribeDevice(subscriptionId);
		
	}
		
	
	@RequestMapping(value = "/deviceresource/list/{deviceID}", method = RequestMethod.GET)
	public ResourceListResponseDTO getResourceList(@PathVariable("deviceID") int id) {
		ResourceListResponseDTO list = spsDeviceService.getResourceList(id);
		return list;
	}
}
