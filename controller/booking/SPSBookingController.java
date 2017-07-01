package com.techmahindra.smartparking.controller.booking;

import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.techmahindra.smartparking.common.exception.application.GenericDBException;
import com.techmahindra.smartparking.constant.IResponse;
import com.techmahindra.smartparking.controller.base.AbstractBaseController;
import com.techmahindra.smartparking.pojo.dto.AbstractBaseDTO;
import com.techmahindra.smartparking.pojo.dto.error.response.APPErrorResponse;
import com.techmahindra.smartparking.pojo.dto.request.booking.BookingExtendedCostRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.booking.BookingPaymentRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.booking.BookingRequestDTO;
import com.techmahindra.smartparking.pojo.dto.request.zone.SpZoneProximityRequestDto;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingCancelResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingCostResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingExtendedCostResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingHistoryDetailsResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingPaymentResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.booking.BookingResponseDTO;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpSlotAndVehicleTypeResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneLevelDetailResponseDto;
import com.techmahindra.smartparking.pojo.dto.response.zone.SpZoneProximityListResponseDto;
import com.techmahindra.smartparking.service.booking.SPSBookingService;
import com.techmahindra.smartparking.utility.CommonUtility;

@RestController
@RequestMapping(value = "/booking")
public class SPSBookingController extends AbstractBaseController {

	@Autowired
	private SPSBookingService spsBookingService;

