package org.naveen.interviews.robot;

public class PowerConsumptionOperation implements Operation {
	private final Robot robot;
	
	public PowerConsumptionOperation(Robot robot) {
		this.robot = robot;
	}
	
	@Override
	public boolean performOperation(Object obj) throws Exception {		
		
		if (robot.remainingBattery() < 15) {
			System.out.println("Battery is below 15 percent");
		}
		
		if (obj == null) return true;
		
		Double neededPower = (Double)obj;
		
		if (neededPower < 0.0 || neededPower > 100.0) {
			throw new IllegalArgumentException("Power to be consumed should be a positive value and should be <= 100");
		}
		
		if (neededPower == 0.0) return true;
		
		if (robot.remainingBattery() < neededPower) {
			throw new InsufficientBatteryException("Insufficient Power", neededPower, robot.remainingBattery());
		}
		
		System.out.println("Power consuming: " + neededPower);
		robot.consumePower(neededPower);
		return true;		
	}

	@Override
	public double requiredPower() {
		return 0.0;
	}

	@Override
	public boolean validate() throws Exception {
		return true;
	}
	
}
