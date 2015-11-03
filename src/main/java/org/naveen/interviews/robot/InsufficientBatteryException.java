package org.naveen.interviews.robot;

public class InsufficientBatteryException extends Exception{
	private static final long serialVersionUID = 1L;
	private final String message;
	private final double batteryNeeded, batteryRemaining;
	
	public InsufficientBatteryException(String message, double batteryNeeded, double batteryRemaining) {
		super(message);
		this.message = message;
		this.batteryNeeded = batteryNeeded;
		this.batteryRemaining = batteryRemaining;
	}

	public String getMessage() {
		return message;
	}

	public double getBatteryNeeded() {
		return batteryNeeded;
	}

	public double getBatteryRemaining() {
		return batteryRemaining;
	}
}
