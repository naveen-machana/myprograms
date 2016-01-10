package org.naveen.sorting;

public class QuickSort {
	
	public static void sort(int[] a) {
		sort(a, 0, a.length-1);
	}
	
	private static void sort(int[] a, int l, int h) {
		if (l < h) {
			int p = partition(a, l, h);
			sort(a, l, p-1);
			sort(a, p+1, h);
		}
	}
	
	private static int partition(int[] a, int l, int h) {
		int pi = a[h], st = l - 1;
		
		for (int i = l; i < h; i++) {
			if (a[i] <= pi) {
				st++;
				swap(a, st, i);
			}
		}		
		st++;
		swap(a, st, h);
		return st;
	}
	
	private static void swap(int[] a, int i, int j) {
		int k = a[i];
		a[i] = a[j];
		a[j] = k;
	}
	
	private void iterativeSort(int[] a, int l, int h) {
		int[] stack = new int[h-l+1];
		int top = -1;
		
		stack[++top] = l;
		stack[++top] = h;
		
		while (stack.length > 0) {
			h = stack[top--];
			l = stack[top--];
			
			int pi = partition(a, l, h);
			if (pi - 1 > l) {
				stack[++top] = l;
				stack[++top] = pi-1;
			}
			
			if (pi + 1 < h) {
				stack[++top] = pi+1;
				stack[++top] = h;
			}
		}
	}
	
	public QuickSort() {
		// TODO Auto-generated constructor stub
	}

}
