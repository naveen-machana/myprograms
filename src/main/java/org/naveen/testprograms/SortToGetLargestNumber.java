package org.naveen.testprograms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortToGetLargestNumber {

	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(94);
		numbers.add(546);
		numbers.add(548);
		numbers.add(60);
		System.out.println(numbers);
		
		Collections.sort(numbers, (x, y) -> {
				String one = x + "";
				String two = y + "";
				String onetwo = one.concat(two);
				String twoone = two.concat(one);
				return twoone.compareTo(onetwo);
			}
		);
		System.out.println(numbers);
		
	}

}
