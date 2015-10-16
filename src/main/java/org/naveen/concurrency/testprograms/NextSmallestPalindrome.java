package org.naveen.concurrency.testprograms;

import java.util.Arrays;

public class NextSmallestPalindrome {
	
	static int[] nextSmallestPalindrome(int[] number) {
		// if number consists of all 9s
		boolean all9s = true;
		for (int i = 0; i < number.length; i++) {
			if (number[i] != 9) {
				all9s = false;
				break;
			}
		}

		if (all9s) {
			number = new int[number.length + 1];
			number[0] = number[number.length - 1] = 1;
			for (int i = 1; i < number.length - 1; i++) {
				number[i] = 0;
			}
			return number;
		}
		
		return nextSmallestPalindromeUtil(number);
	}
	
	static int[] nextSmallestPalindromeUtil(int[] number) {				
		
		int n = number.length;
		
		// start from mid and walk towards ends
		int mid = number.length/2;
		
		// left pointer 
		int l = mid - 1;
		
		// right pointer
		int r = (n % 2) == 0 ? mid : mid + 1;
		
		// flag for checking if mirror of left side is enough or not
		boolean leftSufficient = true;			
		
		// skip all matching digits around middle
		while (l >= 0 && number[l] == number[r]) {
			l--; r++;
		}
		
		// if already a palindrome or 
		// left pointer digit is lesser than right pointer digit
		// then just the mirror left side is not enough
		if (l < 0 || number[l] < number[r])
			leftSufficient = false;
		
		// if mirror(left) is sufficient then 
		// replace right with mirror of left
		while (l >= 0) {
			number[r] = number[l];
			l--; r++;
		}
		
		if (not(leftSufficient)) {
			l = mid - 1;
			int carry = 1;
			
			// if odd number of digits are there,
			// increment middle by 1 and pass on carry
			// to the left
			if (n % 2 == 1) {
				carry = carry + number[mid];
				number[mid] = (carry % 10);
				carry = carry / 10;
				r = mid + 1;
			}
			else {
				r = mid;
			}
			
			while (l >= 0) {
				carry = carry + number[l];
				number[l] = (carry % 10);
				carry = carry / 10;
				number[r] = number[l];
				r++; l--;
			}
		}
		
		return number;
		
	}
	
	static boolean not(boolean flag) {
		return ! flag;
	}
	
	public static void main(String[] args) {
		int[] number = {1};
		System.out.println(Arrays.toString(number));
		number = nextSmallestPalindrome(number);
		System.out.println(Arrays.toString(number));
	}

}
