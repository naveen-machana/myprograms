package org.naveen.interestingprograms;

import java.util.Arrays;

public class LongestPalindromeSubString {

	public static void main(String[] args) {
		char[] input = "abcacba".toCharArray();
		int n =input.length;
		int[][] result = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = 0;
			}
		}
		
		for (int i = 0; i < n; i++) {
			result[i][i] = 1;
		}
		
		for (int i = 0; i < n-1; i++) {
			if (input[i] == input[i+1]) {
				result[i][i+1] = 2;
			}
		}
		
		for (int j = 2; j < n; j++) {
			for (int i = 0; i < n-j; i++) {
				if (input[i] == input[i+j] && result[i+1][i+j-1] != 0) {
					result[i][i+j] = 2 + result[i+1][i+j-1];
				}
				else {
					result[i][i+j] = 0;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(result[i]));
		}
	}

}
