package org.naveen.interestingprograms;

import java.util.LinkedList;
import java.util.Queue;

public class UglyNumbers {

	public static void main(String[] args) {
		printUglyNumbersWay1(50);
		printUglyNumbersWay2(50);
	}
	
	private static int min(int a, int b) {
		return a < b ? a : b;
	}
	
	private static void printUglyNumbersWay1(int n) {
		int count = 1;
		int min = 1;
		Queue<Integer> q2 = new LinkedList<>();
		Queue<Integer> q3 = new LinkedList<>();
		Queue<Integer> q5 = new LinkedList<>();
		int i2, i3, i5;
		
		q2.add(2);
		q3.add(3);
		q5.add(5);
		
		while (count <= n){
			System.out.println(count + " --> " + min);
			i2 = q2.peek();
			i3 = q3.peek();
			i5 = q5.peek();
			
			min = min(i2, min(i3, i5));
			
			if (min == i2) {
				q2.add(min * 2);
				q3.add(min * 3);				
				q2.remove();
				if (min == i3)  q3.remove();
				if (min == i5)  q5.remove();
				
			}
			
			else if (min == i3) {
				q3.add(min * 3);				
				q3.remove();
				if (min == i5) q5.remove();
			}
			
			else if (min == i5) {
				q5.remove();
			}
			
			q5.add(min * 5);
			count ++;
		}
	}
	
	private static void printUglyNumbersWay2(int n) {
		int[] a = new int[n];
		int nextno = 1;
		a[0] = nextno;
		int next2 = 2, next3 = 3, next5 = 5;
		int i2 = 0, i3 = 0, i5 = 0;
		
		for (int i = 1; i < n; i++) {
			nextno = min(next2, min(next3, next5));
			a[i] = nextno;
			
			if (nextno == next2) {
				i2++;
				next2 = 2*a[i2];
			}
			
			if (next3 == nextno) {
				i3++;
				next3 = 3*a[i3];
			}
			
			if (next5 == nextno){
				i5++;
				next5 = a[i5] * 5;
			}
		}
		
		for (int i = 0; i < n; i++) {
			System.out.println(i + " --> " + a[i]);
		}
	}
}
