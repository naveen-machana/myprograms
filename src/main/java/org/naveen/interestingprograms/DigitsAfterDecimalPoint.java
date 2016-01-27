package org.naveen.interestingprograms;

import java.util.HashSet;

public class DigitsAfterDecimalPoint {

	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<Integer>(); 
		int reminder = 0, result = 10;
		int k = 10;
		int n = 8;
		
		for (int i = 1; i <= k; i++) {
			reminder = result % n;
			result = result/n;
			if (set.contains(reminder)) {
				break;
			}
			else {
				set.add(reminder);
			}
			System.out.print(result);
			result = reminder * 10;
		}
	}

}
