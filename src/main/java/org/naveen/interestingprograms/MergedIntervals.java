package org.naveen.interestingprograms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MergedIntervals {
	private static class Interval implements Cloneable{
		int start;
		int end;
		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
		public String toString() {
			return "[" + start + ", " + end + "]";
		}
		
		@Override
		public Interval clone() throws CloneNotSupportedException{
				return (Interval)super.clone();
		}
	}
	
	public static void main(String[] args) throws CloneNotSupportedException {
		List<Interval> intervals = new ArrayList<>();
		fillIntervals(intervals);
		System.out.println(intervals);
		Collections.sort(intervals, (x, y) -> {
			return x.start > y.start ? 1 : 
				(x.start == y.start ? 0 : -1);
		});
		
		Stack<Interval> stack = new Stack<>();
		stack.push(intervals.get(0).clone());
		
		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			Interval top = stack.peek();
			
			if (top.end > current.end) continue;
			else if (top.end > current.start) {
				top.end = current.end;
			}
			else {
				stack.push(current.clone());
			}			
		}
		
		System.out.println(stack);
	}
	
	private static void fillIntervals(List<Interval> intervals) {
		intervals.add(new Interval(6,8));
		intervals.add(new Interval(1,3));
		intervals.add(new Interval(2,4));
		intervals.add(new Interval(4,6));
	}

}
