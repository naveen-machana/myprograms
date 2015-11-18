package org.naveen.techgig.pokerbazi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GetMaximumSumCombinations {
	
	private static List<Object> getMaxCombinations(int[] data, int r) {
		List<Object> combinations = new LinkedList<>();
		
		if (data == null || data.length == 0 || r == 0) return combinations;
		
		int[] temp = new int[r];
		int[] max = new int[]{0};
		maxSumCombinationsUtil(data, temp, data.length, r, max, combinations, 0, 0);
		return combinations;
	}
	
	private static void maxSumCombinationsUtil(int[] data, int[] temp,
			int n, int r, int[] max, List<Object> combinations, int index, int i) {
		
		if (index == r) {
			int currentSum = 0;			
			for (int j = 0; j < r; j++) currentSum += temp[j];
			
			if (currentSum > max[0]) {
				combinations.clear();
				max[0] = currentSum;
				combinations.add(temp.clone());
			}
			else if (currentSum == max[0]) {
				combinations.add(temp.clone());
			}
			return;
		}
		
		if (i >= n) return;
		
		temp[index] = data[i];
		
		maxSumCombinationsUtil(data, temp, n, r, max, combinations, index+1, i+1);
		maxSumCombinationsUtil(data, temp, n, r, max, combinations, index, i+1);		
	}

	public static void main(String[] args) {
		List<Object> combinations = getMaxCombinations(new int[]{2, 5, 1, 2, 4, 1, 6, 5, 2, 2}, 6);
		for (Object array: combinations) {
			int[] a = (int[])array;
			System.out.println(Arrays.toString(a));
		}
	}

}
