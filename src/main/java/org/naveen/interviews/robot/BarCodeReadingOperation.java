package org.naveen.interviews.robot;

public class BarCodeReadingOperation implements Operation {
	private final BarCodeReaderAPI api; 
	private final Object barcodeStream;
	private final Operation op;
	
	public BarCodeReadingOperation(Object barcodeStream, Operation op) {
		super();
		this.api = new BarCodeReaderAPI();
		this.barcodeStream = barcodeStream;
		this.op = op;
	}	

	@Override
	public double requiredPower() {
		return 0;
	}

	@Override
	public boolean performOperation(Object obj) throws Exception{
		try{
			double price = api.readBarCode(barcodeStream);
			System.out.println("Barcode reading operation");
			System.out.println("Value of barcode: " + price);
			return op.performOperation(obj);
			
		} catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean validate() {
		return true;
	}
	
}
