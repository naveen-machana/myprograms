package org.naveen.testprograms;

public class FindDuplicateCharacter {

	public static void main(String[] args) {
		int n = Character.MAX_VALUE/64 + 1;
		long[] bits = new long[n];
		
		char[] string = "abcda".toCharArray();
		
		for (int i = 0; i < string.length; i++) {
			
			int k = string[i]/64;
			int r = string[i] % 64;			
			
			if ((bits[k] & (1 << r))!= 0) {
				System.out.println("Duplicate: " + string[i]);
				break;
			}
			
			bits[k] = bits[k] | (1 << r);
		}
	}

}
