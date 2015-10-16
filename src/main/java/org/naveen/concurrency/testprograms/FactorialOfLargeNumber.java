package org.naveen.concurrency.testprograms;

import java.util.ArrayList;
import java.util.List;

public class FactorialOfLargeNumber {
	
	private static List<Integer> factorial(int n) {
		List<Integer> result = new ArrayList<Integer>();
		result.add(1);
		for (int i = 2; i <= n; i++) {
			multiply(i, result);
		}
		reverse(result);
		return result;
	}
	
	private static void multiply(int x, List<Integer> result) {
		int carry = 0;
		for (int i = 0; i < result.size(); i++) {
			int y = result.get(i);
			int prod = carry + x * y;
			carry = prod/10;
			prod = prod % 10;
			result.set(i, prod);
		}
		
		while (carry != 0) {
			int y = carry % 10;
			carry = carry / 10;
			result.add(y);			
		}
	}
	
	private static void reverse(List<Integer> list) {
		for (int i = 0, j = list.size() - 1; i < j ; i++, j--) {
			Integer k = list.get(i);
			list.set(i, list.get(j));
			list.set(j, k);
		}
	}
	
	public static void main(String[] args) {
		List<Integer> factorial = factorial(150);
		System.out.println("Number of digits in factorial: " + factorial.size());
		System.out.println(factorial);				
	}

}
