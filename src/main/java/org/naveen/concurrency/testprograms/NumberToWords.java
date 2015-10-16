package org.naveen.concurrency.testprograms;

import java.util.HashMap;
import java.util.Map;

public class NumberToWords {
	
	private static Map<Integer, String> map = new HashMap<>();
	static{
		map.put(0, "");
		map.put(1, "one");
		map.put(2, "two");
		map.put(3, "three");
		map.put(4, "four");
		map.put(5, "five");
		map.put(6, "six");
		map.put(7, "seven");
		map.put(8, "eight");
		map.put(9, "nine");
		map.put(10, "ten");
		map.put(11, "eleven");
		map.put(12, "twelve");
		map.put(13, "thirteen");
		map.put(14, "fourteen");
		map.put(15, "fifteen");
		map.put(16, "sixteen");
		map.put(17, "seventeen");
		map.put(18, "eighteen");
		map.put(19, "nineteen");
		map.put(20, "twenty");
		map.put(30, "thirty");
		map.put(40, "fourty");
		map.put(50, "fifty");
		map.put(60, "sixty");
		map.put(70, "seventy");
		map.put(80, "eighty");
		map.put(90, "ninety");		
	}
	
	private static void print(int n) {
		System.out.println();
		if (n/100 != 0) {
			System.out.print(map.get(n/100) + " hundred ");	
		}
		if(n > 99 && n % 100 != 0) {
			System.out.print(" and ");
		}
		n = n % 100;
		if (n >= 20) {
			System.out.print(map.get(n/10*10) + " ");
			n = n % 10;
		}	
		System.out.print(map.get(n));
	}
	
	public static void main(String[] args) {
		for (int i = 1; i < 1000; i++) {
			print (i);
		}
	}

}
