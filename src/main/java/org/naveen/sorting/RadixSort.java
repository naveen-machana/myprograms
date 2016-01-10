package org.naveen.sorting;

import java.util.Arrays;

public class RadixSort {
	
	private static int max(int []a) {
		int mx = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > mx) {
				mx = a[i];
			}
		}
		return mx;
	}
	
	public static void sort(int[] a) {
		int max = max(a);
		
		for (int exp = 1; max/exp > 0; exp *= 10) {
			countSort(a, a.length, exp);
		}
	}
	
	private static void countSort(int[] a, int n, int exp) {
		int[] output = new int[n];
		int[] count = new int[10];
		
		for (int i = 0; i < n; i++) {
			count[(a[i]/exp) % 10]++;
		}
		
		for (int i = 1; i < 10; i++) count[i] += count[i-1];
		
		for (int i = n-1; i >= 0; i--) {
		//for (int i = 0; i < n; i++) {
			output[count[(a[i] / exp) % 10] - 1] = a[i];
			count[(a[i] / exp) % 10]--;
		}
		
		for (int i = 0; i < n; i++) {
			a[i] = output[i];
		}
	}
	
	
	public RadixSort() {
	}

	public static void main(String[] args) {
		int[] a = {182, 186, 2, 33, 18, 0};
		sort(a);
		System.out.println(Arrays.toString(a));
	}

}
