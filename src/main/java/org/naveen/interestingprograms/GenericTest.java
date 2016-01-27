package org.naveen.interestingprograms;

import java.util.Arrays;
import java.util.List;

public class GenericTest {
	public static void copy(List<? super Integer> x) {
	}
	public static void main(String[] args) {
		List<Number> nums = Arrays.asList(1, 3, 4);
		List<Comparable<Integer>> comparables = Arrays.asList(1, 2, 3);
		copy(nums);
		copy(comparables);
	}
}
