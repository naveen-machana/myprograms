package org.naveen.concurrency.testprograms;

public class LuckyNumber {
	
	public static boolean isLucky(int number, int counter) {
		if (number < counter) return true;
		if (number % counter == 0) return false;
		
		number -= number / counter;
		return isLucky(number, counter + 1);
	}
	
	public static void main(String[] args) {
		int counter = 2;
		System.out.println(isLucky(13, counter));
	}

}
