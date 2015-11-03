package org.naveen.interviews.robot;

import java.util.Random;

//Assuming the following API is available for reading the bar code
//Here assuming the price is in the range of 0-10000
public class BarCodeReaderAPI {
	public double readBarCode(Object obj) {
		Random validator = new Random();
		return Math.random() * 10000;
		/*if (validator.nextBoolean()) {
			return Math.random() * 10000;
		}

		throw new IllegalArgumentException("Invalid Barcode");*/
	}
}
