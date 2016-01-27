package org.naveen.interestingprograms;

import java.util.Arrays;

public class AlternateSwapOfPositiveNegativeNumbers {
	
	private static void partition(int[] a) {
		int i = -1, j;
		for (j = 0; j < a.length; j++) {
			if (a[j] < 0) {
				i++;
				swap(a, i, j);
			}
		}
	}
	
	private static void swap(int[] a, int i, int j) {
		int k = a[i];
		a[i] = a[j];
		a[j] = k;
	}
	
	static void alternateSwap(int[] a) {
		partition(a);
		int pos = 0, neg = 0, k = 0, n = a.length;
		while (pos < n && a[pos] < 0) pos++;
		if (pos == 0 || pos == n) return;
		k = pos;
		while (pos < n && neg < pos && a[neg] < 0) {
			swap(a, pos, neg);
			pos ++; neg += 2;
		}		
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, -3, -4, -5, -6, 7, 8, 9};
		System.out.println(Arrays.toString(a));
		alternateSwap(a);
		System.out.println(Arrays.toString(a));
	}

}
