package org.naveen.interestingprograms;

import java.util.ArrayList;

public class CombinationsOf123ForGivenSum {
	
	static void printCombinations(int sum) {
		ArrayList<Integer> array = new ArrayList<>();
		printCombinations(sum, array, 0);
	}
	
	private static void printCombinations(int sum, ArrayList<Integer> array, int position) {
		if (sum < 0) return;
		
		if (sum == 0) {
			print(array, position);
			return;
		}
		else {
			for (int i = 1; i <= 3; i++){
				int subsum = sum - i;
				array.add(position, i);
				printCombinations(subsum, array, position + 1);
				array.remove(position);
			}
		}
	}
	
	private static void print(ArrayList<Integer> array, int position) {		
		for (int i = 0; i < position; i++) {
			System.out.print(array.get(i));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		printCombinations(5);
	}

}
