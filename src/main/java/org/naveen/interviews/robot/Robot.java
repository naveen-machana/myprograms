package org.naveen.interviews.robot;

public interface Robot {
	double remainingBattery();
	boolean performOperation(Operation operation) throws Exception;
	void consumePower(Double amount) throws InsufficientBatteryException;
}
