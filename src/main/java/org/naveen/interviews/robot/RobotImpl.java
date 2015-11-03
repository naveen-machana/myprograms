package org.naveen.interviews.robot;

public class RobotImpl implements Robot{
	private double battery = 100.0;
	private final double maxWalkingCapacity;
	private final double maxWeightCarryingCapacity;
	private final double batteryConsumptionPerKG;
	
	public RobotImpl(double maxWalkingCapacity, double maxWeightCarryingCapacity, double batteryConsumptionPerKG) {
		super();
		this.maxWalkingCapacity = maxWalkingCapacity;
		this.maxWeightCarryingCapacity = maxWeightCarryingCapacity;
		this.batteryConsumptionPerKG = batteryConsumptionPerKG;
	}
	
	public double robotMaxWalkCapacityInKM() {
		return maxWalkingCapacity;
	}

	public double remainingBattery() {
		return this.battery;
	}

	@Override
	public boolean performOperation(Operation operation) throws Exception {
		return operation.performOperation(null);
	}

	@Override
	public void consumePower(Double amount) throws InsufficientBatteryException {
		if (amount > this.battery) {
			throw new InsufficientBatteryException("Insufficient battery", amount, this.battery);
		}
		this.battery -= amount;
	}

	public double batteryPerKG() {
		return batteryConsumptionPerKG;
	}

	public double robotMaxWeightCarryingCapacityInKG() {
		return maxWeightCarryingCapacity;
	}
	
	public void readBarCode(Object object){
		BarCodeReadingOperation op = new BarCodeReadingOperation(new Object(), new PowerConsumptionOperation(this));
		try {
			this.performOperation(op);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean walk(double distance) {
		WalkOperation walkOp = new WalkOperation(new PowerConsumptionOperation(this), this, distance);
		try {
			return this.performOperation(walkOp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;		
	}
	
	public boolean walkWithWeight(double distance, double weight) {
		PowerConsumptionOperation powerOp = new PowerConsumptionOperation(this);
		WalkOperation walkOp = new WalkOperation(powerOp, this, distance);
		WeightOperation weightOp = new WeightOperation(walkOp, this, weight);
		
		try {
			return this.performOperation(weightOp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean walkWeighAndReadBarcode(double distance, double weight, Object barcodeStream) {
		PowerConsumptionOperation powerOp = new PowerConsumptionOperation(this);
		WalkOperation walkOp = new WalkOperation(powerOp, this, distance);
		WeightOperation weightOp = new WeightOperation(walkOp, this, weight);
		BarCodeReadingOperation barcodeOp = new BarCodeReadingOperation(barcodeStream, weightOp);
		
		try {
			return this.performOperation(barcodeOp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
