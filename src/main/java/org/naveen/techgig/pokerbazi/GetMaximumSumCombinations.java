package org.naveen.techgig.pokerbazi;

public class GetMaximumSumCombinations {
	
	private static int getMaxSumCombinations(int[] data, int r) {
		int n = 0;
		if (data == null || (n = data.length) == 0 || r == 0) return 0;
		
		int[] temp = new int[r];
		int[] max = {Integer.MIN_VALUE};
		int[] combinationsCount = {0};
		countCombinations(data, temp, n, r, max, combinationsCount, 0, 0);
		return combinationsCount[0];
	}
	
	private static void countCombinations(int[] data, int[] temp,
			int n, int r, int[] maxValue, int[] combinationsCount, 
			int tempArrayIndex, int dataArrayIndex) {
		
		if (tempArrayIndex == r) {
			int sum = 0;			
			for (int j = 0; j < r; j++) sum += temp[j];
			
			if (sum > maxValue[0]) {
				combinationsCount[0] = 1;
				maxValue[0] = sum;
			}
			else if (sum == maxValue[0]) {
				combinationsCount[0]++;
			}
			return;
		}
		
		if (dataArrayIndex >= n) return;
		
		temp[tempArrayIndex] = data[dataArrayIndex];
		
		countCombinations(data, temp, n, r, maxValue, combinationsCount, tempArrayIndex+1, dataArrayIndex+1);
		countCombinations(data, temp, n, r, maxValue, combinationsCount, tempArrayIndex, dataArrayIndex+1);		
	}

	public static void main(String[] args) {
		int c = getMaxSumCombinations(new int[]{2, 5, 1, 2, 4, 1, 6, 5, 2, 2}, 6);
		System.out.println(c);
	}
}
