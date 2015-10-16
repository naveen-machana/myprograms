package org.naveen.concurrency.testprograms;

public class PermutationsOfAString {
	private static void swap(char[] data, int l, int r){
		char t = data[l];
		data[l] = data[r];
		data[r] = t;
	}
	
	public static void permute(char[] data, int l, int r) {
		if (l == r) {
			System.out.println(data);			
		}
		else {
			for (int i = l; i <= r; i++) {
				swap(data, i, l);
				permute(data, l+1, r);
				swap(data, i, l);				
			}
		}
	}
	
	public static void main(String[] args) {
		char[] data = "abc".toCharArray();
		permute(data, 0, data.length - 1);
	}
}
