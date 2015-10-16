package org.naveen.concurrency.testprograms;

public class DoubleToIntParse {

	public static void main(String[] args) {
		String s = "123.12";
		int value = (int)Double.parseDouble(s);
		System.out.println(value);
	}

}
