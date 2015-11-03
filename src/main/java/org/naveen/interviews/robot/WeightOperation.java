package org.naveen.interviews.robot;


public class WeightOperation implements Operation {
	
	private final double BAT_CONSUMPTION_PER_GRAM;
	private final RobotImpl robot;
	private final double weight;
	private final Operation op;
	private static final Double ZERO = 0.0;
	
	public WeightOperation(Operation op, RobotImpl robot, double weight) {
		this.op = op;
		this.robot = robot;
		this.weight = weight;
		BAT_CONSUMPTION_PER_GRAM = (robot.batteryPerKG()/1000.0);
	}
	
	@Override
	public boolean performOperation(Object obj) throws Exception {
		if (obj == null) obj = ZERO;
		Double totalPowerNeeded = ((Double)obj) + requiredPower();
		if (validate()) {
			System.out.println("Weigh Operation in progress");
			return op.performOperation(totalPowerNeeded);
		}
		return false;
	}

	@Override
	public double requiredPower() {
		double weightInG = ((Double)weight * 1000);
		double powerConsumed = weightInG * BAT_CONSUMPTION_PER_GRAM;		
		return powerConsumed;
	}

	@Override
	public boolean validate() throws Exception {
		if (weight > robot.robotMaxWeightCarryingCapacityInKG()) {
			throw new IllegalArgumentException("Maximum weight this robot can carry is " + robot.robotMaxWeightCarryingCapacityInKG());
		}
		
		if (weight < 0) {
			throw new IllegalArgumentException("Weight to be carried is negative value");
		}
		
		double requiredPower = requiredPower();
		if (robot.remainingBattery() < requiredPower) {
			throw new InsufficientBatteryException("Insufficient Battery", requiredPower, robot.remainingBattery());
		} 
		
		return true;
	}

}
