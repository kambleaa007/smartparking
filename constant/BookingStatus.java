package com.techmahindra.smartparking.constant;

public interface BookingStatus {

	public static int BOOKED = 1;
	public static int BLOCKED = 2;
	public static int CANCELLED = 3;
	public static int OCCUPIED = 4;
	public static int CONSUMED = 5;
	public static int UNUSED = 6;

	public static String findStatus(int value) {
		switch (value) {
		case 1:
			return "BOOKED";
		case 2:
			return "BLOCKED";
		case 3:
			return "CANCELLED";
		case 4:
			return "OCCUPIED";
		case 5:
			return "CONSUMED";
		case 6:
			return "UNUSED";
		default:
			return "NA";
		}

	}
}
