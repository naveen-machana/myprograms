package org.naveen.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {
	// assumption is that the numbers are in the range of 0.0 to 1.0
	public static void sort(float[] a) {
		int n = a.length;
		LinkedList[] lists = new LinkedList[n];
		
		for (int i = 0; i < n; i++) {
			int bi = (int)(n * a[i]);
			if (lists[bi] == null) {
				lists[bi] = new LinkedList();				
			}
			lists[bi].add(Float.valueOf(a[i]));
		}
		
		for (int i = 0; i < n; i++) {
			if (lists[i] != null) {
				Collections.sort(lists[i]);
			}
		}
		
		int k = 0;
		for (int i = 0; i < n; i++){
			if (lists[i] != null) {
				for (Object o : lists[i]) {
					a[k++] = (Float)o;
				}
			}
		}
		
	}

	public BucketSort() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		float arr[] = {0.897f, 0.565f, 0.656f, 0.1234f, 0.665f, 0.3434f};
		System.out.println(Arrays.toString(arr));
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
