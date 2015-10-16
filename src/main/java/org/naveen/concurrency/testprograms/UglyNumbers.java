package org.naveen.concurrency.testprograms;

public class UglyNumbers {
	
	private static int min(int n1, int n2, int n3) {
		return n1 < n2 ? (n1 < n3 ? n1 : n3) : (n2 < n3 ? n2 : n3);
	}
	
	public static void main(String[] args) {
		System.out.println(min(2,2,3));
	}
}
