package org.naveen.interestingprograms;

import java.util.LinkedList;
import java.util.Queue;

public class UglyNumbers {

	public static void main(String[] args) {
		int n = 50;
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
	
	private static int min(int a, int b) {
		return a < b ? a : b;
	}
}
