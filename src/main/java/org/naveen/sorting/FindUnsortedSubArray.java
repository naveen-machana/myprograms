package org.naveen.sorting;

public class FindUnsortedSubArray {
	
	public static void find(int[] a) {
		int n = a.length, s = 0, e = n;
		
		for (int i = 0; i < n-1; i++) {
			if (a[i] > a[i+1]){
				s = i;
				break;
			}
		}
		
		for (int i = n-1; i > 0; i--) {
			if (a[i] < a[i-1]) {
				e = i;
				break;
			}
		}
		
		int min = a[s], max = a[s];
		for (int i = s; i <= e; i++) {
			if (a[i] < min) min = a[i];
			else if (a[i] > max) max = a[i];
		}
		
		for (int i = 0; i < s; i++) {
			if (a[i] > min) {
				s = i; break; 
			}
		}
		
		for (int i = n-1; i > e; i--){
			if (a[i] < max) {
				e = i; break;
			}
		}
		
		for (int i = s; i <= e; i++) {
			System.out.println(a[i]);
		}
	}

	public FindUnsortedSubArray() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		  int arr[] = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
		  find(arr);
	}

}