	/**
	 * Fetches the zone within the range of 5 miles
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@ApiOperation(value = "", notes = "Fetch Proximity Parking Zone", produces = "application/json")
	@RequestMapping(value = "/proximitylist", method = RequestMethod.GET)
	public AbstractBaseDTO fetchProximityList(@RequestParam double latitude,
			@RequestParam double longitude) {

		SpZoneProximityRequestDto spZoneProximityRequestDTO = new SpZoneProximityRequestDto();
		spZoneProximityRequestDTO.setLatitude(latitude);
		spZoneProximityRequestDTO.setLongitude(longitude);
		SpZoneProximityListResponseDto spZoneProximityListResponseDto = null;

		try {
			spZoneProximityListResponseDto = spsBookingService
					.getZoneProximityList(spZoneProximityRequestDTO);

			if (spZoneProximityListResponseDto == null) {
				spZoneProximityListResponseDto = new SpZoneProximityListResponseDto();
				spZoneProximityListResponseDto.setAppHeader(CommonUtility
						.getAppResponseHeader(
								IResponse.ERROR_CODE_FETCH_PARKINGZONE,
								IResponse.ERROR_MESSAGE_FETCH_PARKINGZONE));
			} else {

				spZoneProximityListResponseDto.setAppHeader(CommonUtility
						.getAppResponseHeader(IResponse.SUCCESS_CODE,
								IResponse.SUCCESS_MESSAGE_FETCH_PARKINGZONE));
			}

			return spZoneProximityListResponseDto;

		} catch (GenericDBException e) {
			// TODO Auto-generated catch block
			spZoneProximityListResponseDto = new SpZoneProximityListResponseDto();
			spZoneProximityListResponseDto.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							e.getMessage()));
			return spZoneProximityListResponseDto;
		} catch (final Exception ex) {

			spZoneProximityListResponseDto = new SpZoneProximityListResponseDto();
			spZoneProximityListResponseDto.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							ex.getMessage()));
			return spZoneProximityListResponseDto;

		}

	}

	/**
	 * Fetches the parking Level details
	 * 
	 * @param zoneId
	 * @param vehicleType
	 * @param slotType
	 * @param toTime
	 * @param fromTime
	 * @param timeZone
	 * @return
	 */
	@ApiOperation(value = "", notes = "Fetch Parking Zone Level Details", produces = "application/json")
	@RequestMapping(value = "/levelDetailList", method = RequestMethod.GET)
	public SpZoneLevelDetailResponseDto getParkingZoneLevelDetail(
			@RequestParam(name = "zoneId") int zoneId,
			@RequestParam(name = "vehicleTypeId") int vehicleType,
			@RequestParam(name = "slotTypeId") int slotType,
			@RequestParam(name = "toTime") String toTime,
			@RequestParam(name = "fromTime") String fromTime,
			@RequestParam(name = "timeZone") String timeZone) {
		SpZoneLevelDetailResponseDto spZoneLevelDetailResponseDto = new SpZoneLevelDetailResponseDto();

		try {
			spZoneLevelDetailResponseDto = spsBookingService
					.getParkingZoneLevelDetails(zoneId, vehicleType, slotType,
							toTime, fromTime, timeZone);

			if (spZoneLevelDetailResponseDto == null) {
				spZoneLevelDetailResponseDto = new SpZoneLevelDetailResponseDto();
				spZoneLevelDetailResponseDto.setAppHeader(CommonUtility
						.getAppResponseHeader(IResponse.ERROR_CODE_FETCH_LEVEL,
								IResponse.ERROR_MESSAGE_FETCH_LEVEL_DETAIL));
			} else {

				spZoneLevelDetailResponseDto.setAppHeader(CommonUtility
						.getAppResponseHeader(IResponse.SUCCESS_CODE,
								IResponse.SUCCESS_MESSAGE_FETCH_LEVEL_DETAIL));
			}

			return spZoneLevelDetailResponseDto;

		} catch (GenericDBException e) {
			// TODO Auto-generated catch block
			spZoneLevelDetailResponseDto = new SpZoneLevelDetailResponseDto();
			spZoneLevelDetailResponseDto.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							e.getMessage()));
			return spZoneLevelDetailResponseDto;
		} catch (final Exception ex) {

			spZoneLevelDetailResponseDto = new SpZoneLevelDetailResponseDto();
			spZoneLevelDetailResponseDto.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							ex.getMessage()));
			return spZoneLevelDetailResponseDto;

		}

	}

	/**
	 * Share the Slot and Vehicle details
	 * 
	 * @return
	 */
	@ApiOperation(value = "", notes = "Slot and Vehicle Type Details", produces = "application/json")
	@RequestMapping(value = "/slotAndVehicleType", method = RequestMethod.GET)
	public AbstractBaseDTO getSlotAndVehicleTypeDetails() {
		SpSlotAndVehicleTypeResponseDto spSlotAndVehicleTypeResponseDto = new SpSlotAndVehicleTypeResponseDto();

		try {

			spSlotAndVehicleTypeResponseDto = spsBookingService
					.getSlotAndVehicleTypeDetails();

			spSlotAndVehicleTypeResponseDto.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.SUCCESS_CODE,
							IResponse.SUCCESS_MESSAGE_SLOT_VEHCILE_TYPE_MSG));

			return spSlotAndVehicleTypeResponseDto;

		} catch (GenericDBException e) {
			// TODO Auto-generated catch block
			spSlotAndVehicleTypeResponseDto = new SpSlotAndVehicleTypeResponseDto();
			spSlotAndVehicleTypeResponseDto.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							e.getMessage()));
			return spSlotAndVehicleTypeResponseDto;
		} catch (final Exception ex) {

			spSlotAndVehicleTypeResponseDto = new SpSlotAndVehicleTypeResponseDto();
			spSlotAndVehicleTypeResponseDto.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							ex.getMessage()));
			return spSlotAndVehicleTypeResponseDto;

		}

	}

	/**
	 * Blocks the parking for 10 minutes
	 * 
	 * @param requestBody
	 * @param result
	 * @return
	 */
	@ApiOperation(value = "", notes = "Block the parking.", produces = "application/json")
	@RequestMapping(value = "/block", method = RequestMethod.POST)
	public BookingResponseDTO bookParking(
			@Valid @RequestBody final BookingRequestDTO requestBody,
			BindingResult result)

	{
		BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

		if (result.hasErrors()) {
			APPErrorResponse appErrorResponse = populateError(result);

			bookingResponseDTO.setErrors(appErrorResponse);

			bookingResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(
					IResponse.ERROR_CODE_VALIDATION,
					IResponse.ERROR_MESSAGE_VALIDATION));

			return bookingResponseDTO;

		}
		
		try {
			bookingResponseDTO = spsBookingService.blockParking(requestBody);
			if(bookingResponseDTO == null) {
				bookingResponseDTO = new BookingResponseDTO();
			}
			
			if (bookingResponseDTO.getBookingId() > 0) {
				bookingResponseDTO.setCustomerId(requestBody.getCustomerId());
				bookingResponseDTO.setAppHeader(CommonUtility
						.getAppResponseHeader(IResponse.SUCCESS_CODE,
								IResponse.SUCCESS_MESSAGE_BLOCK_PARKING));
			} else {
				bookingResponseDTO.setAppHeader(CommonUtility
						.getAppResponseHeader(
								IResponse.ERROR_CODE_BLOCK_PARKING,
								IResponse.ERROR_MESSAGE_BLOCK_PARKING));
			}
			
			return bookingResponseDTO;
		}

		catch (GenericDBException e) {
			// TODO Auto-generated catch block
			bookingResponseDTO = new BookingResponseDTO();
			bookingResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(
					IResponse.ERROR_CODE_APPLICATION, e.getMessage()));
			return bookingResponseDTO;
		} catch (final Exception ex) {

			bookingResponseDTO = new BookingResponseDTO();
			bookingResponseDTO.setAppHeader(CommonUtility.getAppResponseHeader(
					IResponse.ERROR_CODE_APPLICATION, ex.getMessage()));
			return bookingResponseDTO;

		}

	}

	/**
	 * Calculates the parking cost
	 * 
	 * @param levelId
	 * @param vehicleType
	 * @param slotType
	 * @param toTime
	 * @param fromTime
	 * @return
	 */
	@ApiOperation(value = "", notes = "Fetch Parking Cost", produces = "application/json")
	@RequestMapping(value = "/cost", method = RequestMethod.GET)
	public BookingCostResponseDTO getBookingCost(
			@RequestParam(name = "levelId") int levelId,
			@RequestParam(name = "vehicleTypeId") int vehicleType,
			@RequestParam(name = "slotTypeId") int slotType,
			@RequestParam(name = "toTime") String toTime,
			@RequestParam(name = "fromTime") String fromTime) {
		BookingCostResponseDTO bookingCostResponseDTO = new BookingCostResponseDTO();

		try {
			bookingCostResponseDTO.setPrice(spsBookingService.getBookingCost(
					levelId, vehicleType, slotType, fromTime, toTime));

			bookingCostResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.SUCCESS_CODE,
							IResponse.SUCCESS_MESSAGE_BOOKING_COST));

			return bookingCostResponseDTO;

		} catch (GenericDBException e) {
			// TODO Auto-generated catch block
			bookingCostResponseDTO = new BookingCostResponseDTO();
			bookingCostResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							e.getMessage()));
			return bookingCostResponseDTO;
		} catch (final Exception ex) {

			bookingCostResponseDTO = new BookingCostResponseDTO();
			bookingCostResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							ex.getMessage()));
			return bookingCostResponseDTO;

		}

	}

	/**
	 * Accepts the payment
	 * 
	 * @param requestBody
	 * @param result
	 * @return
	 */
	@ApiOperation(value = "", notes = "payment", produces = "application/json")
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public BookingPaymentResponseDTO bookingPayment(
			@Valid @RequestBody final BookingPaymentRequestDTO requestBody,
			BindingResult result)

	{
		BookingPaymentResponseDTO bookingPaymentResponseDTO = new BookingPaymentResponseDTO();

		if (result.hasErrors()) {
			APPErrorResponse appErrorResponse = populateError(result);

			bookingPaymentResponseDTO.setErrors(appErrorResponse);

			bookingPaymentResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_VALIDATION,
							IResponse.ERROR_MESSAGE_VALIDATION));

			return bookingPaymentResponseDTO;

		}
		bookingPaymentResponseDTO = new BookingPaymentResponseDTO();

		try {

			spsBookingService.recievePayment(requestBody.getBookingId(),
					requestBody.getTransactionId(), requestBody.getCost(),
					requestBody.isPaymentStatus(),
					requestBody.isExtendedStatus());
			bookingPaymentResponseDTO
					.setCustomerId(requestBody.getCustomerId());

			// bookingPaymentResponseDTO.s(requestBody.getBookingId());
			// bookingResponseDTO.setBookingId(bookingId);
			bookingPaymentResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.SUCCESS_CODE,
							IResponse.SUCCESS_MESSAGE_PARKING_PAYMENT));

			return bookingPaymentResponseDTO;
		}

		catch (GenericDBException e) {
			// TODO Auto-generated catch block
			bookingPaymentResponseDTO = new BookingPaymentResponseDTO();
			bookingPaymentResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							e.getMessage()));
			return bookingPaymentResponseDTO;
		} catch (final Exception ex) {

			bookingPaymentResponseDTO = new BookingPaymentResponseDTO();
			bookingPaymentResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							ex.getMessage()));
			return bookingPaymentResponseDTO;

		}

	}

	/**
	 * Verifies the extended booking
	 * 
	 * @param requestBody
	 * @param result
	 * @return
	 */
	@ApiOperation(value = "", notes = "verifyBooking", produces = "application/json")
	@RequestMapping(value = "/extended/verify", method = RequestMethod.POST)
	public BookingExtendedCostResponseDTO verifyPayment(
			@Valid @RequestBody final BookingExtendedCostRequestDTO requestBody,
			BindingResult result)

	{
		BookingExtendedCostResponseDTO bookingextendedCostResponseDTO = null;

		try {
			bookingextendedCostResponseDTO = spsBookingService
					.verifyExtendedBooking(requestBody);

			if (bookingextendedCostResponseDTO == null) {
				bookingextendedCostResponseDTO = new BookingExtendedCostResponseDTO();
				bookingextendedCostResponseDTO
						.setAppHeader(CommonUtility
								.getAppResponseHeader(
										IResponse.ERROR_CODE_APPLICATION,
										IResponse.ERROR_MESSAGE_VERIFY_EXTENDED_BOOKING));

			} else {

				bookingextendedCostResponseDTO
						.setAppHeader(CommonUtility
								.getAppResponseHeader(
										IResponse.SUCCESS_CODE,
										IResponse.SUCCESS_MESSAGE_VERIFY_EXTENDED_BOOKING));
			}

			return bookingextendedCostResponseDTO;

		} catch (GenericDBException e) {
			// TODO Auto-generated catch block
			bookingextendedCostResponseDTO = new BookingExtendedCostResponseDTO();
			bookingextendedCostResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							e.getMessage()));
			return bookingextendedCostResponseDTO;
		} catch (final Exception ex) {

			bookingextendedCostResponseDTO = new BookingExtendedCostResponseDTO();
			bookingextendedCostResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							ex.getMessage()));
			return bookingextendedCostResponseDTO;

		}

	}

	/**
	 * Fetches the booking history of the particular customer
	 * 
	 * @param customerId
	 * @return
	 */
	@ApiOperation(value = "", notes = "Booking History", produces = "application/json")
	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public BookingHistoryDetailsResponseDTO history(@RequestParam int customerId)

	{
		BookingHistoryDetailsResponseDTO bookingHistoryDetailsResponseDTO = null;
		try {

			bookingHistoryDetailsResponseDTO = spsBookingService
					.bookingHistory(customerId);

			if (bookingHistoryDetailsResponseDTO == null) {
				bookingHistoryDetailsResponseDTO = new BookingHistoryDetailsResponseDTO();

				bookingHistoryDetailsResponseDTO.setAppHeader(CommonUtility
						.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
								IResponse.ERROR_MESSAGE_BOOKING_HISTORY));

			} else {

				bookingHistoryDetailsResponseDTO.setAppHeader(CommonUtility
						.getAppResponseHeader(IResponse.SUCCESS_CODE,
								IResponse.SUCCESS_MESSAGE_BOOKING_HISTORY));

			}

			return bookingHistoryDetailsResponseDTO;
		} catch (Exception ex) {

			bookingHistoryDetailsResponseDTO = new BookingHistoryDetailsResponseDTO();
			bookingHistoryDetailsResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							"SUCCESS"));

		}

		return bookingHistoryDetailsResponseDTO;

	}

	@ApiOperation(value = "", notes = "Cancel Future Booking", produces = "application/json")
	@RequestMapping(value = "/cancel/{bookingId}/{customerId}", method = RequestMethod.DELETE)
	public BookingCancelResponseDTO cancelBooking(
			@PathVariable("bookingId") int bookingId,
			@PathVariable("customerId") int customerId)

	{

		try {
			return spsBookingService.cancelFutureBookedParking(bookingId,
					customerId);
		} catch (GenericDBException e) {
			BookingCancelResponseDTO bookingCancelResponseDTO = new BookingCancelResponseDTO();
			bookingCancelResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							IResponse.ERROR_MESSAGE_BOOKING_CANCEL_SYSTEM + " "
									+ e.getMessage()));

			return bookingCancelResponseDTO;
		} catch (Exception ex) {
			BookingCancelResponseDTO bookingCancelResponseDTO = new BookingCancelResponseDTO();
			bookingCancelResponseDTO.setAppHeader(CommonUtility
					.getAppResponseHeader(IResponse.ERROR_CODE_APPLICATION,
							IResponse.ERROR_MESSAGE_BOOKING_CANCEL_SYSTEM + " "
									+ ex.getMessage()));

			return bookingCancelResponseDTO;
		}

	}

}
