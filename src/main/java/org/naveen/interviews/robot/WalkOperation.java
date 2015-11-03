package org.naveen.interviews.robot;

public class WalkOperation implements Operation {
	private final double BAT_CONSUMPTION_PER_METER;
	private final RobotImpl robot;
	private final double distanceToBeWalked;
	private final Operation op;
	private static final Double ZERO = 0.0;
	
	public WalkOperation(Operation op, RobotImpl robot, double distance) {
		this.op = op;
		this.robot = robot;
		this.distanceToBeWalked = distance;
		BAT_CONSUMPTION_PER_METER = (100.0/inMeters(robot.robotMaxWalkCapacityInKM()));
	}

	@Override
	public boolean performOperation(Object obj) throws Exception {
		
		if (obj == null) obj = ZERO;
		
		Double totalPowerNeeded = ((Double)obj) + requiredPower();
		if (validate()) {
			System.out.println("Walk Operation in progress");
			return op.performOperation(totalPowerNeeded);			
		}
		return false;		
	}

	@Override
	public double requiredPower() {
		double distanceInM = inMeters(distanceToBeWalked);
		double powerConsumed = distanceInM * BAT_CONSUMPTION_PER_METER;		
		return powerConsumed;
	}

	@Override
	public boolean validate() throws Exception {
		if (distanceToBeWalked > robot.robotMaxWalkCapacityInKM()) {
			throw new IllegalArgumentException("Distance to be walked is more than " + robot.robotMaxWalkCapacityInKM());
		}
		
		if (distanceToBeWalked < 0) {
			throw new IllegalArgumentException("Distance to be walked is negative value");
		}
		
		double requiredPower = requiredPower();
		if (robot.remainingBattery() < requiredPower) {
			throw new InsufficientBatteryException("Insufficient Battery", requiredPower, robot.remainingBattery());
		} 
		
		return true;
	}
	
	private double inMeters(double valueInKM) {
		return valueInKM * 1000;
	}


}
