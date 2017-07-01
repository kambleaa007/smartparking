package com.techmahindra.smartparking.controller.reports;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import springfox.documentation.annotations.ApiIgnore;

import com.techmahindra.smartparking.controller.AbstractController;
import com.techmahindra.smartparking.pojo.dto.response.reports.SpReportResponseListDTO;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneLevelSlotMapDto;
import com.techmahindra.smartparking.pojo.dto.response.reports.SpBookingReportResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.reports.SpBookingReportResponseListDTO;
import com.techmahindra.smartparking.service.reports.SPSReportsService;

@RestController
@ApiIgnore
@RequestMapping(value = "/reports")
public class SPSReportsController  extends AbstractController {
	
	 @Autowired
	 private SPSReportsService spsReportService;
	
	@RequestMapping(value = "/revenueReport", method = RequestMethod.GET)
	public ModelAndView getRevenueReportScreen(HttpServletRequest request, HttpServletResponse response) {

			return new ModelAndView("revenue_report");
		
	}

	@RequestMapping(value = "/revenueDetails/{zoneId}/{fromTime}/{toTime}", method = RequestMethod.GET)
	public SpReportResponseListDTO getRevenueReport(@PathVariable("zoneId") Integer zoneId,@PathVariable("fromTime") String fromTime,@PathVariable("toTime") String toTime)
	{
		return spsReportService.getRevenueReport(zoneId,fromTime,toTime);
	}
	
	@RequestMapping(value = "/revenue", method = RequestMethod.GET)
	public SpReportResponseListDTO getConsolidatedRevenueReport()
	{
		return spsReportService.getConsolidatedRevenueReport();
	}
	
	@RequestMapping(value = "/bookingReport", method = RequestMethod.GET)
	public ModelAndView getBookingReportScreen(HttpServletRequest request, HttpServletResponse response) {

			return new ModelAndView("booking_report");
		
	}

	@RequestMapping(value = "/bookingReport/{id}", method = RequestMethod.GET)
	public ModelAndView getBookingReportDetailScreen(HttpServletRequest request, HttpServletResponse response) {

			return new ModelAndView("booking_detail");
		
	}

	@RequestMapping(value = "/bookingDetails/{zoneId}/{fromTime}/{toTime}/{status}", method = RequestMethod.GET)
	public SpBookingReportResponseListDTO getBookingReport(@PathVariable("zoneId") Integer zoneId,@PathVariable("fromTime") String fromTime,@PathVariable("toTime") String toTime,@PathVariable("status") int status)
	{
		return spsReportService.getBookingRevenueReport(zoneId,fromTime,toTime, status);
	}
	
	@RequestMapping(value = "/bookingDetailsById/{bookingId}", method = RequestMethod.GET)
	public SpBookingReportResponseDTO getBookingDetailReport(@PathVariable("bookingId") Integer bookingId)
	{
		return spsReportService.getBookingDetailReport(bookingId);
	}
	
	@RequestMapping(value = "/booking", method = RequestMethod.GET)
	public SpBookingReportResponseListDTO getConsolidatedBookingReport()
	{
		return spsReportService.getConsolidatedBookingRevenueReport();
	}

		
}
