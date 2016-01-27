package org.naveen.interestingprograms;


public class Substrings {
	
	static void printStrings(char[] string, int start, int end) {
		if (start > end) return;
		System.out.println();
		print(string, start, end);
		for (int i = start; i <= end; i++) {
			printStrings(string, i, end - 1);
		}
	}
	
	static void print(char[] string, int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.print(string[i]);
		}
	}

	public static void main(String[] args) {
		char[] string = "navenkumar".toCharArray(); 
		printStrings(string, 0, string.length -1 );
	}

}
