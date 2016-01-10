package org.naveen.sorting;

import java.util.Arrays;

public class HeapSort {
	
	// min heap
	private static class Heap {
		private int size;
		private int[] a;
		
		public Heap(int[] a, int size) {
			this.size = size;
			this.a = a;
			
			int i = (this.size - 1)/2;
			while (i >= 0) {
				heapify(i);
				i--;
			}
		}
		
		private int left(int i) {
			return (2 * i + 1);
		}
		
		private int right(int i) {
			return (2 * i + 2);
		}
		
		private void heapify(int i) {
			if (i >= size) return;
			int smallest = i;
			int l = left(i);
			int r = right(i);
			
			if (l < size && a[l] < a[smallest]) smallest = l;
			if (r < size && a[r] < a[smallest]) smallest = r;
			
			if (smallest != i) {
				swap(smallest, i);
				heapify(smallest);
			}
		}
		
		private void swap(int i, int j) {
			int k = a[i];
			a[i] = a[j];
			a[j] = k;
		}
		
		public int extractMin() {
			int min = a[0];
			int temp = a[size - 1];
			size--;
			if (size > 0) {
				a[0] = temp;
				heapify(0);
			}
			return min;
		}
		
		public int replaceMin(int x) {
			int min = a[0];
			if (min < x) {
				a[0] = x;
				heapify(0);				
			}
			return min;
		}
		
		public static void sort(int[] a) {
			Heap h = new Heap(a, a.length);
			
			while (h.size > 1) {
				h.swap(0, h.size - 1);
				h.size--;
				h.heapify(0);				
			}
			h.size = h.a.length;
			h.reverse();
		}
		
		private void reverse() {
			for (int i = 0, j = size-1; i < j; i++, j--){
				swap(i, j);				
			}
		}
		
		
	}

	public static void sort(int[] a) {
		Heap.sort(a);
	}
	
	public HeapSort() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		int[] a = {5, 3, 6, 1, 8, 2, 4, 8};
		sort(a);
		System.out.println(Arrays.toString(a));
	}

}
